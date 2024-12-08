package org.eruanno

import java.io.File

class Day9 {

    fun partOne(fileName: String): Long {
        val discMap = getLines(fileName).first()
        val compactedDisc = StringBuilder()
        var start = 0
        var end = discMap.length - 1
        var isFile = true
        var fileId = 0
        var lastFileId = (discMap.length - 1) / 2
        var fileToMoveSize = 0
        var emptySpaceSize = 0
        while (start < end && fileId != lastFileId) {
            if (isFile) {
                var fileSize = discMap[start].digitToInt()
                while (fileSize > 0) {
                    compactedDisc.append(intToChar(fileId))
                    fileSize--
                }
                start++
                fileId++
                isFile = false
            } else {
                if (emptySpaceSize == 0) {
                    emptySpaceSize = discMap[start].digitToInt()
                    start++
                }
                if (fileToMoveSize == 0) {
                    fileToMoveSize = discMap[end].digitToInt()
                    end -= 2
                }
                while (emptySpaceSize > 0 && fileToMoveSize > 0) {
                    compactedDisc.append(intToChar(lastFileId))
                    emptySpaceSize--
                    fileToMoveSize--
                }
                if (emptySpaceSize == 0) {
                    isFile = true
                }
                if (fileToMoveSize == 0) {
                    lastFileId--
                }
            }
        }
        printDisc(compactedDisc)
        if(lastFileId != fileId) {
            if (isFile) {
                var fileSize = discMap[start].digitToInt()
                while (fileSize > 0) {
                    compactedDisc.append(intToChar(fileId))
                    fileSize--
                }
            }
            printDisc(compactedDisc)
            if (fileToMoveSize > 0) {
                while (fileToMoveSize > 0) {
                    compactedDisc.append(intToChar(lastFileId))
                    fileToMoveSize--
                }
            }
        } else {
            var fileSize = discMap[start+1].digitToInt()
            while (fileSize > 0) {
                compactedDisc.append(intToChar(fileId))
                fileSize--
            }
        }
        printDisc(compactedDisc)
        var checksum = 0L
        var i = 0
        while (i < compactedDisc.length) {
            checksum += compactedDisc[i].code * i
            i++
        }
        return checksum
    }

    private fun printDisc(disc: StringBuilder) {
        var i = 0
        while (i < disc.length) {
            print(disc[i].code)
            i++
        }
        println()
    }

    fun partTwo(fileName: String): Int {
        return -1
    }

    private fun intToChar(value: Int): Char {
        return (value + 0x0000).toChar()
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
