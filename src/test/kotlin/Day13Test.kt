import org.eruanno.Day13
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day13Test {
    private val subject = Day13()

    @ParameterizedTest
    @CsvSource(
        "'day-13-test', 480",
        "'day-13', 35255"
    )
    fun testPartOne(testFile: String, expectedResult: Long) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-13-test', 875318608908",
        "'day-13', 87582154060429"
    )
    fun testPartTwo(testFile: String, expectedResult: Long) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
