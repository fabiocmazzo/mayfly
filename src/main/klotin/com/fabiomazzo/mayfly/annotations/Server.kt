package com.fabiomazzo.mayfly.annotations

/**
 * Server annotation for define host, port and server name.
 * @author Fabio Covolo Mazzo
 */
annotation class Server(val host : String, val port: Int, val name: String)