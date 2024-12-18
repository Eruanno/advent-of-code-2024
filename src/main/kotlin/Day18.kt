package org.eruanno

import java.io.File
import java.util.*
import kotlin.math.abs

class Day18 {

    fun partOne(fileName: String, bytes: Int, size: Int): Int {
        val memories = getLines(fileName).map { it.split(",") }.map { Pair(it[0].toInt(), it[1].toInt()) }.toList()
        return a(memories.take(bytes), Pair(0, 0), size)
    }

    private fun a(obstacles: List<Pair<Int, Int>>, start: Pair<Int, Int>, size: Int): Int {
        val goal = Pair(size, size)
        val cameFrom = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
        val gScore = mutableMapOf(start to 0)
        val fScore = mutableMapOf(start to h(start, goal))
        val openSet = PriorityQueue<Pair<Int, Int>>(compareBy { fScore.getOrDefault(it, Int.MAX_VALUE) })
        openSet.add(start)

        while (openSet.isNotEmpty()) {
            val current = openSet.poll()

            if (current == goal) {
                val path = reconstruct(cameFrom, current)
                return path.size - 1
            }

            val directions = listOf(
                Pair(-1, 0),
                Pair(1, 0),
                Pair(0, -1),
                Pair(0, 1)
            )

            for (direction in directions) {
                val neighbour = Pair(current.first + direction.first, current.second + direction.second)
                if (neighbour.first in 0..size && neighbour.second in 0..size && !obstacles.contains(neighbour)) {
                    val tentativeGScore = gScore[current]!! + 1
                    if (tentativeGScore < gScore.getOrDefault(neighbour, Int.MAX_VALUE)) {
                        cameFrom[neighbour] = current
                        gScore[neighbour] = tentativeGScore
                        fScore[neighbour] = tentativeGScore + h(goal, neighbour)
                        if (!openSet.contains(neighbour)) {
                            openSet.add(neighbour)
                        }
                    }
                }
            }
        }

        return -1
    }

    private fun h(start: Pair<Int, Int>, goal: Pair<Int, Int>): Int {
        return abs(start.first - goal.first) + abs(start.second - goal.second)
    }

    private fun reconstruct(
        cameFrom: MutableMap<Pair<Int, Int>, Pair<Int, Int>>,
        end: Pair<Int, Int>
    ): List<Pair<Int, Int>> {
        val totalPath = mutableListOf<Pair<Int, Int>>()
        var current = end
        while (cameFrom.containsKey(current)) {
            totalPath.add(current)
            current = cameFrom[current]!!
        }
        totalPath.add(end)
        return totalPath
    }

    fun partTwo(fileName: String, size: Int): String {
        val memories = getLines(fileName).map { it.split(",") }.map { Pair(it[0].toInt(), it[1].toInt()) }.toList()
        var n = 0
        while (n < memories.size) {
            val result = a(memories.take(n), Pair(0, 0), size)
            if (result == -1) {
                return memories[n - 1].first.toString() + "," + memories[n - 1].second.toString()
            }
            n++
        }
        return ""
    }

    private fun drawMemoryMap(obstacles: List<Pair<Int, Int>>, size: Int, path: List<Pair<Int, Int>> = listOf()) {
        val memorySpace = MutableList(size + 1) { MutableList(size + 1) { '.' } }
        obstacles.forEach { memorySpace[it.second][it.first] = '#' }
        path.forEach { memorySpace[it.second][it.first] = 'O' }
        memorySpace.forEach { println(it) }
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
