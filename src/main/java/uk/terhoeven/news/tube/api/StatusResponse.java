package uk.terhoeven.news.tube.api;

import java.util.Collection;
import java.util.Date;

public class StatusResponse
{
	private final Collection<LineStatus> statuses;
	private final Date responseDate;

	public StatusResponse(final Collection<LineStatus> statuses)
	{
		this.statuses = statuses;
		responseDate = new Date();
	}

	public Date getResponseDate()
	{
		return responseDate;
	}

	public Collection<LineStatus> getStatuses()
	{
		return statuses;
	}
}
