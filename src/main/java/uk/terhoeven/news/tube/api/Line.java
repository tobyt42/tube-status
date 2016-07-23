package uk.terhoeven.news.tube.api;

import com.google.common.base.MoreObjects;

public class Line
{
	public static Line OVERGROUND = new Line("london-overground", "London Overground");
	public static Line BAKERLOO = new Line("bakerloo", "Bakerloo");
	public static Line CENTRAL = new Line("central", "Central");
	public static Line CIRCLE = new Line("circle", "Circle");
	public static Line HAMMERSMITH_CITY = new Line("hammersmith-city", "Hammersmith and City");
	public static Line METROPOLITAN = new Line("metropolitan", "Metropolitan");

	private final String id;
	private final String name;

	public Line(final String id, final String name)
	{
		this.id = id;
		this.name = name;
	}

	public static Line parse(final String lineId)
	{
		if (OVERGROUND.getId().equals(lineId))
		{
			return OVERGROUND;
		}
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
		if (HAMMERSMITH_CITY.getId().equals(lineId))
		{
			return HAMMERSMITH_CITY;
		}
		if (METROPOLITAN.getId().equals(lineId))
		{
			return METROPOLITAN;
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
