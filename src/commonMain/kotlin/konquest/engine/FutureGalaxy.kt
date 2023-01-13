package konquest.engine

import kotlin.random.*

class FutureGalaxy(
    override val width: Int,
    override val height: Int,
    planetsCount: Int,
    random: Random,
    override val log: Log,
    override val navigator: Navigator,
    override val players: List<Player>,
) : Galaxy {
    override val movingFleets = MovingFleets()
    override val planets = buildList {
        val availableCoordinates = mutableListOf<Coordinates>()
        for (x in 0 until width) {
            for (y in 0 until height) {
                availableCoordinates.add(Coordinates(x, y))
            }
        }
        repeat(planetsCount) {
            val name = ('A'.code + it).toChar().toString()
            val coordinates = availableCoordinates.removeAt(random.nextInt(availableCoordinates.size))
            add(Planet(name = name, location = coordinates, owner = NobodyPlayer))
        }
    }
    private val planetsCoordinates: Map<Coordinates, Planet> = buildMap { planets.forEach { put(it.location, it) } }
    override fun planet(x: Int, y: Int): Planet?  = planetsCoordinates[Coordinates(x, y)]
}
