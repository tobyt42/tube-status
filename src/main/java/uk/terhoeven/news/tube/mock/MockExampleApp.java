package uk.terhoeven.news.tube.mock;

import uk.terhoeven.news.tube.ExampleApp;
import uk.terhoeven.news.tube.json.JSONRequestHandler;
import uk.terhoeven.news.tube.mapper.ResponseMapper;
import uk.terhoeven.news.tube.parser.NaturalTextParser;

import java.io.IOException;

import static uk.terhoeven.news.tube.api.Line.*;
import static uk.terhoeven.news.tube.api.Line.CENTRAL;
import static uk.terhoeven.news.tube.api.Line.OVERGROUND;

public class MockExampleApp
{
	public static void main(final String[] args) throws IOException
	{
		final JSONRequestHandler requestHandler = new MockRequestHandler();
		final ResponseMapper mapper = new ResponseMapper();
		final NaturalTextParser parser = new NaturalTextParser();

		final ExampleApp app = new ExampleApp(requestHandler, mapper, parser);
		app.start(args.length > 0 ? args : new String[] {BAKERLOO.getId(), CIRCLE.getId(), HAMMERSMITH_CITY.getId(), METROPOLITAN.getId(), CENTRAL.getId(), OVERGROUND.getId()});
	}

}
