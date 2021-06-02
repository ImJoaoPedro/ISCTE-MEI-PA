package plugins

import Inject
import InjectAdd
import Visualizer
import plugins.action.ActionPlugin
import java.io.File
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.isAccessible

class Injector {
    companion object {
        private val map: MutableMap<String, List<KClass<*>>> = mutableMapOf()

        init {
            val scanner =
                Scanner(File("./src/main/kotlin/app.properties"))
            while (scanner.hasNextLine()) {
                val line = scanner.nextLine()
                val parts = line.split("=")
                map[parts[0]] = parts[1].split(",").map { Class.forName(it).kotlin }
            }
            scanner.close()
        }

        fun create(visualizer: Visualizer): Visualizer {
            val type = visualizer::class
            type.declaredMemberProperties.forEach {
                if (it.hasAnnotation<Inject>()) {
                    it.isAccessible = true
                    val key = type.simpleName + "." + it.name
                    if (map[key] != null) {
                        val obj = map[key]!!.first().createInstance()
                        (it as KMutableProperty<*>).setter.call(visualizer, obj)
                    }
                }
                else if (it.hasAnnotation<InjectAdd>()){
                    it.isAccessible = true
                    val key = type.simpleName + "." + it.name
                    if (map[key] != null) {
                        val actions = mutableListOf<ActionPlugin>()
                        map[key]!!.forEach { it2 -> actions.add(it2.createInstance() as ActionPlugin) }
                        (it as KMutableProperty<*>).setter.call(visualizer, actions)
                    }
                }
            }
            return visualizer
        }
    }
}