package uk.terhoeven.news.tube.api;

import com.google.common.base.MoreObjects;

import java.util.List;

public class Validity
{
	private final List<ValidityPeriod> validities;

	public Validity(final List<ValidityPeriod> validities)
	{
		this.validities = validities;
	}

	public boolean isNow()
	{
		for (final ValidityPeriod period : validities)
		{
			if (period.isNow())
			{
				return true;
			}
		}

		return false;
	}

	public boolean hasExpired()
	{
		if (validities.isEmpty())
		{
			return false;
		}

		for (final ValidityPeriod period : validities)
		{
			if (!period.hasExpired())
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString()
	{
		return MoreObjects.toStringHelper(this).add("validities", validities).toString();
	}
}
