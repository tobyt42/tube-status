package uk.terhoeven.news.tube.parser;

import com.google.common.base.Joiner;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import uk.terhoeven.news.tube.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		final Multimap<StatusSeverity, LineStatus> severityToStatusMap = HashMultimap.create();

		final List<String> text = new ArrayList<>();

		statusResponse.getStatuses().stream()
				.filter(lineStatus -> !lineStatus.getValidity().hasExpired())
				.forEach(lineStatus -> severityToStatusMap.put(lineStatus.getSeverity(), lineStatus));

		if(severityToStatusMap.containsKey(StatusSeverity.GOOD_SERVICE))
		{
			text.add(getStatus(StatusSeverity.GOOD_SERVICE, severityToStatusMap));
		}

		severityToStatusMap.keySet().stream()
				.filter(statusSeverity -> statusSeverity != StatusSeverity.GOOD_SERVICE)
				.forEach(statusSeverity -> text.add(getStatus(statusSeverity, severityToStatusMap)));

		text.add("");

		severityToStatusMap.keySet().stream()
				.filter(statusSeverity -> statusSeverity != StatusSeverity.GOOD_SERVICE)
				.map(severityToStatusMap::get)
				.forEach(lineStatuses -> lineStatuses.stream()
						.filter(lineStatus -> lineStatus.getDisruption() != null)
						.forEach(lineStatus -> text.add(lineStatus.getDisruption().getCategory() + " disruption on " + lineStatus.getDisruption().getDescription())));

		return Joiner.on(System.lineSeparator()).join(text);
	}

	private String getStatus(final StatusSeverity severity, final Multimap<StatusSeverity, LineStatus> severityToStatusMap)
	{
		final List<String> lines = severityToStatusMap.get(severity).stream()
				.map(LineStatus::getLine)
				.distinct()
				.map(Line::getName)
				.collect(Collectors.toList());

		return lines.size() == 1
				? severity + " on the " + lines.get(0) + " line."
				: severity + " on the " + Joiner.on(", ").join(lines.subList(0, lines.size() - 1))
					+ " and " + lines.get(lines.size() - 1) + " lines.";
	}
}
