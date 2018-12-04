package uk.terhoeven.news.tube.api

enum class DisruptionCategory private constructor(private val category: String, private val description: String) {
    REAL_TIME("RealTime", "Real Time"),
    PLANNED_WORK("PlannedWork", "Planned Work"),
    UNKNOWN("Unknown", "Unknown");

    override fun toString(): String {
        return description
    }

    companion object {

        fun parse(category: String): DisruptionCategory {
            for (disruptionCategory in DisruptionCategory.values()) {
                if (disruptionCategory.category == category) {
                    return disruptionCategory
                }
            }

            println("Unknown disruption category: $category")
            return UNKNOWN
        }
    }
}
