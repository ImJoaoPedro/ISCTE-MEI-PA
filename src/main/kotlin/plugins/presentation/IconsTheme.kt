package plugins.presentation

import Visualizer
import objects.JsonArray
import objects.JsonNumber
import objects.JsonObject
import objects.JsonValue
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.graphics.ImageData
import org.eclipse.swt.widgets.*
import traverse
import visitors.Leaf

class IconsTheme : PresentationPlugin {
    override fun execute(visualizer: Visualizer) {
        visualizer.tree.traverse {
            when (it.data) {
                is Leaf -> {
                    it.image = Image(Display.getDefault(), ImageData("./src/main/kotlin/icons/file.png").scaledTo(25, 25))
                }
                is JsonObject -> {
                    it.image = Image(Display.getDefault(), ImageData("./src/main/kotlin/icons/folder.png").scaledTo(25, 25))
                }
                is JsonArray -> {
                    it.image = Image(Display.getDefault(), ImageData("./src/main/kotlin/icons/folder.png").scaledTo(25, 25))
                }
            }
        }
    }
}