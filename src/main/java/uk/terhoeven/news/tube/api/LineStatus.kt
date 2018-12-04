package uk.terhoeven.news.tube.api

class LineStatus @JvmOverloads constructor(val line: Line, val severity: StatusSeverity, val validity: Validity, val reason: String? = null, val disruption: Disruption? = null)
