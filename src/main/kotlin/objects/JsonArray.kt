package objects

import visitors.*

class JsonArray(val list: MutableList<JsonValue>) : Composite() {
    override var children: MutableList<JsonValue> = mutableListOf()
    init {
        list.forEach { children.add(it) }
    }
    override fun toString(): String {
        return "[ ${list.joinToString(", ") { it.toString() }} ]".replace("  ", " ")
    }
}