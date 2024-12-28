package org.eruanno

import java.io.File

class Day21 {

    fun partOne(fileName: String): Int {
        val codes = getLines(fileName)
        var acc = 0
        for (code in codes) {
            println()
            var sequence = getTransition('A', code[0]) + "A"
            for (i in 0 until code.length - 1) {
                sequence += getTransition(code[i], code[i + 1]) + "A"
            }
            var firstRobotSequence = getTransitionArrow('A', sequence[0]) + "A"
            for (i in 0 until sequence.length - 1) {
                firstRobotSequence += getTransitionArrow(sequence[i], sequence[i + 1]) + "A"
            }
            var secondRobotSequence = getTransitionArrow('A', firstRobotSequence[0]) + "A"
            for (i in 0 until firstRobotSequence.length - 1) {
                secondRobotSequence += getTransitionArrow(firstRobotSequence[i], firstRobotSequence[i + 1]) + "A"
            }
            val num = code.substring(0, code.length - 1).toInt()
            val length = secondRobotSequence.length
            acc += length * num
            println(code)
            println(sequence)
            println(firstRobotSequence)
            println(secondRobotSequence)
            println("$num \t $length")
        }
        return acc
    }

    private fun getTransitionArrow(from: Char, to: Char): String {
        val transitions = mapOf(
            'A' to mapOf(
                '^' to "<",
                '>' to "v",
                'v' to "v<",
                '<' to "v<<"
            ),
            '^' to mapOf(
                'A' to ">",
                '>' to "v>",
                'v' to "v",
                '<' to "v<"
            ),
            '>' to mapOf(
                '^' to "^<",
                'A' to "^",
                'v' to "<",
                '<' to "<<"
            ),
            'v' to mapOf(
                '^' to "^",
                '>' to ">",
                'A' to ">^",
                '<' to "<"
            ),
            '<' to mapOf(
                '^' to ">^",
                '>' to ">>",
                'v' to ">",
                'A' to ">>^"
            )
        )

        return transitions[from]?.get(to) ?: ""
    }

    private fun getTransition(from: Char, to: Char): String {
        val transitions = mapOf(
            'A' to mapOf(
                '0' to "<",
                '1' to "^<<",
                '2' to "^<",
                '3' to "^",
                '4' to "^^<<",
                '5' to "^^<",
                '6' to "^^",
                '7' to "^^^<<",
                '8' to "^^^<",
                '9' to "^^^"
            ),
            '0' to mapOf(
                'A' to ">",
                '1' to "^<",
                '2' to "^",
                '3' to "^>",
                '4' to "^^<",
                '5' to "^^",
                '6' to "^^>",
                '7' to "^^^<",
                '8' to "^^^",
                '9' to "^^^>"
            ),
            '1' to mapOf(
                '0' to ">v",
                'A' to ">>v",
                '2' to ">",
                '3' to ">>",
                '4' to "^",
                '5' to "^>",
                '6' to "^>>",
                '7' to "^^",
                '8' to "^^>",
                '9' to "^^>>"
            ),
            '2' to mapOf(
                '0' to "v",
                '1' to "<",
                'A' to "v>",
                '3' to ">",
                '4' to "^<",
                '5' to "^",
                '6' to "^>",
                '7' to "^^<",
                '8' to "^^",
                '9' to "^^>"
            ),
            '3' to mapOf(
                '0' to "v<",
                '1' to "<<",
                '2' to "<",
                'A' to "v",
                '4' to "^<<",
                '5' to "^<",
                '6' to "^",
                '7' to "^^<<",
                '8' to "^^<",
                '9' to "^^"
            ),
            '4' to mapOf(
                '0' to ">vv",
                '1' to "v",
                '2' to "v>",
                '3' to "v>>",
                'A' to ">>vv",
                '5' to ">",
                '6' to ">>",
                '7' to "^",
                '8' to "^>",
                '9' to "^>>"
            ),
            '5' to mapOf(
                '0' to "vv",
                '1' to "v<",
                '2' to "v",
                '3' to "v>",
                '4' to "<",
                'A' to "vv>",
                '6' to ">",
                '7' to "^<",
                '8' to "^",
                '9' to "^>"
            ),
            '6' to mapOf(
                '0' to "vv<",
                '1' to "v<<",
                '2' to "v<",
                '3' to "v",
                '4' to "<<",
                '5' to "<",
                'A' to "vv",
                '7' to "^<<",
                '8' to "^<",
                '9' to "^"
            ),
            '7' to mapOf(
                '0' to ">vvv",
                '1' to "vv",
                '2' to "vv>",
                '3' to "vv>>",
                '4' to "v",
                '5' to "v>",
                '6' to "v>>",
                'A' to ">>vvv",
                '8' to ">",
                '9' to ">>"
            ),
            '8' to mapOf(
                '0' to "vvv",
                '1' to "vv<",
                '2' to "vv",
                '3' to "vv>",
                '4' to "v<",
                '5' to "v",
                '6' to "v>",
                '7' to "<",
                'A' to "vvv>",
                '9' to ">"
            ),
            '9' to mapOf(
                '0' to "vvv<",
                '1' to "vv<<",
                '2' to "vv<",
                '3' to "vv",
                '4' to "v<<",
                '5' to "v<",
                '6' to "v",
                '7' to "<<",
                '8' to "<",
                'A' to "vvv"
            )
        )

        return transitions[from]?.get(to) ?: ""
    }

    fun partTwo(fileName: String): Int {
        return -1
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
