package plugins.action

import Visualizer
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.Text
import java.io.File

class WriteToFile: ActionPlugin {
    override val name: String
        get() = "Write to file"

    override fun execute(viewer: Visualizer) {
        val shell = Shell(viewer.shell)

        shell.text = name
        shell.layout = GridLayout(1,true)

        val label = Label(shell, SWT.BORDER or SWT.WRAP)
        label.text = "Please write the absolute path on the box below"

        val path = Text(shell,  SWT.NONE)
        path.layoutData = GridData(GridData.FILL_HORIZONTAL)
        path.text = "C:\\temp\\export.json"

        val button = Button(shell, SWT.PUSH)
        button.layoutData = GridData(GridData.FILL_HORIZONTAL)
        button.text = "Write"

        button.addSelectionListener(object: SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                val path = path.text
                if(path.isNotEmpty()){
                    File(path).writeText(viewer.text.text)
                    shell.close()
                }
            }
        })
        shell.pack()
        shell.open()
    }
}