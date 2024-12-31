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
                        Pair(pole.first.first, pole.first.second + 1), Pair(pole.second.first + resolved, '>')
                    )
                )
            }
            if (maze[pole.first.first + 1][pole.first.second] != '#') {
                val resolved = resolve(pole.second.second, 'v')
                toVisit.add(
                    Pair(
                        Pair(pole.first.first + 1, pole.first.second), Pair(pole.second.first + resolved, 'v')
                    )
                )
            }
            if (maze[pole.first.first][pole.first.second - 1] != '#') {
                val resolved = resolve(pole.second.second, '<')
                toVisit.add(
                    Pair(
                        Pair(pole.first.first, pole.first.second - 1), Pair(pole.second.first + resolved, '<')
                    )
                )
            }
            if (maze[pole.first.first - 1][pole.first.second] != '#') {
                val resolved = resolve(pole.second.second, '^')
                toVisit.add(
                    Pair(
                        Pair(pole.first.first - 1, pole.first.second), Pair(pole.second.first + resolved, '^')
                    )
                )
            }
        }
        return result
    }

    private fun resolve(direction: Char, next: Char): Int {
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
        val (start, end) = maze.flatMapIndexed { i, row ->
            row.mapIndexedNotNull { j, char ->
                when (char) {
                    'S' -> i to j
                    'E' -> i to j
                    else -> null
                }
            }
        }.let { positions ->
            positions.first { maze[it.first][it.second] == 'S' } to positions.first { maze[it.first][it.second] == 'E' }
        }
        val paths = findAllPathsInMaze(maze, start, end)
        val pathsWithCost = paths.map { calculatePathWithCost(it) }
        return pathsWithCost.filter { it.second == pathsWithCost.minOf { path -> path.second } }
            .flatMap { it.first }
            .toSet().size
    }

    private fun calculatePathWithCost(path: List<Pair<Int, Int>>): Pair<List<Pair<Int, Int>>, Int> {
        var direction = '>'
        var acc = 0
        for (i in 0 until path.size - 1) {
            var nextDirection = direction
            if (path[i].first == path[i + 1].first + 1) {
                nextDirection = 'v'
            } else if (path[i].first == path[i + 1].first - 1) {
                nextDirection = '^'
            } else if (path[i].second == path[i + 1].second + 1) {
                nextDirection = '>'
            } else if (path[i].second == path[i + 1].second - 1) {
                nextDirection = '<'
            }
            acc += resolve(direction, nextDirection)
            direction = nextDirection
        }
        return path to acc
    }

    private fun findAllPathsInMaze(maze: MutableList<MutableList<Char>>, start: Pair<Int, Int>, end: Pair<Int, Int>): List<List<Pair<Int, Int>>> {
        val allPaths = mutableListOf<List<Pair<Int, Int>>>()
        val currentPath = mutableListOf<Pair<Int, Int>>()
        val rows = maze.size
        val cols = maze[0].size

        fun dfs(x: Int, y: Int) {
            if (x < 0 || y < 0 || x >= rows || y >= cols || maze[x][y] == '#') return

            currentPath.add(Pair(x, y))

            if (x == end.first && y == end.second) {
                allPaths.add(currentPath.toList())
            } else {
                maze[x][y] = '#'

                dfs(x - 1, y)
                dfs(x + 1, y)
                dfs(x, y - 1)
                dfs(x, y + 1)

                maze[x][y] = '.'
            }

            currentPath.removeAt(currentPath.size - 1)
        }

        dfs(start.first, start.second)
        return allPaths
    }

    private fun toCharMatrix(strings: List<String>): MutableList<MutableList<Char>> {
        val maxLength = strings.maxOf { it.length }
        return strings.map { it.padEnd(maxLength, ' ').toMutableList() }.toMutableList()
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
