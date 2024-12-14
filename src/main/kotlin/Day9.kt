package org.eruanno

import java.io.File

class Day9 {

    fun partOne(fileName: String): Long {
        val discMap = getLines(fileName).first()
        val convertedDisc = convertDisc(discMap)
        val compressedDisc = compressDisc(convertedDisc)
        return calculateChecksum(compressedDisc)
    }

    private fun convertDisc(disc: String): MutableList<Int> {
        var i = 0
        val result = mutableListOf<Int>()
        var isFile = true
        var fileId = 0
        while (i < disc.length) {
            var j = 0
            while (j < disc[i].digitToInt()) {
                if (isFile) {
                    result.add(fileId)
                } else {
                    result.add(-1)
                }
                j++
            }
            if (isFile) {
                fileId++
            }
            isFile = !isFile
            i++
        }
        return result
    }

    private fun compressDisc(disc: MutableList<Int>): MutableList<Int> {
        var leftIndex = 0;
        var rightIndex = disc.size - 1
        val result = disc.toMutableList()
        while (true) {
            while (result[leftIndex] != -1) {
                leftIndex++
            }
            while (result[rightIndex] == -1) {
                rightIndex--
            }
            if (leftIndex > rightIndex) {
                break
            }
            val temp = result[leftIndex]
            result[leftIndex] = result[rightIndex]
            result[rightIndex] = temp
        }
        return result
    }

    private fun calculateChecksum(disc: MutableList<Int>): Long =
        disc.mapIndexed { index, value -> if (value >= 0) value * index else 0 }.sumOf { it.toLong() }

    fun partTwo(fileName: String): Long {
        val discMap = getLines(fileName).first()
        val convertedDisc = convertDiscToChunks(discMap)
        val compressedDisc = compressDiscWholeFiles(convertedDisc)
        return calculateChecksum2(compressedDisc)
    }

    private fun convertDiscToChunks(disc: String): List<Chunk> {
        var fileId = 0
        var isFile = true
        return disc.map { char ->
            val chunk = Chunk(isFile, char.digitToInt(), if (isFile) fileId++ else -1)
            isFile = !isFile
            chunk
        }
    }

    private fun compressDiscWholeFiles(disc: List<Chunk>): MutableList<Chunk> {
        var rightIndex = disc.size - 1
        val result = disc.toMutableList()
        while (true) {
            // Next element to move.
            while (!result[rightIndex].isFile) {
                rightIndex--
            }
            if (rightIndex == 0) {
                break
            }
            // Next big enough space to move.
            var leftIndex = 0;
            while (leftIndex < rightIndex) {
                val left = result[leftIndex]
                val right = result[rightIndex]
                if (!left.isFile && right.size <= left.size) {
                    break
                }
                leftIndex++
            }
            // Moving.
            if (leftIndex < rightIndex) {
                val file = result.removeAt(rightIndex)
                val free = result.removeAt(leftIndex)
                result.add(leftIndex, file)
                result.add(rightIndex, Chunk(false, file.size, -1))
                if (free.size > file.size) {
                    val rest = Chunk(false, free.size - file.size, -1)
                    result.add(leftIndex + 1, rest)
                }
            }
            rightIndex--
        }
        return result
    }

    private fun calculateChecksum2(disc: List<Chunk>): Long {
        var i = 0L
        return disc.sumOf { chunk ->
            (0 until chunk.size).sumOf {
                val value = if (chunk.isFile) chunk.id * i else 0L
                i++
                value
            }
        }
    }

    private data class Chunk(
        val isFile: Boolean,
        val size: Int,
        val id: Int
    )

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
