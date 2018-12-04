package uk.terhoeven.news.tube.json

import com.google.common.base.Joiner
import org.apache.commons.io.IOUtils
import org.json.JSONArray

import java.io.IOException
import java.net.URL
import java.nio.charset.Charset

class RequestHandler : JSONRequestHandler {
    @Throws(IOException::class)
    private fun request(request: String): String {
        return IOUtils.toString(URL("https://api.tfl.gov.uk/$request"), Charset.forName("UTF-8"))
    }

    @Throws(IOException::class)
    override fun requestStatus(vararg lines: String): JSONArray {
        val linesString = Joiner.on(',').join(lines)
        val source = request("Line/$linesString/Status")
        return JSONArray(source)
    }
}
