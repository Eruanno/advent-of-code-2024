package org.eruanno

import java.io.File
import java.util.*
import kotlin.math.abs

class Day18 {

    fun partOne(fileName: String, time: Int, dimension: Int): Int {
        val bytes = getLines(fileName).map { it.split(",") }.map { Pair(it[0].toInt(), it[1].toInt()) }.toList()
        return aStar(bytes.take(time), dimension)
    }

    fun partTwo(fileName: String, dimension: Int): String {
        val bytes = getLines(fileName).map { it.split(",") }.map { Pair(it[0].toInt(), it[1].toInt()) }.toList()
        bytes.indices.forEach { time ->
            val result = aStar(bytes.take(time), dimension)
            if (result == -1) {
                return "${bytes[time - 1].first},${bytes[time - 1].second}"
            }
        }
        return ""
    }

    private fun aStar(bytes: List<Pair<Int, Int>>, size: Int): Int {
        val start = Pair(0, 0)
        val goal = Pair(size, size)
        val cameFrom = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
        val gScore = mutableMapOf(start to 0)
        val fScore = mutableMapOf(start to heuristic(start, size))
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

            if (current.first == size && current.second == size) {
                return gScore[goal]!!
            }

            for (direction in directions) {
                val neighbour = Pair(current.first + direction.first, current.second + direction.second)
                if (neighbour.first in 0..size && neighbour.second in 0..size && !bytes.contains(neighbour)) {
                    val tentativeGScore = gScore[current]!! + 1
                    if (tentativeGScore < gScore.getOrDefault(neighbour, Int.MAX_VALUE)) {
                        cameFrom[neighbour] = current
                        gScore[neighbour] = tentativeGScore
                        fScore[neighbour] = tentativeGScore + heuristic(goal, size)
                        if (!toVisit.contains(neighbour)) {
                            toVisit.add(neighbour)
                        }
                    }
                }
            }
        }

        return -1
    }

    private fun heuristic(current: Pair<Int, Int>, end: Int): Int {
        return abs(current.first - end) + abs(current.second - end)
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
