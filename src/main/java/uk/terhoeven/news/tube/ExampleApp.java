package uk.terhoeven.news.tube;

import org.json.JSONArray;
import uk.terhoeven.news.tube.api.StatusResponse;
import uk.terhoeven.news.tube.json.JSONRequestHandler;
import uk.terhoeven.news.tube.json.RequestHandler;
import uk.terhoeven.news.tube.mapper.ResponseMapper;
import uk.terhoeven.news.tube.parser.NaturalTextParser;

import java.io.IOException;

import static uk.terhoeven.news.tube.api.Line.*;

public class ExampleApp
{
	private final JSONRequestHandler requestHandler;
	private final ResponseMapper mapper;
	private final NaturalTextParser parser;

	public ExampleApp(final JSONRequestHandler requestHandler, final ResponseMapper mapper, final NaturalTextParser parser)
	{
		this.requestHandler = requestHandler;
		this.mapper = mapper;
		this.parser = parser;
	}

	public void start() throws IOException
	{
		final JSONArray status = requestHandler.requestStatus(BAKERLOO.getId(), CIRCLE.getId(), HAMMERSMITH_CITY.getId(), METROPOLITAN.getId(), CENTRAL.getId(), OVERGROUND.getId());
		final StatusResponse statusResponse = mapper.mapStatusResponse(status);
		System.out.println(parser.parse(statusResponse));
	}

	public static void main(final String[] args) throws IOException
	{
		final JSONRequestHandler requestHandler = new RequestHandler();
		final ResponseMapper mapper = new ResponseMapper();
		final NaturalTextParser parser = new NaturalTextParser();

		final ExampleApp app = new ExampleApp(requestHandler, mapper, parser);
		app.start();
	}

}
