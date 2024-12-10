package org.eruanno

import java.io.File

class Day10 {

    fun partOne(fileName: String): Int {
        val map = toCharMatrix(getLines(fileName))
        var row = 0
        val acc = mutableMapOf<Pair<Int, Int>, MutableSet<Pair<Int, Int>>>()
        while (row < map.size) {
            var column = 0
            while (column < map[0].size) {
                if (map[row][column] == 0) {
                    acc[Pair(row, column)] = searchTrailheads(map, row, column)
                }
                column++
            }
            row++
        }
        return acc.values.sumOf { it.size }
    }

    private fun searchTrailheads(
        map: MutableList<MutableList<Int>>,
        row: Int,
        column: Int
    ): MutableSet<Pair<Int, Int>> {
        if (map[row][column] == 9) {
            return mutableSetOf(Pair(row, column))
        }
        val acc = mutableSetOf<Pair<Int, Int>>()
        if (row + 1 < map.size && map[row + 1][column] == map[row][column] + 1) {
            acc.addAll(searchTrailheads(map, row + 1, column))
        }
        if (row - 1 >= 0 && map[row - 1][column] == map[row][column] + 1) {
            acc.addAll(searchTrailheads(map, row - 1, column))
        }
        if (column + 1 < map[0].size && map[row][column + 1] == map[row][column] + 1) {
            acc.addAll(searchTrailheads(map, row, column + 1))
        }
        if (column - 1 >= 0 && map[row][column - 1] == map[row][column] + 1) {
            acc.addAll(searchTrailheads(map, row, column - 1))
        }
        return acc
    }

    fun partTwo(fileName: String): Int {
        val map = toCharMatrix(getLines(fileName))
        var row = 0
        var acc = 0
        while (row < map.size) {
            var column = 0
            while (column < map[0].size) {
                if (map[row][column] == 0) {
                    acc += searchTrailheadsDistinct(map, row, column)
                }
                column++
            }
            row++
        }
        return acc
    }

    private fun searchTrailheadsDistinct(
        map: MutableList<MutableList<Int>>,
        row: Int,
        column: Int
    ): Int {
        if (map[row][column] == 9) {
            return 1
        }
        var acc = 0
        if (row + 1 < map.size && map[row + 1][column] == map[row][column] + 1) {
            acc += searchTrailheadsDistinct(map, row + 1, column)
        }
        if (row - 1 >= 0 && map[row - 1][column] == map[row][column] + 1) {
            acc += searchTrailheadsDistinct(map, row - 1, column)
        }
        if (column + 1 < map[0].size && map[row][column + 1] == map[row][column] + 1) {
            acc += searchTrailheadsDistinct(map, row, column + 1)
        }
        if (column - 1 >= 0 && map[row][column - 1] == map[row][column] + 1) {
            acc += searchTrailheadsDistinct(map, row, column - 1)
        }
        return acc
    }

    private fun toCharMatrix(strings: List<String>): MutableList<MutableList<Int>> {
        return strings.map { row ->
            row.map { char -> char.digitToInt() }.toMutableList()
        }.toMutableList()
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
