package uk.terhoeven.news.tube.json;

import org.json.JSONArray;

import java.io.IOException;

public interface JSONRequestHandler
{
	JSONArray requestStatus(String... lines) throws IOException;
}
