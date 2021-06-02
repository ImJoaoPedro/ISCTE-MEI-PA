package visitors

import objects.JsonValue

abstract class Composite : JsonValue() {
    abstract val children: MutableList<JsonValue>
    override fun accept(v: Visitor) {
        if(v.visit(this))
            children.forEach{
                it.accept(v)
            }
        v.endVisit(this)
    }
}