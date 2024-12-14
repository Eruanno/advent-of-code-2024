package org.eruanno

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Day14 {

    fun partOne(fileName: String, height: Int, width: Int): Int {
        val robots = parseFile(fileName)
        val seconds = 100
        var quadrantA = 0
        var quadrantB = 0
        var quadrantC = 0
        var quadrantD = 0
        for (robot in robots) {
            val finalX = ((robot.first.first + (seconds * robot.second.first)) % width + width) % width
            val finalY = ((robot.first.second + (seconds * robot.second.second)) % height + height) % height
            if (finalX < width / 2 && finalY < height / 2) {
                quadrantA++
            }
            if (finalX > width / 2 && finalY < height / 2) {
                quadrantB++
            }
            if (finalX > width / 2 && finalY > height / 2) {
                quadrantC++
            }
            if (finalX < width / 2 && finalY > height / 2) {
                quadrantD++
            }
        }
        return quadrantA * quadrantB * quadrantC * quadrantD
    }

    fun partTwo(fileName: String, height: Int, width: Int): Long {
        var robots = parseFile(fileName)
        val seconds = 10000
        var second = 0
        while (second < seconds) {
            robots = robots.map { moveRobot(it, height, width) }
            //generateImage(height, width, robots.map { it.first }, fileName, second)
            second++
        }
        return 0
    }

    private fun moveRobot(
        robot: Pair<Pair<Int, Int>, Pair<Int, Int>>,
        height: Int,
        width: Int
    ): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        var x = robot.first.first + robot.second.first
        var y = robot.first.second + robot.second.second
        if (x < 0) {
            x += width
        }
        if (x > width) {
            x -= width
        }
        if (y < 0) {
            y += height
        }
        if (y > width) {
            y -= height
        }
        return Pair(Pair(x, y), robot.second)
    }

    private fun generateImage(x: Int, y: Int, positions: List<Pair<Int, Int>>, outputFileName: String, second: Int) {
        val image = BufferedImage(x, y, BufferedImage.TYPE_INT_RGB)

        for (i in 0 until x) {
            for (j in 0 until y) {
                image.setRGB(i, j, Color.WHITE.rgb)
            }
        }
        for ((xn, yn) in positions) {
            if (xn in 0 until x && yn in 0 until y) {
                image.setRGB(xn, yn, Color.BLACK.rgb)
            }
        }
        val folder = File(outputFileName)
        if (!folder.exists()) {
            folder.mkdirs()
        }

        val outputFile = File(folder, "$outputFileName-$second.png")
        ImageIO.write(image, "png", outputFile)
    }

    private fun parseFile(fileName: String): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> {
        return getLines(fileName).map { line ->
            val regex = """p=(-?\d+),(-?\d+)\s+v=(-?\d+),(-?\d+)""".toRegex()
            val matchResult = regex.matchEntire(line) ?: throw IllegalArgumentException("Invalid line format: $line")
            val (px, py, vx, vy) = matchResult.destructured
            (px.toInt() to py.toInt()) to (vx.toInt() to vy.toInt())
        }
    }

    private fun getLines(fileName: String): List<String> {
        return this::class.java.getResource("/$fileName")?.path?.let { File(it).readLines() } ?: return emptyList()
    }
}
