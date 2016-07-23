package uk.terhoeven.news.tube.api;

public class Disruption
{
	private final DisruptionCategory category;
	private final String description;

	public Disruption(final String category, final String description)
	{
		this.category = DisruptionCategory.parse(category);
		this.description = description;
	}

	public DisruptionCategory getCategory()
	{
		return category;
	}

	public String getDescription()
	{
		return description;
	}
}
