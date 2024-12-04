package org.eruanno

import java.io.File
import java.math.BigInteger

class DayThree {

    fun partOne(fileName: String): BigInteger {
        val filePath = this::class.java.getResource("/$fileName")?.path ?: return BigInteger.ZERO
        val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")
        val input = File(filePath).readLines().joinToString(prefix = "", postfix = "", separator = "")
        return regex.findAll(input).map { sum(it.groupValues[0]) }.reduce { acc, num -> acc + num }
    }

    fun partTwo(fileName: String): BigInteger {
        val filePath = this::class.java.getResource("/$fileName")?.path ?: return BigInteger.ZERO
        val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)||")
        val input = File(filePath).readLines().joinToString(prefix = "", postfix = "", separator = "")
        val commands = regex.findAll(input).map { it.groupValues[0] }.toList()
        var isEnabled = true
        var acc = BigInteger.ZERO
        for (i in commands) {
            if (i.isBlank())
                continue
            else if (i == "do()")
                isEnabled = true
            else if (i == "don't()")
                isEnabled = false
            else if (isEnabled)
                acc += sum(i)
        }
        return acc
    }

    private fun sum(input: String): BigInteger {
        val regex = Regex("\\d{1,3}")
        return multiply(regex.findAll(input).map { it.groupValues[0].toLong() }.toList())
    }

    private fun multiply(input: List<Long>): BigInteger {
        return BigInteger.valueOf(input[0]) * (BigInteger.valueOf(input[1]))
    }
}
