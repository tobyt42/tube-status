package uk.terhoeven.news.tube.api;

import com.google.common.base.MoreObjects;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class Line
{
	public static Line BAKERLOO = new Line("bakerloo", "Bakerloo");
	public static Line CENTRAL = new Line("central", "Central");
	public static Line CIRCLE = new Line("circle", "Circle");
	public static Line DISTRICT = new Line("district", "District");
	public static Line DLR = new Line("dlr", "DLR");
	public static Line JUBILEE = new Line("jubilee", "Jubilee");
	public static Line HAMMERSMITH_CITY = new Line("hammersmith-city", "Hammersmith and City");
	public static Line METROPOLITAN = new Line("metropolitan", "Metropolitan");
	public static Line NORTHERN = new Line("northern", "Northern");
	public static Line OVERGROUND = new Line("london-overground", "London Overground");
	public static Line PICCADILLY = new Line("piccadilly", "Piccadilly");
	public static Line TFL_RAIL = new Line("tfl-rail", "TfL Rail");
	public static Line TRAM = new Line("tram", "Tram");
	public static Line VICTORIA = new Line("victoria", "Victoria");
	public static Line WATERLOO_CITY = new Line("waterloo-city", "Waterloo and City");
	public static Collection<Line> LINES = Arrays.asList(BAKERLOO, CENTRAL, CIRCLE, DISTRICT, DLR, JUBILEE, HAMMERSMITH_CITY, METROPOLITAN, NORTHERN, OVERGROUND, PICCADILLY, TFL_RAIL, TRAM, VICTORIA, WATERLOO_CITY);

	private final String id;
	private final String name;

	public Line(final String id, final String name)
	{
		this.id = id;
		this.name = name;
	}

	public static Line parse(final String lineId)
	{
		final Optional<Line> match = LINES.stream().filter(line -> line.getId().equals(lineId)).findAny();
		if (match.isPresent()) {
			return match.get();
		}
		return new Line(lineId, lineId);
	}

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("name", name)
				.toString();
	}
}
