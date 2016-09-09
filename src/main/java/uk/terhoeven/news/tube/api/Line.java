package uk.terhoeven.news.tube.api;

import com.google.common.base.MoreObjects;

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

	private final String id;
	private final String name;

	public Line(final String id, final String name)
	{
		this.id = id;
		this.name = name;
	}

	public static Line parse(final String lineId)
	{
		// TODO: do this nicer

		if (BAKERLOO.getId().equals(lineId))
		{
			return BAKERLOO;
		}
		if (CENTRAL.getId().equals(lineId))
		{
			return CENTRAL;
		}
		if (CIRCLE.getId().equals(lineId))
		{
			return CIRCLE;
		}
		if (DISTRICT.getId().equals(lineId))
		{
			return DISTRICT;
		}
		if (DLR.getId().equals(lineId))
		{
			return DLR;
		}
		if (HAMMERSMITH_CITY.getId().equals(lineId))
		{
			return HAMMERSMITH_CITY;
		}
		if (JUBILEE.getId().equals(lineId))
		{
			return JUBILEE;
		}
		if (METROPOLITAN.getId().equals(lineId))
		{
			return METROPOLITAN;
		}
		if (NORTHERN.getId().equals(lineId))
		{
			return NORTHERN;
		}
		if (OVERGROUND.getId().equals(lineId))
		{
			return OVERGROUND;
		}
		if (PICCADILLY.getId().equals(lineId))
		{
			return PICCADILLY;
		}
		if (TFL_RAIL.getId().equals(lineId))
		{
			return TFL_RAIL;
		}
		if (TRAM.getId().equals(lineId))
		{
			return TRAM;
		}
		if (VICTORIA.getId().equals(lineId))
		{
			return VICTORIA;
		}
		if (WATERLOO_CITY.getId().equals(lineId))
		{
			return WATERLOO_CITY;
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
