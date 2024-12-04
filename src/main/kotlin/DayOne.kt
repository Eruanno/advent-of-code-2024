package org.eruanno

import java.io.File
import kotlin.math.abs

class DayOne {

    fun partOne(fileName: String): Int {
        val (locationsIds1, locationsIds2) = getLines(fileName)
            .map { it.split("\\s+".toRegex()) }
            .map { it[0].toInt() to it[1].toInt() }
            .unzip()

        return locationsIds1.sorted().zip(locationsIds2.sorted()).sumOf { abs(it.first - it.second) }
    }

    fun partTwo(fileName: String): Int {
        val (locationsIds1, locationsIds2) = getLines(fileName)
            .map { it.split("\\s+".toRegex()) }
            .map { it[0].toInt() to it[1].toInt() }
            .unzip()

        val locationsOccurrences = locationsIds2.groupingBy { it }.eachCount()

        return locationsIds1.sumOf { it * locationsOccurrences.getOrDefault(it, 0) }
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
