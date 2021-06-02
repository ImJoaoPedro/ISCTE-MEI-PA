package debug

import generator.Generator

/*
DEBUGGING CLASS USED TO INSTATIATE OBJECTS WITH DUMMY NAMES
DO NOT COPY
NOT INTENDED FOR ANY USE BESIDES TESTING
 */

internal enum class GGuitar {
    Fender,
    Epiphone,
    Gibson,
    Squier
}

internal data class MMusician(
    val name: String,
    val age: Int,
    val hasAlbum: Boolean,
    val guitar: GGuitar,
    val colleagues: List<MMusician>
)

fun main(args: Array<String>) {
    //print(Generator().generate(listOf("ola", 5, true, null).toString()))

    val srv = MMusician("Stevie Ray Vaughn", 28, true, GGuitar.Fender, listOf())
    val hendrix = MMusician("Jimi Hendrix", 27, true, GGuitar.Fender, listOf(srv))
    print(Generator().generate(hendrix).toString())
}