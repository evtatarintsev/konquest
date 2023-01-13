package konquest.engine


data class AttackResult(
    val planetLeft: Int,
    val fleetLeft: Int,
) {
    val isPlanetWin = fleetLeft == 0
}


data class MovingFleet(
    val owner: Player,
    val home: Planet,
    val destination: Planet,
    var location: Coordinates,
    val count: Int
) {
    fun attack(): AttackResult {
        return if (destination.spaceships > count) {
            AttackResult(planetLeft = destination.spaceships - count, fleetLeft = 0)
        } else {
            AttackResult(planetLeft = 0, fleetLeft = count - destination.spaceships)
        }
    }
}
