package objects

import visitors.Leaf

data class JsonBoolean(override val value: Boolean) : Leaf() {
    override fun toString(): String {
        return value.toString()
    }
}