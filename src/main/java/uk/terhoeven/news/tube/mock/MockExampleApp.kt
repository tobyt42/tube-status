package uk.terhoeven.news.tube.mock

import uk.terhoeven.news.tube.ExampleApp
import uk.terhoeven.news.tube.api.Line.Companion.LINE_IDS
import uk.terhoeven.news.tube.mapper.ResponseMapper
import uk.terhoeven.news.tube.parser.NaturalTextParser
import java.io.IOException


object MockExampleApp {
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val requestHandler = MockRequestHandler()
        val mapper = ResponseMapper()
        val parser = NaturalTextParser()

        val app = ExampleApp(requestHandler, mapper, parser)
        app.start(if (args.size > 0) args else LINE_IDS.toTypedArray())
    }

}
