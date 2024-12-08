package org.eruanno

import java.io.File
import java.math.BigInteger

class Day7 {

    private enum class State {
        ADD, MULTIPLE, CONCATENATE
    }

    fun partOne(fileName: String): BigInteger {
        return getData(fileName)
            .filter { foo(it.first, it.second) }
            .map { it.first }
            .reduce { acc, number -> acc + number }
    }

    fun partTwo(fileName: String): BigInteger {
        return getData(fileName)
            .filter { combined(it.first, it.second) }
            .map { it.first }
            .reduce { acc, number -> acc + number }
    }

    private fun foo(calibrationResult: BigInteger, equations: List<BigInteger>): Boolean {
        val permutations = BooleanArray(equations.size - 1) { false }
        val maxIterations = 1 shl equations.size
        for (step in 0 until maxIterations) {
            for (permutation in permutations.indices) {
                permutations[permutation] = ((step shr (equations.size - permutation - 1)) and 1) == 1
            }
            var testValue = BigInteger.ZERO
            for (index in permutations.indices) {
                if (testValue == BigInteger.ZERO) {
                    if (permutations[index]) {
                        testValue = equations[index] + equations[index + 1]
                    } else {
                        testValue = equations[index] * equations[index + 1]
                    }
                } else {
                    if (permutations[index]) {
                        testValue += equations[index + 1]
                    } else {
                        testValue *= equations[index + 1]
                    }
                }
            }
            if (calibrationResult == testValue) {
                return true
            }
        }
        return false
    }

    private fun combined(result: BigInteger, numbers: List<BigInteger>): Boolean {
        return foo(result, numbers) || bar(result, numbers)
    }

    private fun bar(result: BigInteger, numbers: List<BigInteger>): Boolean {
        return generatePermutations(State.entries, numbers.size - 1).filter { withConcatenate(result, numbers, it) }
            .any()
    }

    private fun withConcatenate(result: BigInteger, numbers: List<BigInteger>, scheme: List<State>): Boolean {
        var acc = BigInteger.ZERO
        for (index in scheme.indices) {
            if (acc == BigInteger.ZERO) {
                if (scheme[index] == State.ADD) {
                    acc = numbers[index] + numbers[index + 1]
                } else if (scheme[index] == State.MULTIPLE) {
                    acc = numbers[index] * numbers[index + 1]
                } else if (scheme[index] == State.CONCATENATE) {
                    acc = BigInteger(numbers[index].toString() + numbers[index + 1].toString())
                }
            } else {
                if (scheme[index] == State.ADD) {
                    acc += numbers[index + 1]
                } else if (scheme[index] == State.MULTIPLE) {
                    acc *= numbers[index + 1]
                } else if (scheme[index] == State.CONCATENATE) {
                    acc = BigInteger(acc.toString() + numbers[index + 1].toString())
                }
            }
        }
        if (result == acc) {
            return true
        }
        return false
    }

    private fun generatePermutations(states: List<State>, length: Int): Sequence<List<State>> {
        if (length == 0) return sequenceOf(emptyList())
        return generatePermutations(states, length - 1).flatMap { perm ->
            states.asSequence().map { state -> perm + state }
        }
    }

    private fun getData(fileName: String): List<Pair<BigInteger, List<BigInteger>>> {
        return getLines(fileName)
            .map { line ->
                val (key, values) = line.split(":").map { it.trim() }
                val keyInt = BigInteger(key)
                val valuesList = values.split("\\s+".toRegex()).map { BigInteger(it) }
                keyInt to valuesList
            }
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
