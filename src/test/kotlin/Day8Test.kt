import org.eruanno.Day8
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day8Test {
    private val subject = Day8()

    @ParameterizedTest
    @CsvSource(
        "'day-8-test', 14",
        "'day-8', 323"
    )
    fun testPartOne(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-8-test', 34",
        "'day-8-test-2', 9",
        "'day-8', 1077"
    )
    fun testPartTwo(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
