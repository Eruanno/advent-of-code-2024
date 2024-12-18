package org.eruanno

import java.io.File

class Day17 {

    fun partOne(fileName: String): String {
        val input = parseFile(fileName)
        var a = input.registerA
        var b = input.registerB
        var c = input.registerC
        val output = arrayListOf<Long>()
        var i = 0
        while (i < input.program.size) {
            val instruction = input.program[i]
            val operand = input.program[i + 1]
            var value = 1L
            if (operand in 0..3) {
                value = operand
            } else if (operand == 4L) {
                value = a
            } else if (operand == 5L) {
                value = b
            } else if (operand == 6L) {
                value = c
            }
            if (instruction == 0L) {
                val nominator = a
                val denominator = 1L shl value.toInt()
                a = (nominator / denominator)
            } else if (instruction == 1L) {
                b = b xor operand
            } else if (instruction == 2L) {
                b = value % 8
            } else if (instruction == 3L) {
                if (a != 0L) {
                    i = value.toInt()
                    continue
                }
            } else if (instruction == 4L) {
                b = b xor c
            } else if (instruction == 5L) {
                output.add(value % 8L)
            } else if (instruction == 6L) {
                val nominator = a
                val denominator = 1L shl value.toInt()
                b = (nominator / denominator)
            } else if (instruction == 7L) {
                val nominator = a
                val denominator = 1L shl value.toInt()
                c = (nominator / denominator)
            }
            i += 2
        }
        return output.joinToString(",") { it.toString() }
    }

    fun partTwo(fileName: String): Long {
        val input = parseFile(fileName)
        var iteration = 0L
        while (true) {
            var a = iteration
            var b = input.registerB
            var c = input.registerC
            val output = arrayListOf<Long>()
            var i = 0
            while (i < input.program.size) {
                val instruction = input.program[i]
                val operand = input.program[i + 1]
                var value = 1L
                if (operand in 0..3) {
                    value = operand
                } else if (operand == 4L) {
                    value = a
                } else if (operand == 5L) {
                    value = b
                } else if (operand == 6L) {
                    value = c
                }
                if (instruction == 0L) {
                    val nominator = a
                    val denominator = 1L shl value.toInt()
                    a = (nominator / denominator)
                } else if (instruction == 1L) {
                    b = b xor operand
                } else if (instruction == 2L) {
                    b = value % 8
                } else if (instruction == 3L) {
                    if (a != 0L) {
                        i = value.toInt()
                        continue
                    }
                } else if (instruction == 4L) {
                    b = b xor c
                } else if (instruction == 5L) {
                    output.add(value % 8L)
                } else if (instruction == 6L) {
                    val nominator = a
                    val denominator = 1L shl value.toInt()
                    b = (nominator / denominator)
                } else if (instruction == 7L) {
                    val nominator = a
                    val denominator = 1L shl value.toInt()
                    c = (nominator / denominator)
                }
                i += 2
                if (input.program.size == output.size) {
                    break
                }
            }
            if (input.program.joinToString(",") { it.toString() } == output.joinToString(
                    ","
                ) { it.toString() }) {
                break
            }
            iteration++
        }
        return iteration
    }

    private fun parseFile(fileName: String): InputData {
        val lines = getLines(fileName)

        val registerA = lines[0].substringAfter(":").trim().toLong()
        val registerB = lines[1].substringAfter(":").trim().toLong()
        val registerC = lines[2].substringAfter(":").trim().toLong()

        val programLine = lines[4].substringAfter(":").trim()
        val program = programLine.split(",").map { it.toLong() }

        return InputData(registerA, registerB, registerC, program)
    }

    private data class InputData(
        val registerA: Long,
        val registerB: Long,
        val registerC: Long,
        val program: List<Long>
    )

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
