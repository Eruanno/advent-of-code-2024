package org.eruanno

import java.io.File

class Day15 {

    fun partOne(fileName: String): Int {
        val parsedFile = parseFile(fileName)
        val map = parsedFile.first
        val moves = parsedFile.second
        var robot = Pair(0, 0)
        var i = 0
        while (i < map.size) {
            var j = 0
            while (j < map[0].size) {
                if (map[i][j] == '@') {
                    robot = Pair(i, j)
                }
                j++
            }
            i++
        }
        for (move in moves) {
            if (move == '^') {
                if (map[robot.first - 1][robot.second] == '.') {
                    map[robot.first][robot.second] = '.'
                    map[robot.first - 1][robot.second] = '@'
                    robot = Pair(robot.first - 1, robot.second)
                } else if (map[robot.first - 1][robot.second] == 'O') {
                    var k = 1
                    while (robot.first - k > 0) {
                        if (map[robot.first - k][robot.second] == '.') {
                            map[robot.first][robot.second] = '.'
                            map[robot.first - 1][robot.second] = '@'
                            map[robot.first - k][robot.second] = 'O'
                            robot = Pair(robot.first - 1, robot.second)
                            break
                        } else if (map[robot.first - k][robot.second] == '#') {
                            break
                        }
                        k++
                    }
                }
            } else if (move == '>') {
                if (map[robot.first][robot.second + 1] == '.') {
                    map[robot.first][robot.second] = '.'
                    map[robot.first][robot.second + 1] = '@'
                    robot = Pair(robot.first, robot.second + 1)
                } else if (map[robot.first][robot.second + 1] == 'O') {
                    var k = 1
                    while (robot.second + k < map[0].size) {
                        if (map[robot.first][robot.second + k] == '.') {
                            map[robot.first][robot.second] = '.'
                            map[robot.first][robot.second + 1] = '@'
                            map[robot.first][robot.second + k] = 'O'
                            robot = Pair(robot.first, robot.second + 1)
                            break
                        } else if (map[robot.first][robot.second + k] == '#') {
                            break
                        }
                        k++
                    }
                }
            } else if (move == 'v') {
                if (map[robot.first + 1][robot.second] == '.') {
                    map[robot.first][robot.second] = '.'
                    map[robot.first + 1][robot.second] = '@'
                    robot = Pair(robot.first + 1, robot.second)
                } else if (map[robot.first + 1][robot.second] == 'O') {
                    var k = 1
                    while (robot.first + k < map.size) {
                        if (map[robot.first + k][robot.second] == '.') {
                            map[robot.first][robot.second] = '.'
                            map[robot.first + 1][robot.second] = '@'
                            map[robot.first + k][robot.second] = 'O'
                            robot = Pair(robot.first + 1, robot.second)
                            break
                        } else if (map[robot.first + k][robot.second] == '#') {
                            break
                        }
                        k++
                    }
                }
            } else if (move == '<') {
                if (map[robot.first][robot.second - 1] == '.') {
                    map[robot.first][robot.second] = '.'
                    map[robot.first][robot.second - 1] = '@'
                    robot = Pair(robot.first, robot.second - 1)
                } else if (map[robot.first][robot.second - 1] == 'O') {
                    var k = 1
                    while (robot.second + k > 0) {
                        if (map[robot.first][robot.second - k] == '.') {
                            map[robot.first][robot.second] = '.'
                            map[robot.first][robot.second - 1] = '@'
                            map[robot.first][robot.second - k] = 'O'
                            robot = Pair(robot.first, robot.second - 1)
                            break
                        } else if (map[robot.first][robot.second - k] == '#') {
                            break
                        }
                        k++
                    }
                }
            }
            //println(move)
            //map.forEach { row -> println(row.joinToString("")) }
        }
        var gps = 0
        i = 0
        while (i < map.size) {
            var j = 0
            while (j < map[0].size) {
                if (map[i][j] == 'O') {
                    gps += 100 * i + j
                }
                j++
            }
            i++
        }
        return gps
    }

