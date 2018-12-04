package uk.terhoeven.news.tube

import uk.terhoeven.news.tube.api.Line.Companion.LINE_IDS
import uk.terhoeven.news.tube.json.JSONRequestHandler
import uk.terhoeven.news.tube.json.RequestHandler
import uk.terhoeven.news.tube.mapper.ResponseMapper
import uk.terhoeven.news.tube.parser.NaturalTextParser
import java.io.IOException

class ExampleApp(private val requestHandler: JSONRequestHandler, private val mapper: ResponseMapper, private val parser: NaturalTextParser) {

    @Throws(IOException::class)
    fun start(lines: Array<String>) {
        val status = requestHandler.requestStatus(*lines)
        val statusResponse = mapper.mapStatusResponse(status)
        println(parser.parse(statusResponse))
    }

    companion object {

        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val requestHandler = RequestHandler()
            val mapper = ResponseMapper()
            val parser = NaturalTextParser()

            val app = ExampleApp(requestHandler, mapper, parser)
            app.start(if (args.isNotEmpty()) args else LINE_IDS.toTypedArray())
        }
    }

}
