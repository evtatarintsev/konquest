package konquest.engine

object NobodyPlayer : Player {
    override val name: String = "nobody"
    override val color: String = "#ffffff"
    override fun step(galaxy: Galaxy): Step {
        return Step(fleets = emptyList())
    }
}
