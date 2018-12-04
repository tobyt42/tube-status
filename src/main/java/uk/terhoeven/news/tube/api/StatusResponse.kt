package uk.terhoeven.news.tube.api

import java.util.*

class StatusResponse(val statuses: Collection<LineStatus>) {
    val responseDate: Date = Date()

}
