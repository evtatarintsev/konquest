package konquest.engine

interface Judge {
    val galaxy: Galaxy
    fun winner(): Player
    fun attack(fleet: MovingFleet)
}
