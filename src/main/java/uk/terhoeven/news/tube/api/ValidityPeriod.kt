package uk.terhoeven.news.tube.api

import com.google.common.base.MoreObjects
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat

class ValidityPeriod(fromDate: String, toDate: String) {

    private val fromDate: DateTime
    private val toDate: DateTime

    init {
        val parser = ISODateTimeFormat.dateTimeNoMillis()
        this.fromDate = parser.parseDateTime(fromDate)
        this.toDate = parser.parseDateTime(toDate)
    }

    fun hasExpired(): Boolean {
        return toDate.isBeforeNow
    }

    override fun toString(): String {
        return MoreObjects.toStringHelper(this).add("fromDate", fromDate).add("toDate", toDate).toString()
    }
}
