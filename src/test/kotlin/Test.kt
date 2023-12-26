import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.math.max
import kotlin.math.min
import kotlin.test.*

internal class Test1 {

    @Test
    fun testEncode() {
        assertEquals(encode( "AAAAABBB#####"), "A±5B±3#±5")
        assertEquals(encode( ""), "")
        assertEquals(encode( "ABC"), "A±1B±1C±1")
    }

    @Test
    fun testDecode() {
        assertEquals(decode("A±5B±3#±5"), "AAAAABBB#####")
        assertEquals(decode(""), "")
        assertEquals(decode( "A±1B±1C±1"), "ABC")
    }

    @Test
    fun testMain() {
        main(arrayOf("-c", "-i", "input.txt", "-o", "output1.txt"))
        assertEquals(File("output.txt").readText(), File("output1.txt").readText())
        main(arrayOf("-d", "-i", "output.txt", "-o", "input1.txt"))
        assertEquals(File("input.txt").readText(), File("input1.txt").readText())
    }
}