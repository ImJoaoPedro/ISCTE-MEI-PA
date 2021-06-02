package generator

import objects.JsonValue
import visitors.Composite
import visitors.Leaf
import visitors.Visitor

class Searcher(val criteria: (JsonValue) -> Boolean) : Visitor {

    val results: MutableList<JsonValue> = mutableListOf()

    override fun visit(l: Leaf) {
        if(criteria(l)){
            results.add(l)
        }
    }
    override fun visit(c: Composite): Boolean {
        if(criteria(c)){
            results.add(c)
        }
        return true
    }

}
