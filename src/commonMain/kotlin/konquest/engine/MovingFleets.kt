package konquest.engine

class MovingFleets {
    private val fleets = mutableMapOf<Player, MutableList<MovingFleet>>()
    fun add(fleet: MovingFleet) {
        if (fleets.containsKey(fleet.owner)) {
            fleets[fleet.owner]!!.add(fleet)
        } else {
            fleets[fleet.owner] = mutableListOf(fleet)
        }
    }

    fun groupByLocation() = fleets.values.flatten().groupBy { it.location }

    fun forEach(action: (MovingFleet) -> Unit) {
        fleets.values.flatten().forEach(action)
    }

    fun owners() = fleets.keys

    fun removeOnFinish() {
        val ownersToRemove = mutableListOf<Player>()
        fleets.forEach { (owner, fleets) ->
            fleets.removeIf { it.location == it.destination.location }
            if (fleets.isEmpty()) {
                ownersToRemove.add(owner)
            }
        }
        ownersToRemove.forEach {
            fleets.remove(it)
        }
    }
}
