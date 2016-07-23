package uk.terhoeven.news.tube.api;

public class LineStatus
{
	private final StatusSeverity severity;
	private final String reason;
	private final Line line;
	private final Validity validity;
	private final Disruption disruption;

	public LineStatus(final Line line, final StatusSeverity severity, final Validity validity, final String reason, final Disruption disruption)
	{
		this.severity = severity;
		this.reason = reason;
		this.line = line;
		this.validity = validity;
		this.disruption = disruption;
	}

	public LineStatus(final Line line, final StatusSeverity severity, final Validity validity)
	{
		this(line, severity, validity, null, null);
	}

	public StatusSeverity getSeverity()
	{
		return severity;
	}

	public String getReason()
	{
		return reason;
	}

	public Line getLine()
	{
		return line;
	}

	public Validity getValidity()
	{
		return validity;
	}

	public Disruption getDisruption()
	{
		return disruption;
	}
}
