package konquest.engine


class StraightNavigator : Navigator {

    override fun path(source: Coordinates, destination: Coordinates): List<Coordinates> {
        return buildList {
            var next = source
            while (next != destination) {
                next = next(next, destination)
                add(next)
            }
        }
    }

    override fun next(source: Coordinates, destination: Coordinates): Coordinates {
        if (destination.x != source.x) {
            val x = if (destination.x > source.x) {
                source.x + 1
            } else {
                source.x - 1
            }
            return Coordinates(x, source.y)
        }

        if (destination.y != source.y) {
            val y = if (destination.y > source.y) {
                source.y + 1
            } else {
                source.y - 1
            }
            return Coordinates(destination.x, y)
        }
        return destination
    }
}
