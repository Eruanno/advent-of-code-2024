package org.eruanno

import java.io.File
import java.util.*

class Day16 {

    fun partOne(fileName: String): Int {
        val maze = toCharMatrix(getLines(fileName))
        val visited = mutableMapOf<Pair<Int, Int>, Pair<Int, Char>>()
        val toVisit = LinkedList<Pair<Pair<Int, Int>, Pair<Int, Char>>>()
        var i = 0
        while (i < maze.size) {
            var j = 0
            while (j < maze[0].size) {
                if (maze[i][j] == 'S') {
                    toVisit.add(Pair(Pair(i, j), Pair(0, '>')))
                }
                j++
            }
            i++
        }
        var result = Int.MAX_VALUE
        while (true) {
            val pole = toVisit.poll()
            if (pole == null) {
                break
            }
            if (maze[pole.first.first][pole.first.second] == 'E') {
                if (pole.second.first < result) {
                    result = pole.second.first
                }
            }
            if (visited.contains(pole.first)) {
                if (visited[pole.first]!!.first < pole.second.first) {
                    continue
                }
            }
            visited[pole.first] = pole.second
            if (maze[pole.first.first][pole.first.second + 1] != '#') {
                val resolved = resolve(pole.second.second, '>')
                toVisit.add(
                    Pair(
                        Pair(pole.first.first, pole.first.second + 1),
                        Pair(pole.second.first + resolved, '>')
                    )
                )
            }
            if (maze[pole.first.first + 1][pole.first.second] != '#') {
                val resolved = resolve(pole.second.second, 'v')
                toVisit.add(
                    Pair(
                        Pair(pole.first.first + 1, pole.first.second),
                        Pair(pole.second.first + resolved, 'v')
                    )
                )
            }
            if (maze[pole.first.first][pole.first.second - 1] != '#') {
                val resolved = resolve(pole.second.second, '<')
                toVisit.add(
                    Pair(
                        Pair(pole.first.first, pole.first.second - 1),
                        Pair(pole.second.first + resolved, '<')
                    )
                )
            }
            if (maze[pole.first.first - 1][pole.first.second] != '#') {
                val resolved = resolve(pole.second.second, '^')
                toVisit.add(
                    Pair(
                        Pair(pole.first.first - 1, pole.first.second),
                        Pair(pole.second.first + resolved, '^')
                    )
                )
            }
        }
        return result
    }

    fun resolve(direction: Char, next: Char): Int {
        if (direction == '>') {
            if (next == '>') {
                return 1
            }
            if (next == 'v') {
                return 1001
            }
            if (next == '<') {
                return 2001
            }
            if (next == '^') {
                return 1001
            }
        }
        if (direction == 'v') {
            if (next == '>') {
                return 1001
            }
            if (next == 'v') {
                return 1
            }
            if (next == '<') {
                return 1001
            }
            if (next == '^') {
                return 2001
            }
        }
        if (direction == '<') {
            if (next == '>') {
                return 2001
            }
            if (next == 'v') {
                return 1001
            }
            if (next == '<') {
                return 1
            }
            if (next == '^') {
                return 1001
            }
        }
        if (direction == '^') {
            if (next == '>') {
                return 1001
            }
            if (next == 'v') {
                return 2001
            }
            if (next == '<') {
                return 1001
            }
            if (next == '^') {
                return 1
            }
        }
        return 0
    }

    fun partTwo(fileName: String): Int {
        val maze = toCharMatrix(getLines(fileName))
        val visited = mutableMapOf<Pair<Int, Int>, Pair<Int, Char>>()
        val toVisit = LinkedList<Pair<Pair<Int, Int>, Pair<Int, Char>>>()
        var end = Pair(0, 0)
        var i = 0
        while (i < maze.size) {
            var j = 0
            while (j < maze[0].size) {
                if (maze[i][j] == 'S') {
                    toVisit.add(Pair(Pair(i, j), Pair(0, '>')))
                }
                if (maze[i][j] == 'E') {
                    end = Pair(i, j)
                }
                j++
            }
            i++
        }
        var result = Int.MAX_VALUE
        while (true) {
            val pole = toVisit.poll()
            if (pole == null) {
                break
            }
            if (maze[pole.first.first][pole.first.second] == 'E') {
                if (pole.second.first < result) {
                    result = pole.second.first
                }
            }
            if (visited.contains(pole.first)) {
                if (visited[pole.first]!!.first < pole.second.first) {
                    continue
                }
            }
            visited[pole.first] = pole.second
            if (maze[pole.first.first][pole.first.second + 1] != '#') {
                val resolved = resolve(pole.second.second, '>')
                toVisit.add(
                    Pair(
                        Pair(pole.first.first, pole.first.second + 1),
                        Pair(pole.second.first + resolved, '>')
                    )
                )
            }
            if (maze[pole.first.first + 1][pole.first.second] != '#') {
                val resolved = resolve(pole.second.second, 'v')
                toVisit.add(
                    Pair(
                        Pair(pole.first.first + 1, pole.first.second),
                        Pair(pole.second.first + resolved, 'v')
                    )
                )
            }
            if (maze[pole.first.first][pole.first.second - 1] != '#') {
                val resolved = resolve(pole.second.second, '<')
                toVisit.add(
                    Pair(
                        Pair(pole.first.first, pole.first.second - 1),
                        Pair(pole.second.first + resolved, '<')
                    )
                )
            }
            if (maze[pole.first.first - 1][pole.first.second] != '#') {
                val resolved = resolve(pole.second.second, '^')
                toVisit.add(
                    Pair(
                        Pair(pole.first.first - 1, pole.first.second),
                        Pair(pole.second.first + resolved, '^')
                    )
                )
            }
        }
        while (true) {
            var toCheck = mutableSetOf<Pair<Int, Int>>()
            for ((key, value) in visited) {
                if (key.first == end.first && key.second - 1 == end.second && value.second == '>') {
                    toCheck.add(key)
                }
                if (key.first - 1 == end.first && key.second == end.second && value.second == 'v') {
                    toCheck.add(key)
                }
                if (key.first == end.first && key.second + 1 == end.second && value.second == '<') {
                    toCheck.add(key)
                }
                if (key.first + 1 == end.first && key.second == end.second && value.second == '^') {
                    toCheck.add(key)
                }
            }
        }
        return result
    }

    private fun toCharMatrix(strings: List<String>): MutableList<MutableList<Char>> {
        val maxLength = strings.maxOf { it.length }
        return strings.map { it.padEnd(maxLength, ' ').toMutableList() }.toMutableList()
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
