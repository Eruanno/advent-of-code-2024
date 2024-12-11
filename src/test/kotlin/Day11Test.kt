import org.eruanno.Day11
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day11Test {
    private val subject = Day11()

    @ParameterizedTest
    @CsvSource(
        "'day-11-test', 55312",
        "'day-11', 220999"
    )
    fun testPartOne(testFile: String, expectedResult: Long) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-11-test', 65601038650482",
        "'day-11', 261936432123724"
    )
    fun testPartTwo(testFile: String, expectedResult: Long) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
