package com.sshhhdev.svnbranchbar

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.StatusBar
import com.intellij.openapi.wm.StatusBarWidget
import com.intellij.openapi.wm.StatusBarWidget.TextPresentation
import com.intellij.openapi.wm.StatusBarWidgetFactory
import com.intellij.openapi.wm.impl.status.EditorBasedStatusBarPopup
import com.intellij.util.Consumer
import java.awt.event.MouseEvent
import javax.swing.Icon
import javax.swing.JLabel

class SvnBranchStatusBar(private val project: Project) : StatusBarWidget, TextPresentation {

    override fun ID(): String = "SvnBranchWidget"

    override fun install(statusBar: StatusBar) {}

    override fun dispose() {}

    override fun getPresentation(): StatusBarWidget.WidgetPresentation = this

    override fun getText(): String {
        val branch = getCurrentSvnBranch()
        return "SVN: $branch"
    }

    override fun getAlignment(): Float = Component.CENTER_ALIGNMENT

    override fun getClickConsumer(): Consumer<MouseEvent>? = null

    private fun getCurrentSvnBranch(): String {
        return try {
            val process = ProcessBuilder("svn", "info")
                .redirectErrorStream(true)
                .start()

            process.inputStream.bufferedReader().useLines { lines ->
                lines.firstOrNull { it.startsWith("Relative URL:") }
                    ?.removePrefix("Relative URL: ^/")
                    ?.trim()
                    ?: "Unknown"
            }
        } catch (e: Exception) {
            "ERR"
        }
    }
}
