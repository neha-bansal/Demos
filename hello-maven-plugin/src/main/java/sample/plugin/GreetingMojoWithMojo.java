package sample.plugin;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import other.MyObject;

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
    @Parameter()
    private Integer[] myArray;
    
    /**
     * My Map.
     */
    @Parameter
    private Map<String, Integer> myMap;
    
    /**
     * My Properties.
     */
    @Parameter
    private Properties myProperties;
    
    /**
     * My Object.
     */
    @Parameter
    private MyObject myObject;
	
    public void execute() throws MojoExecutionException
    {
        getLog().info(greeting + " at " + date);
        for (int i=0; i< myArray.length; i++) {
        	getLog().info(String.valueOf(Integer.valueOf(myArray[i])));
        }
        for (String key : myMap.keySet()) {
        	getLog().info(key + " " + myMap.get(key)); 
        	getLog().info("" + Integer.valueOf(myMap.get(key)));
        }
        for (Entry<Object, Object> entry : myProperties.entrySet()) {
        	getLog().info(entry.getKey() + ":" + entry.getValue());
        }
        getLog().info(myObject.getFirstField() + ":" + myObject.getClass().getPackage());
    }
}