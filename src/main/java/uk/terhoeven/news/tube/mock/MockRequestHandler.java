package uk.terhoeven.news.tube.mock;

import com.google.common.base.Joiner;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import uk.terhoeven.news.tube.json.JSONRequestHandler;

import java.io.FileInputStream;
import java.io.IOException;

public class MockRequestHandler implements JSONRequestHandler
{
	private String request(final String request) throws IOException
	{
		return IOUtils.toString(new FileInputStream("src/test/resources/" + request + ".json"));
	}

	public JSONArray requestStatus(final String... lines) throws IOException
	{
		final String linesString = Joiner.on('.').join(lines);
		return new JSONArray(request("status-" + linesString));
	}
}
