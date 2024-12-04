package org.eruanno

import java.io.File
import kotlin.math.abs

class DayTwo {

    fun partOne(fileName: String): Int {
        return getLines(fileName)
            .map { it.split("\\s+".toRegex()).map(String::toInt) }
            .count(::isReportSafe)
    }

    private fun isReportSafe(levels: List<Int>): Boolean {
        val direction = levels[0] > levels[1]
        return levels.zipWithNext().all { (a, b) ->
            direction == (a > b) && abs(b - a) in 1..3
        }
    }

    fun partTwo(fileName: String): Int {
        return getLines(fileName)
            .map { it.split("\\s+".toRegex()).map(String::toInt) }
            .count(::isReportSafeWithTolerance)
    }

    private fun isReportSafeWithTolerance(levels: List<Int>): Boolean {
        return isReportSafe(levels) || levels.indices
            .map { index -> levels.filterIndexed { i, _ -> i != index } }
            .any { isReportSafe(it) }
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
