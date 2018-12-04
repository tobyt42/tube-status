package uk.terhoeven.news.tube.api

class Disruption(category: String, val description: String) {
    val category: DisruptionCategory = DisruptionCategory.parse(category)

}
