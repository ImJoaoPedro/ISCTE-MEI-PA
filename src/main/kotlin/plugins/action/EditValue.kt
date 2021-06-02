package plugins.action

import Visualizer
import objects.JsonBoolean
import objects.JsonNull
import objects.JsonNumber
import objects.JsonString
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*
import visitors.Leaf

class EditValue: ActionPlugin {
    override val name: String
        get() = "Edit Value"

    override fun execute(visualizer: Visualizer) {
        val item: TreeItem = visualizer.tree.selection.first()
        val value = item.data
        if (value is Leaf && value !is JsonNull) {
            val shell = Shell(visualizer.shell)

            shell.text = name
            shell.layout = GridLayout(1, true)

            val newValue = Text(shell, SWT.NONE)
            newValue.layoutData = GridData(GridData.FILL_HORIZONTAL)
            newValue.text = value.value.toString()

            val button = Button(shell, SWT.PUSH)
            button.layoutData = GridData(GridData.FILL_HORIZONTAL)
            button.text = "Edit"

            button.addSelectionListener(object : SelectionAdapter() {
                override fun widgetSelected(e: SelectionEvent) {
                    when (value) {
                        is JsonBoolean -> {
                            value.value = newValue.text.toBoolean()
                        }
                        is JsonNumber -> {
                            value.value = newValue.text.toInt()
                        }
                        else -> (value as JsonString).value = newValue.text
                    }
                    visualizer.refresh()
                    shell.close()
                }
            })
            shell.pack()
            shell.open()
        }
    }
}