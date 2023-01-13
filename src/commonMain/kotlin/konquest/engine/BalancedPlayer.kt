package konquest.engine

class BalancedPlayer(
    override val name: String,
    override val color: String,
    protection: Int,  // how many fleets should protect planets, percents
) : Player {
    private val me = this
    private val protectionMul =100 / (100-protection)

    private fun safeFleetsCount(total: Int): Int {
        return total *  protectionMul
    }
    override fun step(galaxy: Galaxy): Step {
        val myPlanets = galaxy.planets.filter { it.owner == me }

        val destinations = galaxy.planets.filter { it.owner != me }.toMutableList()
        if (myPlanets.isEmpty() || destinations.isEmpty()) {
            return Step(fleets = emptyList())
        }
        val fleets = mutableListOf<Fleet>()

        myPlanets.forEach {src->
            val fleet = safeFleetsCount(src.spaceships)
            val toEvery = fleet / destinations.size
            destinations.forEach {dest->
                fleets.add(Fleet(source = src, destination = dest, count=toEvery))
            }
        }
        return Step(fleets = fleets)
    }
}
