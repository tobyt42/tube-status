package uk.terhoeven.news.tube.parser;

import com.google.common.base.Joiner;
import uk.terhoeven.news.tube.api.Disruption;
import uk.terhoeven.news.tube.api.LineStatus;
import uk.terhoeven.news.tube.api.StatusResponse;

import java.util.ArrayList;
import java.util.List;

public class NaturalTextParser
{
	public String parse(final LineStatus status)
	{
		final Disruption disruption = status.getDisruption();
		if (disruption == null)
		{
			return status.getSeverity() + " on the " + status.getLine().getName() + " line.";
		}
		else
		{
			return disruption.getCategory() + " disruption on the " + status.getLine().getName() + " line: " + disruption.getDescription();
		}
	}

	public String parse(final StatusResponse statusResponse)
	{
		final List<String> lineStatuses = new ArrayList<>();

		statusResponse.getStatuses().stream()
				.filter(lineStatus -> !lineStatus.getValidity().hasExpired())
				.forEach(lineStatus -> lineStatuses.add(parse(lineStatus)));

		return Joiner.on(System.lineSeparator()).join(lineStatuses);
	}
}
