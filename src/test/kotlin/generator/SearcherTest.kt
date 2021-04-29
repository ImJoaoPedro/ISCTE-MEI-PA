package generator

import objects.JsonString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import testModels.Guitar
import testModels.Musician
import kotlin.test.assertEquals

internal class SearcherTest{

    val srv = Musician("Stevie Ray Vaughn", 28, true, Guitar.Fender, listOf())
    val hendrix = Musician("Jimi Hendrix", 27, true, Guitar.Fender, listOf(srv))

    @Test
    fun testSearchAllStrings(){
        val json = Generator().generate(hendrix)
        val searcher = Searcher { it is JsonString }
        json.accept(searcher)

        assertEquals(
            "[\"Jimi Hendrix\", \"Stevie Ray Vaughn\"]",
            searcher.results.toString()
        )
    }

    @Test
    fun testSearchByString(){
        val json = Generator().generate(hendrix)
        val searcher = Searcher { it is JsonString && it.value == "Jimi Hendrix"}
        json.accept(searcher)

        assertEquals(
            "[\"Stevie Ray Vaughn\"]",
            searcher.results.toString()
        )
    }

}