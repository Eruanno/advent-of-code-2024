package org.eruanno

import java.io.File

class DaySix {

    fun partOne(fileName: String): Int {
        val lines = getLines(fileName)
        val matrix = toCharMatrix(lines)
        val start = findDirection(matrix)
        var direction = start.first
        var row = start.second.first
        var column = start.second.second
        while (true) {
            var nextRow = row
            var nextColumn = column
            if (direction == '>')
                nextColumn++
            if (direction == 'v')
                nextRow++
            if (direction == '<')
                nextColumn--
            if (direction == '^')
                nextRow--
            if (nextRow < 0 || nextColumn < 0 || nextRow >= matrix.size || nextColumn >= matrix[0].size) {
                matrix[row][column] = 'X'
                return countSteps(matrix)
            }
            if (matrix[nextRow][nextColumn] == '#') {
                direction = turn(direction)
            } else {
                matrix[row][column] = 'X'
                row = nextRow
                column = nextColumn
            }
        }
        return 0
    }

    private fun countSteps(matrix: List<List<Char>>): Int {
        var steps = 0
        var row = 0
        while (row < matrix.size) {
            var column = 0
            while (column < matrix[0].size) {
                if (matrix[row][column] == 'X') {
                    steps++
                }
                column++
            }
            row++
        }
        return steps
    }

    private fun findDirection(matrix: List<List<Char>>): Pair<Char, Pair<Int, Int>> {
        var row = 0
        while (row < matrix.size) {
            var column = 0
            while (column < matrix[0].size) {
                if (matrix[row][column] != '#' && matrix[row][column] != '.') {
                    return Pair(matrix[row][column], Pair(row, column))
                }
                column++
            }
            row++
        }
        return Pair('^', Pair(0, 0))
    }

    private fun turn(direction: Char): Char {
        if (direction == '>')
            return 'v'
        if (direction == 'v')
            return '<'
        if (direction == '<')
            return '^'
        return '>'
    }

    fun partTwo(fileName: String): Int {
        val lines = getLines(fileName)
        val matrix = toCharMatrix(lines)
        val start = findDirection(matrix)
        var acc = 0
        var row = 0
        while (row < matrix.size) {
            var column = 0
            while (column < matrix[0].size) {
                if (matrix[row][column] == '.') {
                    matrix[row][column] = 'O'
                    if (foo(matrix, start)) {
                        acc++
                    }
                    matrix[row][column] = '.'
                }
                column++
            }
            row++
        }
        return acc
    }

    private fun foo(matrix: List<List<Char>>, start: Pair<Char, Pair<Int, Int>>): Boolean {
        var direction = start.first
        var row = start.second.first
        var column = start.second.second
        var steps = 0
        while (true) {
            var nextRow = row
            var nextColumn = column
            if (direction == '>')
                nextColumn++
            if (direction == 'v')
                nextRow++
            if (direction == '<')
                nextColumn--
            if (direction == '^')
                nextRow--
            if (nextRow < 0 || nextColumn < 0 || nextRow >= matrix.size || nextColumn >= matrix[0].size) {
                return false
            }
            if (matrix[nextRow][nextColumn] == '#' || matrix[nextRow][nextColumn] == 'O') {
                direction = turn(direction)
            } else {
                row = nextRow
                column = nextColumn
                steps++
            }
            if (row == start.second.first && column == start.second.second && direction == start.first) {
                return true
            }
            if (steps > matrix.size * matrix[0].size) {
                return true
            }
        }
    }

    private fun toCharMatrix(strings: List<String>): MutableList<MutableList<Char>> {
        val maxLength = strings.maxOf { it.length }
        return strings.map { it.padEnd(maxLength, ' ').toMutableList() }.toMutableList()
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
