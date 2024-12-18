import org.eruanno.Day16
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day16Test {
    private val subject = Day16()

    @ParameterizedTest
    @CsvSource(
        "'day-16-test-smaller', 7036",
        "'day-16-test', 11048",
        "'day-16', 103512"
    )
    fun testPartOne(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-16-test-smaller', 45",
        "'day-16-test', 64",
        "'day-16', 103512"
    )
    fun testPartTwo(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
