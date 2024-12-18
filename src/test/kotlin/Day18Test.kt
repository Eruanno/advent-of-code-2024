import org.eruanno.Day18
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test

class Day18Test {
    private val subject = Day18()

    @ParameterizedTest
    @CsvSource(
        "'day-18-test', 22, 12, 6",
        "'day-18', 298, 1024, 70"
    )
    fun testPartOne(testFile: String, expectedResult: Int, bytes: Int, size: Int) {
        assertEquals(expectedResult, subject.partOne(testFile, bytes, size))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-18-test', '6,1', 6",
        "'day-18', '52,32', 70"
    )
    fun testPartTwo(testFile: String, expectedResult: String, size: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile, size))
    }
}
