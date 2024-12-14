import org.eruanno.Day14
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day14Test {
    private val subject = Day14()

    @ParameterizedTest
    @CsvSource(
        "'day-14-test', 12, 7, 11",
        "'day-14', 217328832, 103, 101"
    )
    fun testPartOne(testFile: String, expectedResult: Int, height: Int, width: Int) {
        assertEquals(expectedResult, subject.partOne(testFile, height, width))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-14-test', -1, 7, 11",
        "'day-14', 7412, 103, 101"
    )
    fun testPartTwo(testFile: String, expectedResult: Long, height: Int, width: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile, height, width))
    }
}
