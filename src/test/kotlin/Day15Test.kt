import org.eruanno.Day15
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day15Test {
    private val subject = Day15()

    @ParameterizedTest
    @CsvSource(
        "'day-15-test', 10092",
        "'day-15-test-smaller', 2028",
        "'day-15', 1486930"
    )
    fun testPartOne(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-15-test', 9021",
        "'day-15-test-smaller-2', 618",
        "'day-15', 1492011"
    )
    fun testPartTwo(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
