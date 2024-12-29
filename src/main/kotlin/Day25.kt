package org.eruanno

import java.io.File

class Day25 {

    fun partOne(fileName: String): Int {
        val data = readFileToStruct(fileName)
        var acc = 0
        for (key in data.first) {
            for (lock in data.second) {
                var fit = true
                var i = 0
                while (i < key.size) {
                    if (key[i] + lock[i] > 5) {
                        fit = false
                    }
                    i++
                }
                if (fit) {
                    acc++
                }
            }
        }
        return acc
    }

    fun partTwo(fileName: String): Int {
        return -1
    }

    private fun readFileToStruct(fileName: String): Pair<List<List<Int>>, List<List<Int>>> {
        val keys = mutableListOf<List<Int>>()
        val locks = mutableListOf<List<Int>>()
        val lines = getLines(fileName)
        val currentList = mutableListOf<String>()

        for (line in lines) {
            if (line.isEmpty()) {
                if (currentList.isNotEmpty()) {
                    if (isKey(currentList)) {
                        keys.add(countHashesInColumns(currentList))
                    } else {
                        locks.add(countHashesInColumns(currentList))
                    }
                    currentList.clear()
                }
            } else {
                currentList.add(line)
            }
        }

        if (currentList.isNotEmpty()) {
            if (isKey(currentList)) {
                keys.add(countHashesInColumns(currentList))
            } else {
                locks.add(countHashesInColumns(currentList))
            }
        }

        return Pair(keys, locks)
    }

    private fun isKey(currentList: List<String>): Boolean {
        return currentList.isNotEmpty() && currentList.last().all { it == '#' }
    }

    private fun countHashesInColumns(currentList: List<String>): List<Int> {
        if (currentList.isEmpty()) return emptyList()

        val columnCount = currentList[0].length

        val hashCounts = MutableList(columnCount) { -1 }

        for (row in currentList) {
            for (col in 0 until columnCount) {
                if (row[col] == '#') {
                    hashCounts[col]++
                }
            }
        }

        return hashCounts
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
