import org.eruanno.Day7
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigInteger

class Day7Test {
    private val subject = Day7()

    @ParameterizedTest
    @CsvSource(
        "'day-7-test', 3749",
        "'day-7', 2314935962622"
    )
    fun testPartOne(testFile: String, expectedResult: BigInteger) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-7-test', 11387",
        "'day-7', 401477450831495"
    )
    fun testPartTwo(testFile: String, expectedResult: BigInteger) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
