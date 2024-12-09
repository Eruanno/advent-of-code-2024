import org.eruanno.Day2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day2Test {
    private val subject = Day2()

    @ParameterizedTest
    @CsvSource(
        "'day-2-test', 2",
        "'day-2', 299"
    )
    fun testPartOne(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-2-test', 4",
        "'day-2-edge-cases', 12",
        "'day-2', 364"
    )
    fun testPartTwo(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
