package objects

import visitors.Leaf

class JsonNull : Leaf() {
    override val value: Nothing? = null
    override fun toString(): String {
        return "null"
    }
}