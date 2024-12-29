import org.eruanno.Day24
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day24Test {
    private val subject = Day24()

    @ParameterizedTest
    @CsvSource(
        "'day-24-test', 2024",
        "'day-24', 36902370467952"
    )
    fun testPartOne(testFile: String, expectedResult: Long) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-24-test', '0'",
        "'day-24', '0'"
    )
    fun testPartTwo(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
