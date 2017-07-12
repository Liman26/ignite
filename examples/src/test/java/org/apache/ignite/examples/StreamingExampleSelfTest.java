package java.org.apache.ignite.examples;

import org.junit.Test;
import java.org.apache.ignite.examples.streaming.StreamingExample;


/**
 * Created by Roman Taranov on 12.07.2017.
 *
 * Streaming examples self test.
 */
public class StreamingExampleSelfTest
{
	static final String[] EMPTY_ARGS = new String[0];

	/**
	 * @throws Exception If failed.
	 */
	@Test
	public void mainTest() throws Exception
	{
		StreamingExample.main(EMPTY_ARGS);
	}
}
