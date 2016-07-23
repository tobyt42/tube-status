package uk.terhoeven.news.tube.mock;

import uk.terhoeven.news.tube.ExampleApp;
import uk.terhoeven.news.tube.json.JSONRequestHandler;
import uk.terhoeven.news.tube.mapper.ResponseMapper;
import uk.terhoeven.news.tube.parser.NaturalTextParser;

import java.io.IOException;

public class MockExampleApp
{
	public static void main(final String[] args) throws IOException
	{
		final JSONRequestHandler requestHandler = new MockRequestHandler();
		final ResponseMapper mapper = new ResponseMapper();
		final NaturalTextParser parser = new NaturalTextParser();

		final ExampleApp app = new ExampleApp(requestHandler, mapper, parser);
		app.start();
	}

}
