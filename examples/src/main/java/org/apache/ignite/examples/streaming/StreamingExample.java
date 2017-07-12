package java.org.apache.ignite.examples.streaming;

import org.apache.ignite.*;
import org.apache.ignite.cache.CacheEntryProcessor;
import org.apache.ignite.examples.ExampleNodeStartup;
import org.apache.ignite.stream.StreamTransformer;

import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.MutableEntry;
import java.io.IOException;

/**
 * Created by Roman Taranov on 12.07.2017.
 */
public class StreamingExample
{
	public static class StreamingExampleCacheEntryProcessor implements CacheEntryProcessor<String, Long, Object>
	{
		@Override
		public Object process(MutableEntry<String, Long> e, Object... arg) throws EntryProcessorException
		{
			Long val = e.getValue();
			e.setValue(val == null ? 1L : val + 1);
			return null;
		}
	}

	public static void main(String[] args) throws IgniteException, IOException
	{
		Ignition.setClientMode(true);
		try (Ignite ignite = Ignition.start("examples/config/example-ignite.xml"))
		{
			IgniteCache<String, Long> stmCache = ignite.getOrCreateCache("mycache");
			try (IgniteDataStreamer<String, Long> stmr = ignite.dataStreamer(stmCache.getName()))
			{
				stmr.allowOverwrite(true);
				stmr.receiver(StreamTransformer.from(new StreamingExampleCacheEntryProcessor()));
				stmr.addData("word", 1L);
				System.out.println("Finished");
			}
		}
	}
}
