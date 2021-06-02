package visitors

interface Visitor {
    fun visit(l: Leaf)
    fun visit(c: Composite): Boolean
    fun endVisit(c: Composite) {}
}