    fun partTwo(fileName: String): Int {
        val parsedFile = parseFile(fileName)
        val map = transformMap(parsedFile.first)
        val moves = parsedFile.second
        var robot = Pair(0, 0)
        var i = 0
        while (i < map.size) {
            var j = 0
            while (j < map[0].size) {
                if (map[i][j] == '@') {
                    robot = Pair(i, j)
                }
                j++
            }
            i++
        }
        map.forEach { row -> println(row.joinToString("")) }
        for (move in moves) {
            if (move == '^') {
                if (map[robot.first - 1][robot.second] == '.') {
                    map[robot.first][robot.second] = '.'
                    map[robot.first - 1][robot.second] = '@'
                    robot = Pair(robot.first - 1, robot.second)
                } else if (map[robot.first - 1][robot.second] == '[' || map[robot.first - 1][robot.second] == ']') {
                    if (checkUp(robot.first, robot.second, map)) {
                        moveUp(robot.first, robot.second, map)
                        map[robot.first - 1][robot.second] = '@'
                        map[robot.first][robot.second] = '.'
                        robot = Pair(robot.first - 1, robot.second)
                    }
                }
            } else if (move == '>') {
                if (map[robot.first][robot.second + 1] == '.') {
                    map[robot.first][robot.second] = '.'
                    map[robot.first][robot.second + 1] = '@'
                    robot = Pair(robot.first, robot.second + 1)
                } else if (map[robot.first][robot.second + 1] == '[' || map[robot.first][robot.second + 1] == ']') {
                    var k = 1
                    while (robot.second + k < map[0].size) {
                        if (map[robot.first][robot.second + k] == '.') {
                            var l = k
                            while (l > 1) {
                                map[robot.first][robot.second + l] = map[robot.first][robot.second + l - 1]
                                l--
                            }
                            map[robot.first][robot.second + 1] = '@'
                            map[robot.first][robot.second] = '.'
                            robot = Pair(robot.first, robot.second + 1)
                            break
                        } else if (map[robot.first][robot.second + k] == '#') {
                            break
                        }
                        k++
                    }
                }
            } else if (move == 'v') {
                if (map[robot.first + 1][robot.second] == '.') {
                    map[robot.first][robot.second] = '.'
                    map[robot.first + 1][robot.second] = '@'
                    robot = Pair(robot.first + 1, robot.second)
                } else if (map[robot.first + 1][robot.second] == '[' || map[robot.first + 1][robot.second] == ']') {
                    if (checkDown(robot.first, robot.second, map)) {
                        moveDown(robot.first, robot.second, map)
                        map[robot.first + 1][robot.second] = '@'
                        map[robot.first][robot.second] = '.'
                        robot = Pair(robot.first + 1, robot.second)
                    }
                }
            } else if (move == '<') {
                if (map[robot.first][robot.second - 1] == '.') {
                    map[robot.first][robot.second] = '.'
                    map[robot.first][robot.second - 1] = '@'
                    robot = Pair(robot.first, robot.second - 1)
                } else if (map[robot.first][robot.second - 1] == '[' || map[robot.first][robot.second - 1] == ']') {
                    var k = 1
                    while (robot.second + k > 0) {
                        if (map[robot.first][robot.second - k] == '.') {
                            var l = k
                            while (l > 1) {
                                map[robot.first][robot.second - l] = map[robot.first][robot.second - l + 1]
                                l--
                            }
                            map[robot.first][robot.second - 1] = '@'
                            map[robot.first][robot.second] = '.'
                            robot = Pair(robot.first, robot.second - 1)
                            break
                        } else if (map[robot.first][robot.second - k] == '#') {
                            break
                        }
                        k++
                    }
                }
            }
            //println(move)
        }
        map.forEach { row -> println(row.joinToString("")) }
        var gps = 0
        i = 0
        while (i < map.size) {
            var j = 0
            while (j < map[0].size) {
                if (map[i][j] == '[') {
                    gps += 100 * i + j
                }
                j++
            }
            i++
        }
        return gps
    }

    fun checkUp(row: Int, column: Int, map: Array<Array<Char>>): Boolean {
        if (map[row - 1][column] == '[' && map[row - 2][column] == '.' && map[row - 2][column + 1] == '.') {
            return true
        }
        if (map[row - 1][column] == ']' && map[row - 2][column - 1] == '.' && map[row - 2][column] == '.') {
            return true
        }
        if (map[row - 1][column] == '[' && (map[row - 2][column] == '#' || map[row - 2][column + 1] == '#')) {
            return false
        }
        if (map[row - 1][column] == ']' && (map[row - 2][column - 1] == '#' || map[row - 2][column] == '#')) {
            return false
        }
        if (map[row - 1][column] == '[') {
            return checkUp(row - 1, column, map) && checkUp(row - 1, column + 1, map)
        }
        if (map[row - 1][column] == ']') {
            return checkUp(row - 1, column - 1, map) && checkUp(row - 1, column, map)
        }
        return true
    }

