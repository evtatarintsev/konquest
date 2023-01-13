package konquest.engine

class ConsoleLog: Log {
    override fun info(message: String) {
        println(message)
    }
}

