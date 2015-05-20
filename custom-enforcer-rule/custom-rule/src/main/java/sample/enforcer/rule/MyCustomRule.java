package sample.enforcer.rule;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.enforcer.rule.api.EnforcerLevel;
import org.apache.maven.enforcer.rule.api.EnforcerRule;
import org.apache.maven.enforcer.rule.api.EnforcerRule2;
import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.apache.maven.enforcer.rule.api.EnforcerRuleHelper;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.execution.RuntimeInformation;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluationException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;

public class MyCustomRule implements EnforcerRule2 {
	
	private boolean shouldIFail = false;

	@Override
	public void execute(EnforcerRuleHelper helper) throws EnforcerRuleException {
		
		Log log = helper.getLog();
		
		try {
			MavenProject project = (MavenProject) helper.evaluate("${project}");
			MavenSession session = (MavenSession) helper.evaluate("${session}");
			String target = (String) helper.evaluate("${project.build.directory}");
			String artifactId = (String) helper.evaluate("${project.artifactId}");
			
			ArtifactResolver artifactRsolver = (ArtifactResolver) helper.getComponent(ArtifactResolver.class);
			RuntimeInformation runtimeInformation = (RuntimeInformation) helper.getComponent(RuntimeInformation.class);
			
			log.info( "Retrieved Target Folder: " + target );
            log.info( "Retrieved ArtifactId: " +artifactId );
            log.info( "Retrieved Project: " + project );
            log.info( "Retrieved RuntimeInfo: " + runtimeInformation.toString() + ":" + runtimeInformation.getApplicationVersion() );
            log.info( "Retrieved Session: " + session );
            log.info( "Retrieved Resolver: " + artifactRsolver );

			if (shouldIFail) {
				log.error("logging... failing as my param said so... "); 
				log.error(helper.getClass() + "");
				throw new EnforcerRuleException("failing as my param said so...");
			}
		} catch (ExpressionEvaluationException e) {
			e.printStackTrace();
		} catch (ComponentLookupException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public String getCacheId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCacheable() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isResultValid(EnforcerRule arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EnforcerLevel getLevel() {
		// TODO Auto-generated method stub
		return null;
	}

}
