package org.eruanno

import java.io.File

class Day22 {

    fun partOne(fileName: String): Long {
        var secrets = getLines(fileName).stream().map { it.toLong() }.toList()
        repeat(2000) {
            secrets = secrets.stream().map { evolve(it) }.toList()
        }
        return secrets.sum()
    }

    private fun evolve(secret: Long): Long {
        val first = ((secret * 64) xor secret) % 16777216
        val second = ((first.floorDiv(32)) xor first) % 16777216
        val third = ((second * 2048) xor second) % 16777216
        return third
    }

    fun partTwo(fileName: String): Int {
        return -1
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
