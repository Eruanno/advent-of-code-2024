package org.eruanno

import java.io.File
import kotlin.math.abs

class DayOne {

    fun partOne(fileName: String): Int {
        val filePath = this::class.java.getResource("/$fileName")?.path ?: return 0
        val (locationsIds1, locationsIds2) = File(filePath)
            .readLines()
            .map { it.split("\\s+".toRegex()) }
            .map { it[0].toInt() to it[1].toInt() }
            .unzip()

        return locationsIds1.sorted().zip(locationsIds2.sorted()).sumOf { abs(it.first - it.second) }
    }

    fun partTwo(fileName: String): Int {
        val filePath = this::class.java.getResource("/$fileName")?.path ?: return 0
        val (locationsIds1, locationsIds2) = File(filePath)
            .readLines()
            .map { it.split("\\s+".toRegex()) }
            .map { it[0].toInt() to it[1].toInt() }
            .unzip()

        val map = locationsIds2.groupingBy { it }.eachCount()

        return locationsIds1.sumOf { it * map.getOrDefault(it, 0) }
    }
}
