package objects

import visitors.Composite

data class JsonObject(val values: List<Pair<String, JsonValue>>) : Composite() {
    override val children: MutableList<JsonValue> = mutableListOf()
    init {
        values.forEach { children.add(it.second) }
    }
    override fun toString(): String {
        return "{ ${
            values.joinToString(separator = ", ") { "\"${it.first}\": ${it.second}" }
        } }".replace("  ", " ")
    }
}