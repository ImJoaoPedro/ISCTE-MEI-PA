package generator

import objects.*
import kotlin.reflect.full.*

@Target(AnnotationTarget.PROPERTY)
annotation class Ignore

@Target(AnnotationTarget.PROPERTY)
annotation class Key(val name: String)

class Generator {

    fun generate(obj: Any?): JsonValue {
        return when(obj) {
            null -> JsonNull()
            is Boolean -> JsonBoolean(obj)
            is String -> JsonString(obj)
            is Number -> JsonNumber(obj)
            is Enum<*> -> JsonString(obj.name)
            is List<*> -> JsonArray(obj.map { it -> generate(it) }.toMutableList())
            is Set<*> -> JsonArray(obj.map { it -> generate(it) }.toMutableList())
            is Map<*, *> -> JsonObject(obj.toList().map { it.first.toString() to generate(it.second) })
            else -> JsonObject((obj::class).declaredMemberProperties.filter { !it.hasAnnotation<Ignore>() }
                .map { (if (it.hasAnnotation<Key>()) it.findAnnotation<Key>()!!.name else it.name) to generate(it.call(obj)) })
        }
    }

}