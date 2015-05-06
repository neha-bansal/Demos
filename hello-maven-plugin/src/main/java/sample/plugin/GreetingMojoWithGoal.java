package sample.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Says "Hi" to the user.
 * @goal saygreet
 *
 */
public class GreetingMojoWithGoal extends AbstractMojo
{
	/**
	 * @parameter default-value="Hello from GreetingMojoWithGoal" property="saygreet.greet"
	 */
	private String greeting;
	
    public void execute() throws MojoExecutionException
    {
        getLog().info(greeting);
    }
}