package com.grizzly.jersey.chunked.test;

import static org.apache.http.client.params.CookiePolicy.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

public class ServicesProxyServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ServicesProxyServlet.class.getName());

    private static final long serialVersionUID = -8253533772553372091L;

    private static final String J_SECURITY_CHECK = "j_security_check";

    private static final String J_USERNAME = "j_username";

    private static final String J_PASSWORD = "j_password";

    private static final String URL_HEADER = "url";

    private class ServicesRequestBuilder<H extends HttpRequestBase> {

        private H httpRequestBase;

        ServicesRequestBuilder(final H httpRequestBase) {
            this.httpRequestBase = httpRequestBase;
        }

        public H createServicesRequest(final HttpServletRequest req) {
            URI uri = null;
            try {
                uri = new URI(req.getHeader(URL_HEADER));
            } catch (final Exception e) {
                LOGGER.log(Level.WARNING, "An exception occurred: ", e);
            }
            httpRequestBase.setURI(uri);
            httpRequestBase.setHeaders(extractHeaders(req));
            final H servicesRequest = httpRequestBase;
            httpRequestBase = null;
            return servicesRequest;
        }

        private Header[] extractHeaders(final HttpServletRequest req) {
            final String[] headerNames = new String[] { "srcIpAddress", "requestId", "Content-type", "Accept" };
            final ArrayList<Header> headers = new ArrayList<Header>();
            for (final String name : headerNames) {
                final Header header = new BasicHeader(name, req.getHeader(name));
                headers.add(header);
            }
            final Header[] headerArray = headers.toArray(new Header[headers.size()]);
            return headerArray;
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        LOGGER.info("Initializing Development Mode Proxy Servlet.");
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {
        LOGGER.finer("Post Calling URL: " + req.getHeader(URL_HEADER));

        final HttpClient httpClient = createHttpClient();
        final ServicesRequestBuilder<HttpPost> servicesRequestBuilder = new ServicesRequestBuilder<HttpPost>(
                new HttpPost());
        final HttpPost httpPost = servicesRequestBuilder.createServicesRequest(req);

        // Post and Put get an entity, Get does not
        final HttpEntity entity = new InputStreamEntity(req.getInputStream(), req.getContentLength());
        httpPost.setEntity(entity);

        executeRequest(httpClient, req, resp, httpPost);
    }

    @Override
    protected void doPut(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {
        LOGGER.finer("Put Calling URL: " + req.getHeader(URL_HEADER));

        final HttpClient httpClient = createHttpClient();
        final ServicesRequestBuilder<HttpPut> servicesRequestBuilder = new ServicesRequestBuilder<HttpPut>(
                new HttpPut());
        final HttpPut httpPut = servicesRequestBuilder.createServicesRequest(req);

        // Post and Put get an entity, Get does not
        final HttpEntity entity = new InputStreamEntity(req.getInputStream(), req.getContentLength());
        httpPut.setEntity(entity);
        executeRequest(httpClient, req, resp, httpPut);
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {
        LOGGER.finer("Get Calling URL: " + req.getHeader(URL_HEADER));

        final HttpClient httpClient = createHttpClient();
        final ServicesRequestBuilder<HttpGet> servicesRequestBuilder = new ServicesRequestBuilder<HttpGet>(
                new HttpGet());
        final HttpGet httpGet = servicesRequestBuilder.createServicesRequest(req);

        executeRequest(httpClient, req, resp, httpGet);
    }

    private void executeRequest(final HttpClient httpClient, final HttpServletRequest req,
            final HttpServletResponse resp, final HttpRequestBase requestForServices) {
        try {
            if (authenticate(httpClient, req) == null) {
                LOGGER.log(Level.WARNING, "Authentication failed");
            }
            final HttpResponse httpResponse = httpClient.execute(requestForServices);
            if (httpResponse == null) {
                LOGGER.log(Level.WARNING, "Request failed");
            } else {
                resp.setStatus(httpResponse.getStatusLine().getStatusCode());
                resp.getWriter().write(convertResponseToString(httpResponse));
            }
        } catch (final Exception e) {
            LOGGER.log(Level.WARNING, "An exception occurred", e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }

    private String convertResponseToString(final HttpResponse httpResponse) throws IOException {
        final InputStream inputStream = httpResponse.getEntity().getContent();
        final StringWriter writer = new StringWriter();
        IOUtils.copy(inputStream, writer, "UTF-8");
        return writer.toString();
    }

    // We are creating a new HTTPClient for each request. This is a memory hog but it works right now.
    // Much better would be to keep HTTPClient as a singleton, authenticate once and maintain the session.
    // However requests for MapResource should not be sent over the same authenticated HTTPClient.
    private HttpClient createHttpClient() {
        final HttpClient httpClient = new DefaultHttpClient(); // Yes, this is not nice
        httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, BROWSER_COMPATIBILITY);
        httpClient.getParams().setParameter(ClientPNames.HANDLE_AUTHENTICATION, true);
        httpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false);
        return httpClient;
    }

    private HttpResponse authenticate(final HttpClient httpClient, final HttpServletRequest req) throws IOException {
        final String userName = req.getHeader("userName");
        final String userPassword = req.getHeader("userPassword");
        final String appContextRoot = req.getHeader("appContextRoot");
        final HttpResponse servicesResponse = authenticateHttpClient(httpClient, appContextRoot, userName, userPassword);
        if (servicesResponse == null
                || servicesResponse.getStatusLine().getStatusCode() != HttpStatus.SC_MOVED_TEMPORARILY) {
            System.err.println(".......statuscode:" + servicesResponse.getStatusLine().getStatusCode());

            servicesResponse.getEntity().consumeContent();

            return servicesResponse;

        }
        servicesResponse.getEntity().consumeContent();
        httpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true);
        return servicesResponse;
    }

    private HttpResponse authenticateHttpClient(final HttpClient httpClient, final String url, final String userName,
            final String userPassword) throws IOException {

        LOGGER.info("Authenticating URL: " + url);

        final List<NameValuePair> formParams = new ArrayList<NameValuePair>();
        formParams.add(new BasicNameValuePair(J_USERNAME, userName));
        formParams.add(new BasicNameValuePair(J_PASSWORD, userPassword));

        final HttpPost httpPost = new HttpPost(url + J_SECURITY_CHECK);

        final UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, "UTF-8");
        httpPost.setEntity(entity);

        return httpClient.execute(httpPost);
    }
}
