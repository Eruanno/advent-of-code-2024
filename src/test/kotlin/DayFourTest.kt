import org.eruanno.DayFour
import org.eruanno.DayThree
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigInteger

class DayFourTest {
    private val subject = DayFour()

    @ParameterizedTest
    @CsvSource(
        "'day-4-test', 18",
        "'day-4', 2549"
    )
    fun testPartOne(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-4-test', 9",
        "'day-4', 2003"
    )
    fun testPartTwo(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}