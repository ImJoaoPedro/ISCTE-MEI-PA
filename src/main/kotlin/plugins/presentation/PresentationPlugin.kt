package plugins.presentation

import Visualizer

interface PresentationPlugin {
    fun execute(visualizer: Visualizer)
}