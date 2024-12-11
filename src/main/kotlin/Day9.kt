package org.eruanno

import java.io.File

class Day9 {

    fun partOne(fileName: String): Long {
        val discMap = getLines(fileName).first()
        val convertedDisc = convertDisc2(discMap)
        val compressedDisc = compressDisc2(convertedDisc)
        return calculateChecksum(compressedDisc)
    }

    private fun convertDisc(disc: String): String {
        var i = 0
        var result = ""
        var isFile = true
        var fileId = 0
        while (i < disc.length) {
            var j = 0
            while (j < disc[i].digitToInt()) {
                if (isFile) {
                    result += intToChar(fileId)
                } else {
                    result += "."
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

    private fun convertDisc2(disc: String): MutableList<Int> {
        var i = 0
        var result = mutableListOf<Int>()
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

    private fun compressDisc(disc: String): String {
        var leftIndex = 0;
        var rightIndex = disc.length - 1
        var result = ""
        var asdasda = mutableListOf<Int>()
        while (leftIndex <= rightIndex) {
            if (disc[leftIndex] == '.') {
                while (disc[rightIndex] == '.') {
                    rightIndex--
                }
                if (leftIndex < rightIndex) {
                    result += disc[rightIndex]
                    rightIndex--
                }
            } else {
                result += disc[leftIndex]
            }
            leftIndex++
        }
        return result
    }

    private fun compressDisc2(disc: MutableList<Int>): MutableList<Int> {
        var leftIndex = 0;
        var rightIndex = disc.size - 1
        var result = disc.toMutableList()
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
        println('.'.code)
        return result
    }

    private fun swapChars(input: String, index1: Int, index2: Int): String {
        val charArray = input.toCharArray()
        val temp = charArray[index1]
        charArray[index1] = charArray[index2]
        charArray[index2] = temp
        return String(charArray)
    }

    private fun calculateChecksum(disc: String): Long =
        disc.mapIndexed { index, char -> getCode(char.code) * index }.sumOf { it.toLong() }

    private fun calculateChecksum(disc: MutableList<Int>): Long =
        disc.mapIndexed { index, value -> if (value >= 0) value * index else 0 }.sumOf { it.toLong() }

    private fun getCode(code: Int): Int {
        if (code == 10001)
            return 41
        return code
    }

    private fun printDisc(disc: String): String {
        var i = 0
        var result = ""
        while (i < disc.length) {
            result += disc[i].code
            i++
        }
        println(result)
        return result
    }

    fun partTwo(fileName: String): Long {
        val discMap = getLines(fileName).first()
        val convertedDisc = convertDisc3(discMap)
        val compressedDisc = compressDisc3(convertedDisc)
        return -1
    }

    private fun convertDisc3(disc: String): MutableList<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        var isFile = true
        var fileId = 0
        var i = 0
        while (i < disc.length) {
            if (isFile) {
                result.add(fileId to disc[i].digitToInt())
                fileId++
            } else {
                result.add(-1 to disc[i].digitToInt())
            }
            isFile = !isFile
            i++
        }
        return result
    }

    private fun compressDisc3(disc: MutableList<Pair<Int, Int>>): MutableList<Pair<Int, Int>> {
        var leftIndex = 0;
        var rightIndex = disc.size - 1
        val result = disc.toMutableList()
        while (true) {
            while (result[leftIndex].first != -1) {
                leftIndex++
            }
            while (result[rightIndex].first == -1) {
                rightIndex--
            }
            if (leftIndex > rightIndex) {
                break
            }
        }
        println('.'.code)
        return result
    }

    private fun intToChar(value: Int): Char {
        if (value == 41)
            return (10001 + 0x0000).toChar()
        return (value + 0x0000).toChar()
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
