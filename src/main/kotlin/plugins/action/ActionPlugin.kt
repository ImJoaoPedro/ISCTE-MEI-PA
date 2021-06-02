package plugins.action

import Visualizer

interface ActionPlugin {
    val name: String
    fun execute(visualizer: Visualizer)
}