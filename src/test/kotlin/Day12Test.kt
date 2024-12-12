import org.eruanno.Day12
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day12Test {
    private val subject = Day12()

    @ParameterizedTest
    @CsvSource(
        "'day-12-test-simple', 140",
        "'day-12-test', 1930",
        "'day-12', 1433460"
    )
    fun testPartOne(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-12-test-simple', 80",
        "'day-12-test-e-shaped', 236",
        "'day-12-test-a-b', 368",
        "'day-12-test', 1206",
        "'day-12', 855082"
    )
    fun testPartTwo(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
