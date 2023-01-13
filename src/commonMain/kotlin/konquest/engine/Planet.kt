package konquest.engine

interface IPlanet {
    val name: String
    val location: Coordinates
    val owner: Player
    val spaceships: Int
    val production: Int
}

interface Factory {
    fun produce()
}

interface Ownerable {

}

data class Planet (
    override val name: String,
    override val location: Coordinates,
    override var owner: Player,
    override var spaceships: Int = 0,
    override val production: Int = 10,
) : IPlanet, Factory {
    override fun produce() {
        if (owner != NobodyPlayer) {
            spaceships += production
        }
    }
}
