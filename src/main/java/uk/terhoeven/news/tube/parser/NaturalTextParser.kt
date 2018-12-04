package uk.terhoeven.news.tube.parser

import com.google.common.base.Joiner
import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import uk.terhoeven.news.tube.api.Line
import uk.terhoeven.news.tube.api.LineStatus
import uk.terhoeven.news.tube.api.StatusResponse
import uk.terhoeven.news.tube.api.StatusSeverity
import java.util.*
import java.util.stream.Collectors

class NaturalTextParser {

    fun parse(statusResponse: StatusResponse): String {
        val severityToStatusMap = HashMultimap.create<StatusSeverity, LineStatus>()

        val text = ArrayList<String>()

        statusResponse.statuses.stream()
                .filter { lineStatus -> !lineStatus.validity.hasExpired() }
                .forEach { lineStatus -> severityToStatusMap.put(lineStatus.severity, lineStatus) }

        if (severityToStatusMap.containsKey(StatusSeverity.GOOD_SERVICE)) {
            text.add(getStatus(StatusSeverity.GOOD_SERVICE, severityToStatusMap))
        }

        severityToStatusMap.keySet().stream()
                .filter { statusSeverity -> statusSeverity !== StatusSeverity.GOOD_SERVICE }
                .forEach { statusSeverity -> text.add(getStatus(statusSeverity, severityToStatusMap)) }

        text.add("")

        severityToStatusMap.keySet().stream()
                .filter { statusSeverity -> statusSeverity !== StatusSeverity.GOOD_SERVICE }
                .map<Collection<LineStatus>> { severityToStatusMap.get(it)}
                .forEach { lineStatuses ->
                    lineStatuses.stream()
                            .filter { lineStatus -> lineStatus.disruption != null }.forEach { lineStatus ->
                                val status = lineStatus.disruption!!.category.toString() + " disruption on " + lineStatus.disruption.description
                                if (!text.contains(status)) {
                                    text.add(status)
                                }
                            }
                }

        return Joiner.on(System.lineSeparator()).join(text)
    }

    private fun getStatus(severity: StatusSeverity, severityToStatusMap: Multimap<StatusSeverity, LineStatus>): String {
        val lines = severityToStatusMap.get(severity).stream()
                .map<Line> {it.line}
                .distinct()
                .map<String> {it.name}
                .collect(Collectors.toList())

        return if (lines.size == 1)
            severity.toString() + " on the " + lines[0] + " line."
        else
            severity.toString() + " on the " + Joiner.on(", ").join(lines.subList(0, lines.size - 1)) + " and " + lines[lines.size - 1] + " lines."
    }
}
