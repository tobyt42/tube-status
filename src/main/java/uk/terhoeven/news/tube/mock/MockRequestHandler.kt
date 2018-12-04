package uk.terhoeven.news.tube.mock

import com.google.common.base.Joiner
import org.apache.commons.io.IOUtils
import org.json.JSONArray
import uk.terhoeven.news.tube.json.JSONRequestHandler

import java.io.FileInputStream
import java.io.IOException

class MockRequestHandler : JSONRequestHandler {
    @Throws(IOException::class)
    private fun request(request: String): String {
        return IOUtils.toString(FileInputStream("src/test/resources/$request.json"))
    }

    @Throws(IOException::class)
    override fun requestStatus(vararg lines: String): JSONArray {
        val linesString = Joiner.on('.').join(lines)
        return JSONArray(request("status-$linesString"))
    }
}
