package org.eruanno

import java.io.File
import java.util.*
import kotlin.math.abs

class Day20 {

    fun partOne(fileName: String): Int {
        val maze = toCharMatrix(getLines(fileName))

        var start = Pair(0, 0)
        var end = Pair(0, 0)
        val obstacles = mutableListOf<Pair<Int, Int>>()
        for (rowIndex in maze.indices) {
            for (colIndex in maze[rowIndex].indices) {
                if (maze[rowIndex][colIndex] == '#') {
                    obstacles.add(Pair(rowIndex, colIndex))
                } else if (maze[rowIndex][colIndex] == 'S') {
                    start = Pair(rowIndex, colIndex)
                } else if (maze[rowIndex][colIndex] == 'E') {
                    end = Pair(rowIndex, colIndex)
                }
            }
        }
        val distance = aStar(obstacles, start, end, maze.size, maze[0].size)
        var acc = 0
        println("Distance: $distance")
        println(obstacles.size)
        for (i in obstacles.indices) {
            if (obstacles[i].first != 0 && obstacles[i].second != 0 && obstacles[i].first != maze.size && obstacles[i].second != maze[0].size) {
                val rest = obstacles.take(i) + obstacles.drop(i + 1)
                val cheated = aStar(rest, start, end, maze.size, maze[0].size)
                val difference = distance - cheated
                print("$i \t $cheated \t $difference")
                if (difference >= 100) {
                    acc++
                    print("\t $acc")
                }
                print("\n")
            }
        }
        return acc
    }

    fun partTwo(fileName: String): Int {
        return -1
    }

    private fun aStar(
        obstacles: List<Pair<Int, Int>>,
        start: Pair<Int, Int>,
        end: Pair<Int, Int>,
        length: Int,
        width: Int
    ): Int {
        val cameFrom = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
        val gScore = mutableMapOf(start to 0)
        val fScore = mutableMapOf(start to heuristic(start, end))
        val toVisit = PriorityQueue<Pair<Int, Int>>(compareBy { fScore.getOrDefault(it, Int.MAX_VALUE) })
        toVisit.add(start)

        val directions = listOf(
            Pair(-1, 0),
            Pair(1, 0),
            Pair(0, -1),
            Pair(0, 1)
        )

        while (toVisit.isNotEmpty()) {
            val current = toVisit.poll()

            if (current.first == end.first && current.second == end.second) {
                return gScore[end]!!
            }

            for (direction in directions) {
                val neighbour = Pair(current.first + direction.first, current.second + direction.second)
                if (neighbour.first in 0..length && neighbour.second in 0..width && !obstacles.contains(neighbour)) {
                    val tentativeGScore = gScore[current]!! + 1
                    if (tentativeGScore < gScore.getOrDefault(neighbour, Int.MAX_VALUE)) {
                        cameFrom[neighbour] = current
                        gScore[neighbour] = tentativeGScore
                        fScore[neighbour] = tentativeGScore + heuristic(neighbour, end)
                        if (!toVisit.contains(neighbour)) {
                            toVisit.add(neighbour)
                        }
                    }
                }
            }
        }

        return -1
    }

    private fun heuristic(current: Pair<Int, Int>, end: Pair<Int, Int>): Int {
        return abs(current.first - end.first) + abs(current.second - end.second)
    }

    private fun toCharMatrix(strings: List<String>): MutableList<MutableList<Char>> {
        val maxLength = strings.maxOf { it.length }
        return strings.map { it.padEnd(maxLength, ' ').toMutableList() }.toMutableList()
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
