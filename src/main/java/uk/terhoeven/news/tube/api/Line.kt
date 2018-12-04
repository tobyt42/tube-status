package uk.terhoeven.news.tube.api

import com.google.common.base.MoreObjects
import java.util.*
import java.util.stream.Collectors

class Line(val id: String, val name: String) {

    override fun toString(): String {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString()
    }

    companion object {
        var BAKERLOO = Line("bakerloo", "Bakerloo")
        var CENTRAL = Line("central", "Central")
        var CIRCLE = Line("circle", "Circle")
        var DISTRICT = Line("district", "District")
        var DLR = Line("dlr", "DLR")
        var JUBILEE = Line("jubilee", "Jubilee")
        var HAMMERSMITH_CITY = Line("hammersmith-city", "Hammersmith and City")
        var METROPOLITAN = Line("metropolitan", "Metropolitan")
        var NORTHERN = Line("northern", "Northern")
        var OVERGROUND = Line("london-overground", "London Overground")
        var PICCADILLY = Line("piccadilly", "Piccadilly")
        var TFL_RAIL = Line("tfl-rail", "TfL Rail")
        var TRAM = Line("tram", "Tram")
        var VICTORIA = Line("victoria", "Victoria")
        var WATERLOO_CITY = Line("waterloo-city", "Waterloo and City")
        var LINES: Collection<Line> = Arrays.asList(BAKERLOO, CENTRAL, CIRCLE, DISTRICT, DLR, JUBILEE, HAMMERSMITH_CITY, METROPOLITAN, NORTHERN, OVERGROUND, PICCADILLY, TFL_RAIL, TRAM, VICTORIA, WATERLOO_CITY)
        var LINE_IDS: Collection<String> = LINES.stream().map { it.id }.collect(Collectors.toList())

        fun parse(lineId: String): Line {
            val match = LINES.stream().filter { line -> line.id == lineId }.findAny()
            return if (match.isPresent) {
                match.get()
            } else Line(lineId, lineId)
        }
    }
}
