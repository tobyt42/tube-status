package uk.terhoeven.news.tube.mapper;

import org.json.JSONArray;
import org.json.JSONObject;
import uk.terhoeven.news.tube.api.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ResponseMapper
{
	public StatusResponse mapStatusResponse(final JSONArray apiResponse)
	{
		final Collection<LineStatus> statuses = new ArrayList<>();

		for (int i=0 ; i<apiResponse.length() ; i++)
		{
			processStatuses(statuses, apiResponse.getJSONObject(i));
		}

		return new StatusResponse(statuses);
	}

	private void processStatuses(final Collection<LineStatus> statuses, final JSONObject lineJson)
	{
		final JSONArray jsonStatuses = lineJson.getJSONArray("lineStatuses");
		for (int i = 0; i < jsonStatuses.length(); i++)
		{
			final JSONObject jsonStatus = jsonStatuses.getJSONObject(i);

			final Validity validity = new Validity(getValidityPeriods(jsonStatus.getJSONArray("validityPeriods")));
			final StatusSeverity severity = StatusSeverity.parse(jsonStatus.getString("statusSeverityDescription"));
			if (severity == StatusSeverity.GOOD_SERVICE)
			{
				final Line line = Line.parse(lineJson.getString("id"));
				final LineStatus lineStatus = new LineStatus(line, severity, validity);
				statuses.add(lineStatus);
			}
			else
			{
				final Line line = Line.parse(jsonStatus.getString("lineId"));
				final String reason = jsonStatus.getString("reason");
				final JSONObject disruptionJson = jsonStatus.getJSONObject("disruption");
				final Disruption disruption = new Disruption(disruptionJson.getString("categoryDescription"), disruptionJson.getString("description"));
				final LineStatus lineStatus = new LineStatus(line, severity, validity, reason, disruption);
				statuses.add(lineStatus);
			}
		}
	}

	private List<ValidityPeriod> getValidityPeriods(final JSONArray validityPeriods)
	{
		final List<ValidityPeriod> validities = new ArrayList<>();
		for (int i=0; i<validityPeriods.length(); i++)
		{
			final JSONObject period = validityPeriods.getJSONObject(i);
			validities.add(new ValidityPeriod(period.getString("fromDate"), period.getString("toDate")));
		}
		return validities;
	}
}
