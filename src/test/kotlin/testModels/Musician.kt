package testModels

data class Musician(
    val name: String,
    val age: Int,
    val hasAlbum: Boolean,
    val guitar: Guitar,
    val colleagues: List<Musician>
)
