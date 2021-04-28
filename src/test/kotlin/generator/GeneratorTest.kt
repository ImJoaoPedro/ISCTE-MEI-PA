package generator

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

enum class Guitar {
    Fender,
    Epiphone,
    Gibson,
    Squier
}

data class Musician(
    val name: String,
    val age: Int,
    val hasAlbum: Boolean,
    val guitar: Guitar,
    val colleagues: List<Musician>
)

internal class GeneratorTest {

    @Test
    fun testNull(){
        assertEquals("null", Generator().generate(null).toString())
    }
    @Test
    fun testNumber(){
        assertEquals("5", Generator().generate(5).toString())
    }
    @Test
    fun testString(){
        assertEquals("ola", Generator().generate("ola").toString())
    }
    @Test
    fun testBoolean(){
        assertEquals("true", Generator().generate(true).toString())
        assertEquals("false", Generator().generate(false).toString())
    }
    @Test
    fun testArray(){
        assertEquals(
            "[ \"ola\", 5, true, null ]",
            Generator().generate(listOf("ola", 5, true, null))
        )
    }
    @Test
    fun testObject(){
        val srv = Musician("Stevie Ray Vaughn", 28, true, Guitar.Fender, listOf())
        val hendrix = Musician("Jimi Hendrix", 27, true, Guitar.Fender, listOf(srv))
        assertEquals(
            "",
            Generator().generate(hendrix).toString()
        )
    }

}