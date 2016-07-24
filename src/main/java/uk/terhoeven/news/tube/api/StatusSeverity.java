package uk.terhoeven.news.tube.api;

public enum StatusSeverity
{
	PART_SUSPENDED("Part Suspended"),
	PART_CLOSURE("Part Closure"),
	GOOD_SERVICE("Good Service"),
	MINOR_DELAYS("Minor Delays"),
	SEVERE_DELAYS("Severe Delays"),
	UNKNOWN("Unknown");

	private final String severity;

	StatusSeverity(final String severity)
	{
		this.severity = severity;
	}

	public static StatusSeverity parse(final String severity)
	{
		for (final StatusSeverity statusSeverity : StatusSeverity.values())
		{
			if(statusSeverity.toString().equals(severity))
			{
				return statusSeverity;
			}
		}

		System.out.println("Unknown severity: " + severity);
		return UNKNOWN;
	}

	@Override
	public String toString()
	{
		return severity;
	}
}
