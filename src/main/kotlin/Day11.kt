package org.eruanno

import java.io.File

class Day11 {

    fun partOne(fileName: String): Long {
        val stones = getLines(fileName).first().split(" ").map { it.toLong() }
        return blink(25, stones)
    }

    fun partTwo(fileName: String): Long {
        val stones = getLines(fileName).first().split(" ").map { it.toLong() }
        return blink(75, stones)
    }

    private fun blink(blinks: Int, inputStones: List<Long>): Long {
        var blink = 0
        var stones = inputStones.associateWith { 1L }.toMutableMap()
        while (blink < blinks) {
            val newStones = mutableMapOf<Long, Long>()
            for ((stone, occurrence) in stones) {
                if (stone == 0L) {
                    newStones.merge(1, occurrence) { oldValue, newValue -> oldValue + newValue }
                } else if (stone.toString().length % 2 == 0) {
                    val left = stone.toString().substring(0, (stone.toString().length / 2))
                    val right = stone.toString().substring(stone.toString().length / 2, stone.toString().length)
                    newStones.merge(left.toLong(), occurrence) { oldValue, newValue -> oldValue + newValue }
                    newStones.merge(right.toLong(), occurrence) { oldValue, newValue -> oldValue + newValue }
                } else {
                    newStones.merge(stone * 2024, occurrence) { oldValue, newValue -> oldValue + newValue }
                }
            }
            stones = newStones
            blink++
        }
        return stones.values.sumOf { it }
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