    fun moveUp(row: Int, column: Int, map: Array<Array<Char>>) {
        if (map[row - 1][column] == '[' && map[row - 2][column] == '.' && map[row - 2][column + 1] == '.') {
            map[row - 2][column] = '['
            map[row - 2][column + 1] = ']'
            map[row - 1][column] = '.'
            map[row - 1][column + 1] = '.'
            return
        }
        if (map[row - 1][column] == ']' && map[row - 2][column - 1] == '.' && map[row - 2][column] == '.') {
            map[row - 2][column - 1] = '['
            map[row - 2][column] = ']'
            map[row - 1][column - 1] = '.'
            map[row - 1][column] = '.'
            return
        }
        if (map[row - 1][column] == '[') {
            moveUp(row - 1, column, map)
            moveUp(row - 1, column + 1, map)
            map[row - 2][column] = '['
            map[row - 2][column + 1] = ']'
            map[row - 1][column] = '.'
            map[row - 1][column + 1] = '.'
            return
        }
        if (map[row - 1][column] == ']') {
            moveUp(row - 1, column - 1, map)
            moveUp(row - 1, column, map)
            map[row - 2][column - 1] = '['
            map[row - 2][column] = ']'
            map[row - 1][column - 1] = '.'
            map[row - 1][column] = '.'
            return
        }
    }

    fun checkDown(row: Int, column: Int, map: Array<Array<Char>>): Boolean {
        if (map[row + 1][column] == '[' && map[row + 2][column] == '.' && map[row + 2][column + 1] == '.') {
            return true
        }
        if (map[row + 1][column] == ']' && map[row + 2][column - 1] == '.' && map[row + 2][column] == '.') {
            return true
        }
        if (map[row + 1][column] == '[' && (map[row + 2][column] == '#' || map[row + 2][column + 1] == '#')) {
            return false
        }
        if (map[row + 1][column] == ']' && (map[row + 2][column - 1] == '#' || map[row + 2][column] == '#')) {
            return false
        }
        if (map[row + 1][column] == '[') {
            return checkDown(row + 1, column, map) && checkDown(row + 1, column + 1, map)
        }
        if (map[row + 1][column] == ']') {
            return checkDown(row + 1, column - 1, map) && checkDown(row + 1, column, map)
        }
        return true
    }

    fun moveDown(row: Int, column: Int, map: Array<Array<Char>>) {
        if (map[row + 1][column] == '[' && map[row + 2][column] == '.' && map[row + 2][column + 1] == '.') {
            map[row + 2][column] = '['
            map[row + 2][column + 1] = ']'
            map[row + 1][column] = '.'
            map[row + 1][column + 1] = '.'
            return
        }
        if (map[row + 1][column] == ']' && map[row + 2][column - 1] == '.' && map[row + 2][column] == '.') {
            map[row + 2][column - 1] = '['
            map[row + 2][column] = ']'
            map[row + 1][column - 1] = '.'
            map[row + 1][column] = '.'
            return
        }
        if (map[row + 1][column] == '[') {
            moveDown(row + 1, column, map)
            moveDown(row + 1, column + 1, map)
            map[row + 2][column] = '['
            map[row + 2][column + 1] = ']'
            map[row + 1][column] = '.'
            map[row + 1][column + 1] = '.'
            return
        }
        if (map[row + 1][column] == ']') {
            moveDown(row + 1, column - 1, map)
            moveDown(row + 1, column, map)
            map[row + 2][column - 1] = '['
            map[row + 2][column] = ']'
            map[row + 1][column - 1] = '.'
            map[row + 1][column] = '.'
            return
        }
    }

    fun transformMap(inputMatrix: Array<Array<Char>>): Array<Array<Char>> {
        val outputMatrix = Array(inputMatrix.size) { Array(inputMatrix[0].size * 2) { ' ' } }
        var i = 0
        while (i < inputMatrix.size) {
            var j = 0
            while (j < inputMatrix[i].size) {
                if (inputMatrix[i][j] == '#') {
                    outputMatrix[i][j * 2] = '#'
                    outputMatrix[i][j * 2 + 1] = '#'
                } else if (inputMatrix[i][j] == 'O') {
                    outputMatrix[i][j * 2] = '['
                    outputMatrix[i][j * 2 + 1] = ']'
                } else if (inputMatrix[i][j] == '.') {
                    outputMatrix[i][j * 2] = '.'
                    outputMatrix[i][j * 2 + 1] = '.'
                } else if (inputMatrix[i][j] == '@') {
                    outputMatrix[i][j * 2] = '@'
                    outputMatrix[i][j * 2 + 1] = '.'
                }
                j++
            }
            i++
        }
        return outputMatrix
    }

    private fun parseFile(fileName: String): Pair<Array<Array<Char>>, String> {
        val lines = getLines(fileName)

        val matrix = lines.takeWhile { it.contains("#") }
            .map { it.toCharArray().toTypedArray() }
            .toTypedArray()

        val stringSection = lines.dropWhile { it.contains("#") }
            .joinToString("")

        return Pair(matrix, stringSection)
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
