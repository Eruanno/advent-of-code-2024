package org.eruanno

import java.io.File

class Day12 {

    fun partOne(fileName: String): Int {
        val map = toCharMatrix(getLines(fileName))
        val farms = mutableListOf<MutableSet<Pair<Int, Int>>>()
        var row = 0
        while (row < map.size) {
            var column = 0
            while (column < map[0].size) {
                if (!farms.any { set -> Pair(row, column) in set }) {
                    val currentType = map[row][column]
                    farms.add(exploreFarm(currentType, row, column, map, mutableSetOf()))
                }
                column++
            }
            row++
        }
        return farms.sumOf { calculateFenceCost(it) }
    }

    private fun exploreFarm(
        type: Char,
        row: Int,
        column: Int,
        map: MutableList<MutableList<Char>>,
        inputFarm: MutableSet<Pair<Int, Int>>
    ): MutableSet<Pair<Int, Int>> {
        if (inputFarm.contains(Pair(row, column))) {
            return inputFarm
        }
        var farm = inputFarm
        farm.add(Pair(row, column))
        if (row + 1 < map.size && map[row + 1][column] == type) {
            farm = (exploreFarm(type, row + 1, column, map, farm))
        }
        if (row - 1 >= 0 && map[row - 1][column] == type) {
            farm = (exploreFarm(type, row - 1, column, map, farm))
        }
        if (column + 1 < map[0].size && map[row][column + 1] == type) {
            farm = (exploreFarm(type, row, column + 1, map, farm))
        }
        if (column - 1 >= 0 && map[row][column - 1] == type) {
            farm = (exploreFarm(type, row, column - 1, map, farm))
        }
        return farm
    }

    private fun calculateFenceCost(farm: MutableSet<Pair<Int, Int>>): Int {
        val minRow = farm.minBy { it.first }.first
        val maxRow = farm.maxBy { it.first }.first
        val minColumn = farm.minBy { it.second }.second
        val maxColumn = farm.maxBy { it.second }.second
        var row = minRow
        var fences = 0
        while (row <= maxRow) {
            var column = minColumn
            while (column <= maxColumn) {
                if (!farm.contains(Pair(row, column))) {
                    column++
                    continue
                }
                if (!farm.contains(Pair(row - 1, column))) {
                    fences++
                }
                if (!farm.contains(Pair(row + 1, column))) {
                    fences++
                }
                if (!farm.contains(Pair(row, column - 1))) {
                    fences++
                }
                if (!farm.contains(Pair(row, column + 1))) {
                    fences++
                }
                column++
            }
            row++
        }
        return fences * farm.size
    }

    fun partTwo(fileName: String): Int {
        val map = toCharMatrix(getLines(fileName))
        val farms = mutableListOf<MutableSet<Pair<Int, Int>>>()
        var row = 0
        while (row < map.size) {
            var column = 0
            while (column < map[0].size) {
                if (!farms.any { set -> Pair(row, column) in set }) {
                    val currentType = map[row][column]
                    farms.add(exploreFarm(currentType, row, column, map, mutableSetOf()))
                }
                column++
            }
            row++
        }
        return farms.sumOf { calculateFenceCostWithDiscount(it) }
    }

    private fun calculateFenceCostWithDiscount(farm: MutableSet<Pair<Int, Int>>): Int {
        val minRow = farm.minBy { it.first }.first
        val maxRow = farm.maxBy { it.first }.first
        val minColumn = farm.minBy { it.second }.second
        val maxColumn = farm.maxBy { it.second }.second
        var row = minRow
        var corners = 0
        while (row <= maxRow) {
            var column = minColumn
            while (column <= maxColumn) {
                if (!farm.contains(Pair(row, column))) {
                    column++
                    continue
                }
                // Outside corners.
                if (!farm.contains(Pair(row, column - 1)) && !farm.contains(Pair(row - 1, column - 1)) && !farm.contains(Pair(row - 1, column))) {
                    corners++
                }
                if (!farm.contains(Pair(row - 1, column)) && !farm.contains(Pair(row - 1, column + 1)) && !farm.contains(Pair(row, column + 1))) {
                    corners++
                }
                if (!farm.contains(Pair(row, column + 1)) && !farm.contains(Pair(row + 1, column + 1)) && !farm.contains(Pair(row + 1, column))) {
                    corners++
                }
                if (!farm.contains(Pair(row + 1, column)) && !farm.contains(Pair(row + 1, column - 1)) && !farm.contains(Pair(row, column - 1))) {
                    corners++
                }
                // Inside corners.
                if (farm.contains(Pair(row, column -1 )) && farm.contains(Pair(row -1 , column )) && !farm.contains(Pair(row - 1, column - 1))) {
                    corners++
                }
                if (farm.contains(Pair(row - 1, column)) && farm.contains(Pair(row, column +1)) && !farm.contains(Pair(row - 1, column + 1))) {
                    corners++
                }
                if (farm.contains(Pair(row, column+1)) && farm.contains(Pair(row+1, column )) && !farm.contains(Pair(row +1, column+1))) {
                    corners++
                }
                if (farm.contains(Pair(row+1, column)) && farm.contains(Pair(row, column-1 )) && !farm.contains(Pair(row+1, column-1 ))) {
                    corners++
                }
                // cross corners corners.
                if (!farm.contains(Pair(row, column -1 )) && !farm.contains(Pair(row -1 , column )) && farm.contains(Pair(row - 1, column - 1))) {
                    corners++
                }
                if (!farm.contains(Pair(row - 1, column)) && !farm.contains(Pair(row, column +1)) && farm.contains(Pair(row - 1, column + 1))) {
                    corners++
                }
                if (!farm.contains(Pair(row, column+1)) && !farm.contains(Pair(row+1, column )) && farm.contains(Pair(row +1, column+1))) {
                    corners++
                }
                if (!farm.contains(Pair(row+1, column)) && !farm.contains(Pair(row, column-1 )) && farm.contains(Pair(row+1, column-1 ))) {
                    corners++
                }
                column++
            }
            row++
        }
        return corners * farm.size
    }

    private fun toCharMatrix(strings: List<String>): MutableList<MutableList<Char>> {
        val maxLength = strings.maxOf { it.length }
        return strings.map { it.padEnd(maxLength, ' ').toMutableList() }.toMutableList()
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
