import org.eruanno.DayOne
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DayOneTest {
    private val subject: DayOne = DayOne()

    @ParameterizedTest
    @CsvSource(
        "'day-1-test', 11",
        "'day-1', 2164381"
    )
    fun testPartOne(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-1-test', 31",
        "'day-1', 20719933"
    )
    fun testPartTwo(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
