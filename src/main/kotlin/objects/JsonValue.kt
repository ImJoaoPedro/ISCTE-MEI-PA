package objects

import visitors.Visitor

abstract class JsonValue {
    abstract fun accept(v: Visitor)
}