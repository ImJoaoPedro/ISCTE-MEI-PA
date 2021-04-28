package objects

import visitors.Leaf

data class JsonNumber(override val value: Number): Leaf()  {
    override fun toString(): String {
        return "$value"
    }
}