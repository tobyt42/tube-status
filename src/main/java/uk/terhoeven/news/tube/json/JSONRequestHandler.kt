package uk.terhoeven.news.tube.json

import org.json.JSONArray

import java.io.IOException

interface JSONRequestHandler {
    @Throws(IOException::class)
    fun requestStatus(vararg lines: String): JSONArray
}
