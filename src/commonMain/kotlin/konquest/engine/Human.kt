package konquest.engine


class Human(
    override val name: String,
    override val color: String,
) : Player {

    override fun step(galaxy: Galaxy): Step {
        return Step(fleets = emptyList())
    }
}
