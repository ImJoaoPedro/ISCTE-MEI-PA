package objects

import visitors.Leaf

data class JsonNumber(override var value: Number): Leaf()  {
    override fun toString(): String {
        return "$value"
    }
}