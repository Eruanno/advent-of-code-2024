import org.eruanno.Day23
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class Day23Test {
    private val subject = Day23()

    @ParameterizedTest
    @CsvSource(
        "'day-23-test', 7",
        "'day-23', 1173"
    )
    fun testPartOne(testFile: String, expectedResult: Int) {
        assertEquals(expectedResult, subject.partOne(testFile))
    }

    @ParameterizedTest
    @CsvSource(
        "'day-23-test', 'co,de,ka,ta'",
        "'day-23', 'cm,de,ez,gv,hg,iy,or,pw,qu,rs,sn,uc,wq'"
    )
    fun testPartTwo(testFile: String, expectedResult: String) {
        assertEquals(expectedResult, subject.partTwo(testFile))
    }
}
