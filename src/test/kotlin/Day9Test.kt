import org.eruanno.Day9
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day9Test {
    private val subject = Day9()

    @ParameterizedTest
    @CsvSource(
        //"'day-9-test-simple', 60",
        //"'day-9-test-simple-2', 99",
        //"'day-9-test', 1928",
        //"'day-9-test-double-digits', 3383",
         "'day-9', 0"
        // too low: 729060782, 227509067733
        // too high: 6415978510013
    )
    fun testPartOne(testFile: String, expectedResult: Long) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-9-test', 0",
        "'day-9', 0"
    )
    fun testPartTwo(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
