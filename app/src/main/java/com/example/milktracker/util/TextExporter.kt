package com.example.milktracker.util

import android.content.Context
import android.os.Environment
import java.io.File

object TextExporter {
    fun exportSummary(context: Context, text: String): File {
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) ?: context.filesDir
        return File(dir, "milk_summary.txt").apply { writeText(text) }
    }

    fun formatMonth(entries: List<Triple<String, Float, Float>>): String {
        val sb = StringBuilder()
        sb.appendLine("Milk Tracker Summary")
        sb.appendLine("--------------------")
        entries.forEach { (date, qty, cost) ->
            sb.appendLine("$date  |  $qty L  |  ₹$cost")
        }
        return sb.toString()
    }
}
