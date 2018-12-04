package uk.terhoeven.news.tube.mapper

import org.json.JSONArray
import org.json.JSONObject
import uk.terhoeven.news.tube.api.*
import java.util.*

class ResponseMapper {
    fun mapStatusResponse(apiResponse: JSONArray): StatusResponse {
        val statuses = ArrayList<LineStatus>()

        for (i in 0 until apiResponse.length()) {
            processStatuses(statuses, apiResponse.getJSONObject(i))
        }

        return StatusResponse(statuses)
    }

    private fun processStatuses(statuses: MutableCollection<LineStatus>, lineJson: JSONObject) {
        val jsonStatuses = lineJson.getJSONArray("lineStatuses")
        for (i in 0 until jsonStatuses.length()) {
            val jsonStatus = jsonStatuses.getJSONObject(i)

            val validity = Validity(getValidityPeriods(jsonStatus.getJSONArray("validityPeriods")))
            val severity = StatusSeverity.parse(jsonStatus.getString("statusSeverityDescription"))
            if (severity === StatusSeverity.GOOD_SERVICE) {
                val line = Line.parse(lineJson.getString("id"))
                val lineStatus = LineStatus(line, severity, validity)
                statuses.add(lineStatus)
            } else {
                val line = Line.parse(jsonStatus.getString("lineId"))
                val reason = jsonStatus.getString("reason")
                val disruptionJson = jsonStatus.getJSONObject("disruption")
                val disruption = Disruption(disruptionJson.getString("categoryDescription"), disruptionJson.getString("description"))
                val lineStatus = LineStatus(line, severity, validity, reason, disruption)
                statuses.add(lineStatus)
            }
        }
    }

    private fun getValidityPeriods(validityPeriods: JSONArray): List<ValidityPeriod> {
        val validities = ArrayList<ValidityPeriod>()
        for (i in 0 until validityPeriods.length()) {
            val period = validityPeriods.getJSONObject(i)
            validities.add(ValidityPeriod(period.getString("fromDate"), period.getString("toDate")))
        }
        return validities
    }
}
