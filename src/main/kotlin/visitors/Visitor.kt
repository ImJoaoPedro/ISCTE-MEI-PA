package visitors

interface Visitor {
    fun visit(l: Leaf)
    fun visit(c: Composite): Boolean
    fun endVisit(l: Leaf) {}
    fun endVisit(c: Composite) {}
}