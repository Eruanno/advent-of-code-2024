package org.eruanno

import java.io.File

class DayFour {

    fun partOne(fileName: String): Int {
        val lines = getLines(fileName)
        var acc = 0
        // Left to right.
        for (i in lines) {
            acc += i.split("XMAS").size - 1
            acc += i.split("SAMX").size - 1
        }
        // Up to down.
        val maxLength = lines.maxOf { it.length }
        for (columnIndex in 0 until maxLength) {
            val column = lines.mapNotNull { it.getOrNull(columnIndex)?.toString() }
            val columnString = column.joinToString("")
            acc += columnString.split("XMAS").size - 1
            acc += columnString.split("SAMX").size - 1
        }
        // Diagonal to right bottom
        val matrix = toCharMatrix(lines)
        var row = matrix.size - 1
        var column = 0
        while (row >= 0) {
            var iRow = row
            var iColumn = column
            while (iRow < matrix.size && iColumn < matrix[0].size) {
                if (matrix[iRow][iColumn] == 'X' && iRow + 3 < matrix.size && iColumn + 3 < matrix[0].size) {
                    if (matrix[iRow + 1][iColumn + 1] == 'M' && matrix[iRow + 2][iColumn + 2] == 'A' && matrix[iRow + 3][iColumn + 3] == 'S') {
                        acc++
                    }
                } else if (matrix[iRow][iColumn] == 'S' && iRow + 3 < matrix.size && iColumn + 3 < matrix[0].size) {
                    if (matrix[iRow + 1][iColumn + 1] == 'A' && matrix[iRow + 2][iColumn + 2] == 'M' && matrix[iRow + 3][iColumn + 3] == 'X') {
                        acc++
                    }
                }
                iRow++
                iColumn++
            }
            row--
        }
        row = 0
        column = 1
        while (column < matrix[0].size) {
            var iRow = row
            var iColumn = column
            while (iRow < matrix.size && iColumn < matrix[0].size) {
                if (matrix[iRow][iColumn] == 'X' && iRow + 3 < matrix.size && iColumn + 3 < matrix[0].size) {
                    if (matrix[iRow + 1][iColumn + 1] == 'M' && matrix[iRow + 2][iColumn + 2] == 'A' && matrix[iRow + 3][iColumn + 3] == 'S') {
                        acc++
                    }
                }
                if (matrix[iRow][iColumn] == 'S' && iRow + 3 < matrix.size && iColumn + 3 < matrix[0].size) {
                    if (matrix[iRow + 1][iColumn + 1] == 'A' && matrix[iRow + 2][iColumn + 2] == 'M' && matrix[iRow + 3][iColumn + 3] == 'X') {
                        acc++
                    }
                }
                iRow++
                iColumn++
            }
            column++
        }

        // Diagonal to left bottom
        row = matrix.size - 1
        column = matrix[0].size - 1
        while (row >= 0) {
            var iRow = row
            var iColumn = column
            while (iRow < matrix.size && iColumn > 0) {
                if (matrix[iRow][iColumn] == 'X' && iRow + 3 < matrix.size && iColumn - 3 >= 0) {
                    if (matrix[iRow + 1][iColumn - 1] == 'M' && matrix[iRow + 2][iColumn - 2] == 'A' && matrix[iRow + 3][iColumn - 3] == 'S') {
                        acc++
                    }
                } else if (matrix[iRow][iColumn] == 'S' && iRow + 3 < matrix.size && iColumn - 3 >= 0) {
                    if (matrix[iRow + 1][iColumn - 1] == 'A' && matrix[iRow + 2][iColumn - 2] == 'M' && matrix[iRow + 3][iColumn - 3] == 'X') {
                        acc++
                    }
                }
                iRow++
                iColumn--
            }
            row--
        }
        row = 0
        column = matrix[0].size - 2
        while (column >= 0) {
            var iRow = row
            var iColumn = column
            while (iRow < matrix.size && iColumn > 0) {
                if (matrix[iRow][iColumn] == 'X' && iRow + 3 < matrix.size && iColumn - 3 >= 0) {
                    if (matrix[iRow + 1][iColumn - 1] == 'M' && matrix[iRow + 2][iColumn - 2] == 'A' && matrix[iRow + 3][iColumn - 3] == 'S') {
                        acc++
                    }
                }
                if (matrix[iRow][iColumn] == 'S' && iRow + 3 < matrix.size && iColumn - 3 >= 0) {
                    if (matrix[iRow + 1][iColumn - 1] == 'A' && matrix[iRow + 2][iColumn - 2] == 'M' && matrix[iRow + 3][iColumn - 3] == 'X') {
                        acc++
                    }
                }
                iRow++
                iColumn--
            }
            column--
        }
        return acc
    }

    fun toCharMatrix(strings: List<String>): List<List<Char>> {
        val maxLength = strings.maxOf { it.length }
        return strings.map { it.padEnd(maxLength, ' ').toList() }
    }

    fun partTwo(fileName: String): Int {
        val lines = getLines(fileName)
        var acc = 0
        val matrix = toCharMatrix(lines)

        var row = 1
        while (row < matrix.size - 1) {
            var column = 1
            while (column < matrix[0].size - 1) {
                if (matrix[row][column] == 'A') {
                    if (((matrix[row - 1][column - 1] == 'M' && matrix[row + 1][column + 1] == 'S') || (matrix[row - 1][column - 1] == 'S' && matrix[row + 1][column + 1] == 'M'))
                        && ((matrix[row - 1][column + 1] == 'M' && matrix[row + 1][column - 1] == 'S') || (matrix[row - 1][column + 1] == 'S' && matrix[row + 1][column - 1] == 'M'))
                    ) {
                        acc++
                    }
                }
                column++
            }
            row++
        }
        return acc
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
