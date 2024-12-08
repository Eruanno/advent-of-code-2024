package org.eruanno

import java.io.File
import java.util.Collections

class Day5 {

    fun partOne(fileName: String): Int {
        val (rules, updates) = readFileData(fileName)
        return updates.sumOf { checkUpdate(rules, it) }
    }

    fun partTwo(fileName: String): Int {
        val (rules, updates) = readFileData(fileName)
        return updates.filter { checkUpdate(rules, it) == 0 }.sumOf { reorderUpdate(rules, it) }
    }

    private fun checkUpdate(rules: Map<Int, List<Int>>, update: List<Int>): Int {
        var i = 0
        while (i < update.size - 1) {
            var j = i + 1
            while (j < update.size) {
                val values = rules.getOrDefault(update[i], mutableListOf())
                if (values.contains(update[j])) {
                    return 0
                }
                j++
            }
            i++
        }
        return update[update.size / 2]
    }

    private fun reorderUpdate(rules: Map<Int, List<Int>>, update: List<Int>): Int {
        var i = 0
        while (i < update.size - 1) {
            var j = i + 1
            while (j < update.size) {
                val values = rules.getOrDefault(update[i], mutableListOf())
                if (values.contains(update[j])) {
                    Collections.swap(update, i, j)
                    i = -1
                    j = update.size
                }
                j++
            }
            i++
        }
        return update[update.size / 2]
    }

    private fun readFileData(fileName: String): Pair<Map<Int, List<Int>>, List<List<Int>>> {
        val lines = getLines(fileName)

        val map = mutableMapOf<Int, MutableList<Int>>()
        val numbersList = mutableListOf<List<Int>>()
        var isReadingPairs = true

        for (line in lines) {
            if (line.isBlank()) {
                isReadingPairs = false
                continue
            }

            if (isReadingPairs) {
                val (key, value) = line.split("|")
                val keyInt = key.toInt()
                val valueInt = value.toInt()
                map.computeIfAbsent(valueInt) { mutableListOf() }.add(keyInt)
            } else {
                val numbers = line.split(",").map { it.toInt() }
                numbersList.add(numbers)
            }
        }

        return map.mapValues { it.value.toList() } to numbersList
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
