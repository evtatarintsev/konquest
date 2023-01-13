package konquest.engine

interface Galaxy{
    val width: Int
    val height: Int
    val planets: List<Planet>
    val movingFleets: MovingFleets
    fun planet(x: Int, y: Int): Planet?
    val log: Log
    val navigator: Navigator
    val players: List<Player>
}
