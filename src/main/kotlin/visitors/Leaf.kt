package visitors

import objects.JsonValue

abstract class Leaf : JsonValue() {
    abstract val value: Any?
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}