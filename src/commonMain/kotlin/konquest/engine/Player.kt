package konquest.engine

interface Player {
    val name: String
    val color: String
    fun step(galaxy: Galaxy): Step
}
