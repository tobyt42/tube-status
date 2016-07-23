package uk.terhoeven.news.tube.api;

import com.google.common.base.MoreObjects;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class ValidityPeriod
{

	private final DateTime fromDate;
	private final DateTime toDate;

	public ValidityPeriod(final String fromDate, final String toDate)
	{
		final DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();
		this.fromDate = parser.parseDateTime(fromDate);
		this.toDate = parser.parseDateTime(toDate);
	}

	public boolean isNow()
	{
		return fromDate.isBeforeNow() && toDate.isAfterNow();
	}

	public boolean hasExpired()
	{
		return toDate.isBeforeNow();
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this).add("fromDate", fromDate).add("toDate", toDate).toString();
	}
}
