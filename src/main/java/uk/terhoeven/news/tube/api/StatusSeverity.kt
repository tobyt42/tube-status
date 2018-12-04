package uk.terhoeven.news.tube.api

enum class StatusSeverity(private val severity: String) {
    PART_SUSPENDED("Part Suspended"),
    PART_CLOSURE("Part Closure"),
    GOOD_SERVICE("Good Service"),
    MINOR_DELAYS("Minor Delays"),
    SEVERE_DELAYS("Severe Delays"),
    SPECIAL_SERVICE("Special Service"),
    UNKNOWN("Unknown");

    override fun toString(): String {
        return severity
    }

    companion object {

        fun parse(severity: String): StatusSeverity {
            for (statusSeverity in StatusSeverity.values()) {
                if (statusSeverity.toString() == severity) {
                    return statusSeverity
                }
            }

            println("Unknown severity: $severity")
            return UNKNOWN
        }
    }
}
