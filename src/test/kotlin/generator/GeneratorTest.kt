package generator

import org.junit.jupiter.api.Test
import testModels.Guitar
import testModels.Musician
import kotlin.test.assertEquals

internal class GeneratorTest {

    @Test
    fun testGenerateNull(){
        assertEquals("null", Generator().generate(null).toString())
    }
    @Test
    fun testGenerateNumber(){
        assertEquals("5", Generator().generate(5).toString())
    }
    @Test
    fun testGenerateString(){
        assertEquals("ola", Generator().generate("ola").toString())
    }
    @Test
    fun testGenerateBoolean(){
        assertEquals("true", Generator().generate(true).toString())
        assertEquals("false", Generator().generate(false).toString())
    }
    @Test
    fun testGenerateArray(){
        assertEquals(
            "[ \"ola\", 5, true, null ]",
            Generator().generate(listOf("ola", 5, true, null))
        )
    }
    @Test
    fun testGenerateObject(){
        val srv = Musician("Stevie Ray Vaughn", 28, true, Guitar.Fender, listOf())
        val hendrix = Musician("Jimi Hendrix", 27, true, Guitar.Fender, listOf(srv))
        assertEquals(
            "",
            Generator().generate(hendrix).toString()
        )
    }

}