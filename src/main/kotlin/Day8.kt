package org.eruanno

import java.io.File

class Day8 {

    fun partOne(fileName: String): Int {
        val lines = getLines(fileName)
        val matrix = toCharMatrix(lines)
        val map = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        var row = 0
        while (row < matrix.size) {
            var column = 0
            while (column < matrix[0].size) {
                if (matrix[row][column] != '.') {
                    map.getOrPut(matrix[row][column]) { mutableListOf() }.add(row to column)
                }
                column++
            }
            row++
        }
        return map.filter { (_, list) -> list.size > 1 }
            .map { (_, list) -> countAntinodes(list, matrix[0].size, matrix.size) }
            .flatten().toSet().size
    }

    private fun countAntinodes(antennas: List<Pair<Int, Int>>, maxX: Int, maxY: Int): MutableList<Pair<Int, Int>> {
        var acc = mutableListOf<Pair<Int, Int>>()

        antennas.flatMap { a ->
            antennas.filter { b -> a != b }.map { b -> a to b }
        }.forEach { (a, b) ->
            val xd = a.first - b.first
            val yd = a.second - b.second
            val x1 = a.first + xd
            val y1 = a.second + yd
            if ((x1 in 0 until maxX) && (y1 in 0 until maxY)) {
                acc.add(x1 to y1)
            }
            val x2 = b.first - xd
            val y2 = b.second - yd
            if ((x2 in 0 until maxX) && (y2 in 0 until maxY)) {
                acc.add(x2 to y2)
            }
        }

        return acc
    }

    fun partTwo(fileName: String): Int {
        val lines = getLines(fileName)
        val matrix = toCharMatrix(lines)
        val map = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
        var row = 0
        while (row < matrix.size) {
            var column = 0
            while (column < matrix[0].size) {
                if (matrix[row][column] != '.') {
                    map.getOrPut(matrix[row][column]) { mutableListOf() }.add(row to column)
                }
                column++
            }
            row++
        }
        return map.filter { (_, list) -> list.size > 1 }
            .map { (_, list) -> countAntinodesWithResonantHarmonics(list, matrix[0].size, matrix.size) }
            .flatten().toSet().size
    }

    private fun countAntinodesWithResonantHarmonics(antennas: List<Pair<Int, Int>>, maxX: Int, maxY: Int): MutableList<Pair<Int, Int>> {
        var acc = mutableListOf<Pair<Int, Int>>()

        antennas.flatMap { a ->
            antennas.filter { b -> a != b }.map { b -> a to b }
        }.forEach { (a, b) ->
            acc.add(a)
            acc.add(b)
            val xd = a.first - b.first
            val yd = a.second - b.second
            var x1 = a.first + xd
            var y1 = a.second + yd
            while ((x1 in 0 until maxX) && (y1 in 0 until maxY)) {
                acc.add(x1 to y1)
                x1 += xd
                y1 += yd
            }
            var x2 = b.first - xd
            var y2 = b.second - yd
            while ((x2 in 0 until maxX) && (y2 in 0 until maxY)) {
                acc.add(x2 to y2)
                x2 -= xd
                y2 -= yd
            }
        }
        println("${acc.toSet()}")
        return acc
    }

    private fun toCharMatrix(strings: List<String>): MutableList<MutableList<Char>> {
        val maxLength = strings.maxOf { it.length }
        return strings.map { it.padEnd(maxLength, ' ').toMutableList() }.toMutableList()
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
