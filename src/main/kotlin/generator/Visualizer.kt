import debug.GGuitar
import debug.MMusician
import generator.Generator
import objects.JsonArray
import objects.JsonObject
import objects.JsonValue
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.graphics.Color
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*
import plugins.Injector
import visitors.Leaf
import plugins.action.ActionPlugin
import plugins.presentation.PresentationPlugin

fun main() {
    val srv = MMusician("Stevie Ray Vaughn", 28, true, GGuitar.Fender, listOf())
    val hendrix = MMusician("Jimi Hendrix", 27, true, GGuitar.Fender, listOf(srv))
    val json = Generator().generate(hendrix)
    Injector.create(Visualizer(json)).open()
}

class Visualizer(val value : JsonValue) {
    val shell: Shell
    val tree: Tree
    val text: Label
    val search: Text

    @Inject
    private lateinit var presentation: PresentationPlugin

    @InjectAdd
    private var actions = mutableListOf<ActionPlugin>()

    init {
        val display = Display()
        shell = Shell(display)
        shell.text = "Visualizer"
        shell.layout = GridLayout(2,true)

        tree = Tree(shell, SWT.BORDER)
        tree.layoutData = GridData(GridData.FILL_BOTH)

        text = Label(shell, SWT.BORDER or SWT.WRAP)
        text.layoutData = GridData(GridData.FILL_BOTH)

        val searchLabel = Label(shell, SWT.BORDER or SWT.WRAP)
        searchLabel.text = "Search here -> "
        searchLabel.layoutData = GridData(GridData.FILL_HORIZONTAL)

        search = Text(shell,  SWT.NONE)
        search.layoutData = GridData(GridData.FILL_HORIZONTAL)
        search.addModifyListener { highlight(search.text); }

        tree.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                text.text = tree.selection.first().data.toString()
            }
        })

        createTree(tree, value)
    }

    private fun createTree(branch: Any, value: JsonValue, name: String? = null) {
        val treeItem = if (branch is Tree) {
            TreeItem(branch, SWT.None)
        } else {
            TreeItem(branch as TreeItem, SWT.None)
        }
        when (value) {
            is JsonObject -> {
                if (name != null){
                    treeItem.text = "object: $name"
                } else {
                    treeItem.text = "object"
                }
                treeItem.data = value
                value.values.forEach {
                    createTree(treeItem, it.second, it.first)
                }
            }
            is Leaf -> {
                if (name != null) {
                    treeItem.text = "$name: $value"
                } else {
                    treeItem.text = "$value"
                }
                treeItem.data = value
            }
            is JsonArray -> {
                treeItem.text = "$name"
                treeItem.data = value
                value.list.forEach {
                    createTree(treeItem, it)
                }
            }
        }
    }

    fun open() {
        tree.expandAll()
        applyPresentation()
        applyActions()
        shell.pack()
        shell.open()
        val display = Display.getDefault()
        while (!shell.isDisposed) {
            if (!display.readAndDispatch()) display.sleep()
        }
        display.dispose()
    }

    private fun highlight(text: String) {
        tree.traverse {
            it.background = null
            if (text.isNotEmpty() && it.text.toLowerCase().contains(text))
                it.background =  Color(255, 0, 0)
        }
    }

    private fun applyPresentation(){
        if (this::presentation.isInitialized) {
            presentation.execute(this)
        }
    }

    private fun applyActions() {
        val viewer = this
        actions.forEach {
            val button = Button(shell, SWT.PUSH)
            button.text = it.name
            button.addSelectionListener(object: SelectionAdapter() {
                override fun widgetSelected(e: SelectionEvent) {
                    it.execute(viewer)
                }
            })
        }
    }

    fun refresh(){
        tree.removeAll()
        createTree(tree, value)
        tree.expandAll()
        applyPresentation()
    }
}


