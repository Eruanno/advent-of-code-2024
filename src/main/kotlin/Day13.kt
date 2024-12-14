package org.eruanno

import java.io.File
import kotlin.math.abs

class Day13 {

    fun partOne(fileName: String): Long {
        return parseArcadeFile(fileName).sumOf { foo(it) }
    }

    fun partTwo(fileName: String): Long {
        return parseArcadeFile(fileName, 10_000_000_000_000.0).sumOf { foo(it) }
    }

    // Using Cramer's rule.
    private fun foo(arcade: Arcade): Long {
        val denominator = arcade.buttonA.first * arcade.buttonB.second - arcade.buttonA.second * arcade.buttonB.first
        val x = (arcade.prize.first * arcade.buttonB.second - arcade.prize.second * arcade.buttonB.first) / denominator
        val y = (arcade.prize.first * arcade.buttonA.second - arcade.prize.second * arcade.buttonA.first) / denominator
        if (isInteger(x) && isInteger(y)) {
            return abs(x.toLong()) * 3 + abs(y.toLong())
        }
        return 0
    }

    private fun isInteger(value: Double): Boolean {
        return value % 1 == 0.0
    }

    private fun parseArcadeFile(filePath: String, offset: Double = 0.0): List<Arcade> {
        val lines = getLines(filePath)
        val arcades = mutableListOf<Arcade>()

        var buttonA: Pair<Double, Double>? = null
        var buttonB: Pair<Double, Double>? = null
        var prize: Pair<Double, Double>? = null

        for (line in lines) {
            if (line.isBlank()) {
                if (buttonA != null && buttonB != null && prize != null) {
                    arcades.add(Arcade(buttonA, buttonB, prize))
                }
                buttonA = null
                buttonB = null
                prize = null
            } else {
                val parts = line.split(": ", limit = 2)
                when (parts[0]) {
                    "Button A" -> {
                        buttonA = parseCoordinates(parts[1], 0.0)
                    }

                    "Button B" -> {
                        buttonB = parseCoordinates(parts[1], 0.0)
                    }

                    "Prize" -> {
                        prize = parseCoordinates(parts[1].replace("=", "+"), offset)
                    }
                }
            }
        }

        if (buttonA != null && buttonB != null && prize != null) {
            arcades.add(Arcade(buttonA, buttonB, prize))
        }

        return arcades
    }

    private fun parseCoordinates(coordinateString: String, offset: Double): Pair<Double, Double> {
        val coordinates = coordinateString.split(", ")
            .map { it.substring(2).toDouble() }
        return Pair(coordinates[0] + offset, coordinates[1] + offset)
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }

    private data class Arcade(
        val buttonA: Pair<Double, Double>,
        val buttonB: Pair<Double, Double>,
        val prize: Pair<Double, Double>
    )
}
