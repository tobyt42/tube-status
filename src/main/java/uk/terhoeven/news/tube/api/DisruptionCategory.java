package uk.terhoeven.news.tube.api;

public enum DisruptionCategory
{
	REAL_TIME("RealTime", "Real Time"),
	PLANNED_WORK("PlannedWork", "Planned Work"),
	UNKNOWN("Unknown", "Unknown");

	private final String category;
	private final String description;

	DisruptionCategory(final String category, final String description)
	{
		this.category = category;
		this.description = description;
	}

	public static DisruptionCategory parse(final String category)
	{
		for (final DisruptionCategory disruptionCategory : DisruptionCategory.values())
		{
			if (disruptionCategory.category.equals(category))
			{
				return disruptionCategory;
			}
		}

		System.out.println("Unknown disruption category: " + category);
		return UNKNOWN;
	}

	@Override
	public String toString()
	{
		return description;
	}
}
