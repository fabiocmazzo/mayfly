package com.fabiomazzo.mayfly.server

/**
 * Created by fabiomazzo on 13/03/2017.
 */

import java.io.BufferedReader

data class HttpRequest(val method: HttpMethod,
                       val url: String,
                       val httpVersion: String,
                       val headers: Map<String, String>,
                       val stream: BufferedReader) {
    val content: String by lazy {
        (1..headers.getOrElse("Content-Length", { "0" }).toInt()).fold("", { a, b -> a + stream.read().toChar() })
    }
}