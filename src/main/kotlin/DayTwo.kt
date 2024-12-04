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
        levels.zipWithNext().forEach { (a, b) ->
            val isDirectionValid = direction == a > b
            val isDistanceValid = abs(b - a) in 1..3
            if (!isDirectionValid || !isDistanceValid) {
                return false
            }
        }
        return true
    }

    fun partTwo(fileName: String): Int {
        return getLines(fileName)
            .map { it.split("\\s+".toRegex()).map(String::toInt) }
            .count(::isReportSafeWithTolerance)
    }

    private fun isReportSafeWithTolerance(levels: List<Int>): Boolean {
        val isReportSafe = isReportSafe(levels)
        if (!isReportSafe) {
            return List(levels.size) { index -> levels.filterIndexed { i, _ -> i != index } }
                .map { isReportSafe(it) }
                .any { it }
        }
        return true
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
