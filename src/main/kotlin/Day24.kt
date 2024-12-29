package org.eruanno

import java.io.File

class Day24 {

    fun partOne(fileName: String): Long {
        val data = readFileToStructures(fileName)
        val registers = data.first
        val operations = data.second
        while (operations.isNotEmpty()) {
            val iterator = operations.iterator()
            while (iterator.hasNext()) {
                val operation = iterator.next()
                if (operation.registerA in registers && operation.registerB in registers) {
                    val valueA = registers[operation.registerA] ?: 0
                    val valueB = registers[operation.registerB] ?: 0

                    when (operation.operation) {
                        "AND" -> registers[operation.resultRegister] = valueA and valueB
                        "OR" -> registers[operation.resultRegister] = valueA or valueB
                        "XOR" -> registers[operation.resultRegister] = valueA xor valueB
                    }

                    iterator.remove()
                }
            }
        }
        return registers.filter { it.key.startsWith('z') }.toSortedMap().map { "${it.value}" }.joinToString("").reversed().toLong(2)
    }

    fun partTwo(fileName: String): Int {
        return -1
    }

    private fun readFileToStructures(fileName: String): Pair<MutableMap<String, Int>, MutableList<Operation>> {
        val registerMap = mutableMapOf<String, Int>()
        val operations = mutableListOf<Operation>()

        getLines(fileName).stream().forEach { line ->
            when {
                line.contains(":") -> {
                    val (key, value) = line.split(":").map { it.trim() }
                    registerMap[key] = value.toInt()
                }

                line.contains("->") -> {
                    val parts = line.split("->").map { it.trim() }
                    val operationParts = parts[0].split(" ").map { it.trim() }

                    if (operationParts.size == 3) {
                        val (regA, op, regB) = operationParts
                        val resultReg = parts[1]
                        operations.add(Operation(regA, op, regB, resultReg))
                    }
                }
            }
        }

        return registerMap to operations
    }

    private data class Operation(val registerA: String, val operation: String, val registerB: String, val resultRegister: String)

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
