import org.eruanno.Day10
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day10Test {
    private val subject = Day10()

    @ParameterizedTest
    @CsvSource(
        "'day-10-test', 36",
        "'day-10-test-1', 1",
        "'day-10', 798"
    )
    fun testPartOne(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-10-test', 81",
        "'day-10', 1816"
    )
    fun testPartTwo(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
