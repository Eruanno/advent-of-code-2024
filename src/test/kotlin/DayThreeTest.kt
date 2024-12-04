import org.eruanno.DayThree
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigInteger

class DayThreeTest {
    private val subject: DayThree = DayThree()

    @ParameterizedTest
    @CsvSource(
        "'day-3-test', 161",
        "'day-3', 170807108"
    )
    fun testPartOne(testFile: String, expectedResult: BigInteger) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-3-part-2-test', 48",
        "'day-3', 74838033"
    )
    fun testPartTwo(testFile: String, expectedResult: BigInteger) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}