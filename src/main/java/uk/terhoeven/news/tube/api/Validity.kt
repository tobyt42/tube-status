package uk.terhoeven.news.tube.api

import com.google.common.base.MoreObjects

class Validity(private val validities: List<ValidityPeriod>) {

    fun hasExpired(): Boolean {
        if (validities.isEmpty()) {
            return false
        }

        for (period in validities) {
            if (!period.hasExpired()) {
                return false
            }
        }

        return true
    }

    override fun toString(): String {
        return MoreObjects.toStringHelper(this).add("validities", validities).toString()
    }
}
