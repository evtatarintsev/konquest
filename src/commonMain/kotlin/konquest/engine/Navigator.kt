package konquest.engine

interface Navigator {
    fun path(source: Coordinates, destination: Coordinates): List<Coordinates>
    fun next(source: Coordinates, destination: Coordinates): Coordinates
}
