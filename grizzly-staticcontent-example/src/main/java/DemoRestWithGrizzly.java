

import java.io.IOException;

import com.sun.grizzly.http.embed.GrizzlyWebServer;
import com.sun.grizzly.http.servlet.ServletAdapter;
import com.sun.jersey.spi.container.servlet.ServletContainer;

public class DemoRestWithGrizzly {

	public static void main(String[] args) {
		
		try {
		// static content is linked from here
        GrizzlyWebServer gws = new GrizzlyWebServer(8090, "/resources");

        // Jersey web resources
        ServletAdapter jerseyAdapter = new ServletAdapter();
        jerseyAdapter.addInitParameter("com.sun.jersey.config.property.packages",
                "com.example");
        jerseyAdapter.setContextPath("/jersey");
        jerseyAdapter.setServletInstance(new ServletContainer());
        
        // Another non-Jersey servlet
//        ServletAdapter simpleServletAdapter = new ServletAdapter();
//        simpleServletAdapter.setContextPath("/simple");
//        simpleServletAdapter.setServletInstance(new SimpleServlet());

        // register all above defined adapters
//        gws.addGrizzlyAdapter(jerseyAdapter, new String[] {"/jersey"});
//        gws.addGrizzlyAdapter(simpleServletAdapter, new String[] {"/simple"});

        // let Grizzly run
			gws.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
}
