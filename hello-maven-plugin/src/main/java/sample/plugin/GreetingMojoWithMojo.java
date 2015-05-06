package sample.plugin;

import java.util.Date;
import java.util.Map;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Says "Hi" to the user.
 *
 */
@Mojo( name = "sayhi")
public class GreetingMojoWithMojo extends AbstractMojo
{
	@Parameter(property="greeting", defaultValue="Hello there from my GreetingMojo!!!")
	private String greeting;
	
	/**
     * My Date.
     */
    @Parameter(property="sayhi.date", defaultValue="2015-05-06 15:41:55")
    private Date date;
    
    /**
     * My Array.
     */
    @Parameter
    private int[] myArray;
    
//    /**
//     * My Map.
//     */
//    @Parameter
//    private Map<String, String> myMap;
	
    public void execute() throws MojoExecutionException
    {
        getLog().info(greeting + " at " + date);
        for (int i=0; i< myArray.length; i++) {
        	getLog().info(String.valueOf(myArray[i]));
        }
//        for (String key : myMap.keySet()) {
//        	getLog().info(key + " " + myMap.get(key)); 
//        }
    }
}