package uk.terhoeven.news.tube.json;

import com.google.common.base.Joiner;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class RequestHandler implements JSONRequestHandler
{
	private String request(final String request) throws IOException
	{
		return IOUtils.toString(new URL("https://api.tfl.gov.uk/" + request), Charset.forName("UTF-8"));
	}

	public JSONArray requestStatus(final String... lines) throws IOException
	{
		final String linesString = Joiner.on(',').join(lines);
		final String source = request("Line/" + linesString + "/Status");
		return new JSONArray(source);
	}
}
