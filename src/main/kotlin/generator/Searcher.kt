package generator

import objects.JsonValue
import visitors.Composite
import visitors.Leaf
import visitors.Visitor

class Searcher(val parameter: (JsonValue) -> Boolean) : Visitor {

    val results: MutableList<JsonValue> = mutableListOf()

    override fun visit(l: Leaf) {
        if(parameter(l)){
            results.add(l)
        }
    }
    override fun visit(c: Composite): Boolean {
        if(parameter(c)){
            results.add(c)
        }
        return true
    }

}
