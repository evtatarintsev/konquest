package konquest.engine

class AllToOnePlanetPlayer(
    override val name: String,
    override val color: String,
) : Player {
    private val me = this

    override fun step(galaxy: Galaxy): Step {
        val myPlanets = galaxy.planets.filter { it.owner == me }
        val destination = galaxy.planets.find { it.owner != me }
        if (destination == null) {
            return Step(fleets = emptyList())
        }

        return Step(fleets = buildList {
            myPlanets.map {
                add(Fleet(source = it, destination = destination, count=it.spaceships))
            }
        })
    }
}
