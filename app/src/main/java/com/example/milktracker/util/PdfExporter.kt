package com.example.milktracker.util

import android.content.Context
import android.graphics.pdf.PdfDocument
import android.os.Environment
import java.io.File
import java.io.FileOutputStream

object PdfExporter {
    fun exportBill(context: Context, filename: String, data: List<String>): File {
        val doc = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = doc.startPage(pageInfo)
        val canvas = page.canvas
        val paint = android.graphics.Paint().apply { color = android.graphics.Color.DKGRAY; textSize = 12f }
        canvas.drawText("Milk Tracker - Monthly Bill", 40f, 40f, paint)
        paint.textSize = 10f
        var y = 80f
        data.forEach {
            canvas.drawText(it, 40f, y, paint)
            y += 14f
        }
        doc.finishPage(page)
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) ?: context.filesDir
        val file = File(dir, filename)
        FileOutputStream(file).use { doc.writeTo(it) }
        doc.close()
        return file
    }
}
