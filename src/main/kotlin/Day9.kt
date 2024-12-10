package org.eruanno

import java.io.File

class Day9 {

    fun partOne(fileName: String): Long {
        val discMap = getLines(fileName).first()
        val printedDiscMap = printDiscMap(discMap)
        val compactedDisc = StringBuilder()
        var leftIndex = 0
        var rightIndex = discMap.length - 1
        var isFile = true
        var leftFileId = 0
        var rightFileId = (discMap.length - 1) / 2
        var rightFileSize = 0
        var emptySpaceSize = 0
        while (leftIndex < rightIndex && leftFileId != rightFileId) {
            if (isFile) {
                var leftFileSize = discMap[leftIndex].digitToInt()
                while (leftFileSize > 0) {
                    compactedDisc.append(intToChar(leftFileId))
                    leftFileSize--
                }
                leftIndex++
                leftFileId++
                isFile = false
            } else {
                if (emptySpaceSize == 0) {
                    emptySpaceSize = discMap[leftIndex].digitToInt()
                    leftIndex++
                }
                if (rightFileSize == 0) {
                    rightFileSize = discMap[rightIndex].digitToInt()
                    rightIndex -= 2
                }
                while (emptySpaceSize > 0 && rightFileSize > 0) {
                    compactedDisc.append(intToChar(rightFileId))
                    emptySpaceSize--
                    rightFileSize--
                }
                if (emptySpaceSize == 0) {
                    isFile = true
                }
                if (rightFileSize == 0) {
                    rightFileId--
                }
            }
        }
        //printDisc(compactedDisc)
        if (leftFileId == rightFileId) {
            var fileSize = discMap[leftIndex + 1].digitToInt()
            while (fileSize > 0) {
                compactedDisc.append(intToChar(leftFileId))
                fileSize--
            }
        } else {
            if (isFile) {
                var fileSize = discMap[leftIndex].digitToInt()
                while (fileSize > 0) {
                    compactedDisc.append(intToChar(leftFileId))
                    fileSize--
                }
            }
            //printDisc(compactedDisc)
            if (rightFileSize > 0) {
                while (rightFileSize > 0) {
                    compactedDisc.append(intToChar(rightFileId))
                    rightFileSize--
                }
            }
        }
        printDisc(compactedDisc)
        var checksum = 0L
        var i = 0
        while (i < compactedDisc.length) {
            checksum += compactedDisc[i].code * i
            i++
        }
        return foo(printedDiscMap)
    }

    private fun foo(disc: String): Long {
        var leftIndex = 0;
        var rightIndex = disc.length - 1
        var result = ""
        while (leftIndex <= rightIndex) {
            if (disc[leftIndex] == '.') {
                while (disc[rightIndex] == '.') {
                    rightIndex--
                }
                if(leftIndex < rightIndex) {
                    result += disc[rightIndex]
                    rightIndex--
                }
            } else {
                result += disc[leftIndex]
            }
            leftIndex++
        }
        println("foo")
        printDisc(result)
        var checksum = 0L
        var i = 0
        while (i < result.length) {
            checksum += result[i].code * i
            i++
        }
        return checksum
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

    private fun printDisc(disc: StringBuilder): String {
        var i = 0
        var result = ""
        while (i < disc.length) {
            result += disc[i].code
            i++
        }
        println(result)
        return result
    }

    private fun printDiscMap(disc: String): String {
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
        //println(result)
        return result
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
