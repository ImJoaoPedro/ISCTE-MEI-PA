package objects

import visitors.Leaf

data class JsonString(override val value: String) : Leaf()  {
    override fun toString(): String {
        return "\"$value\""
    }
}