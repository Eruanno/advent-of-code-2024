import org.eruanno.DayFive
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DayFiveTest {
    private val subject = DayFive()

    @ParameterizedTest
    @CsvSource(
        "'day-5-test', 143",
        "'day-5', 5948"
    )
    fun testPartOne(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-5-test', 123",
        "'day-5', 3062"
    )
    fun testPartTwo(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}