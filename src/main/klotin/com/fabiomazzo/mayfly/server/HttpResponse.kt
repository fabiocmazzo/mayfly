package com.fabiomazzo.mayfly.server

/**
 * Created by fabiomazzo on 13/03/2017.
 */

package org.kottpd

import java.io.OutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter

data class HttpResponse(var status: Status = Status.OK,
                        val stream: OutputStream) {
    private val printWriter: PrintWriter by lazy {
        PrintWriter(OutputStreamWriter(stream))
    }

    private var dirty = false

    fun send(content: String, status: Status = this.status, headers: Map<String, String> = emptyMap()) {
        if (!dirty) {
            printWriter.println("HTTP/1.1 ${status.code} ${status.value}")
            if (headers.isNotEmpty()) {
                headers.forEach { printWriter.println("${it.key}: ${it.value}") }
                printWriter.println()
            }
            printWriter.println()
            dirty = true
        }
        printWriter.print(content)
    }

    fun flush() {
        printWriter.flush()
    }
}