import org.eruanno.Day17
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day17Test {
    private val subject = Day17()

    @ParameterizedTest
    @CsvSource(
        "'day-17-test', '4,6,3,5,6,3,5,2,1,0'",
        "'day-17', '3,4,3,1,7,6,5,6,0'"
    )
    fun testPartOne(testFile: String, expectedResult: String) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-17-test-part-2', 117440",
        "'day-17', 0"
    )
    fun testPartTwo(testFile: String, expectedResult: Long) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
