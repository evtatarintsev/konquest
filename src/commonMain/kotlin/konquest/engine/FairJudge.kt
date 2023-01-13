package konquest.engine

class FairJudge(override val galaxy: Galaxy) : Judge {
    override fun winner(): Player {
        // if there are some fleets of different owners there is a chance to capture planet
        if (galaxy.movingFleets.owners().size > 1) {
            return NobodyPlayer
        }
        val owners = galaxy.planets.map { it.owner }.toSet() + galaxy.movingFleets.owners()

        return if (owners.size == 1) {
            owners.first()
        } else {
            NobodyPlayer
        }
    }

    override fun attack(fleet: MovingFleet) {
        val planet = fleet.destination
        assert(fleet.location == planet.location)

        when (planet.owner) {
            fleet.owner -> {
                galaxy.log.info("player ${fleet.owner.name} fleet from ${fleet.home.name} land on planet ${planet.name}")
                planet.spaceships += fleet.count
            }
            NobodyPlayer -> {
                galaxy.log.info("player ${fleet.owner.name} capture planet ${planet.name}")
                planet.owner = fleet.owner
                planet.spaceships += fleet.count
            }
            else -> {
                galaxy.log.info("player ${fleet.owner.name} attack planet ${planet.name}")
                val result = fleet.attack()
                if (result.isPlanetWin) {
                    planet.spaceships = result.planetLeft
                } else {
                    planet.spaceships = result.fleetLeft
                    planet.owner = fleet.owner
                }
            }
        }
    }
}
