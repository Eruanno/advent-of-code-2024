import org.eruanno.Day22
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day22Test {
    private val subject = Day22()

    @ParameterizedTest
    @CsvSource(
        "'day-22-test', 37327623",
        "'day-22', 17960270302"
    )
    fun testPartOne(testFile: String, expectedResult: Long) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-22-test-part-2', '23'",
        "'day-22', '0'"
    )
    fun testPartTwo(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
