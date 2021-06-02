package objects

import visitors.Leaf

data class JsonString(override var value: String) : Leaf()  {
    override fun toString(): String {
        return "\"$value\""
    }
}