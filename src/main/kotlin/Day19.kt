package org.eruanno

import java.io.File

class Day19 {

    fun partOne(fileName: String): Int {
        val data = readFileToLists(fileName)
        return data.second.stream().filter { createTowel("", it, data.first) }.count().toInt()
    }

    private fun createTowel(test: String, towel: String, chunks: List<String>): Boolean {
        if (test.length > towel.length) {
            return false
        }
        if (test == towel) {
            return true
        }
        for (chunk in chunks) {
            if (test.length + chunk.length > towel.length) {
                continue
            }
            val sub = towel.substring(test.length, test.length + chunk.length)
            if (sub == chunk && createTowel(test + chunk, towel, chunks)) {
                return true
            }
        }
        return false
    }

    fun partTwo(fileName: String): Int {
        val data = readFileToLists(fileName)
        return data.second.take(1).stream().mapToInt { createTowelCount("", it, data.first) }.sum()
    }

    private fun createTowelCount(test: String, towel: String, chunks: List<String>): Int {
        if (test.length > towel.length) {
            return 0
        }
        if (test == towel) {
            return 1
        }
        var acc = 0
        for (chunk in chunks) {
            if (test.length + chunk.length > towel.length) {
                continue
            }
            val sub = towel.substring(test.length, test.length + chunk.length)
            if (sub == chunk) {
                acc += createTowelCount(test + chunk, towel, chunks)
            }
        }
        return acc
    }

    private fun readFileToLists(fileName: String): Pair<List<String>, List<String>> {
        val lines = getLines(fileName)

        val chunks = lines.first().split(",").map { it.trim() }

        val towels = lines.drop(2).map { it.trim() }

        return Pair(chunks, towels)
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
