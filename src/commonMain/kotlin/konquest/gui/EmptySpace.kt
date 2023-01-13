package konquest.gui

import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import konquest.engine.*


open class EmptySpace(
    x: Int,
    y: Int,
    cellSize: Double,
    private val freeSpace: Int,
) : Cell {
    override val container = RoundRect(cellSize, cellSize, 2.0, fill = Colors["#cec0b2"]).xy(
        freeSpace + (freeSpace + cellSize) * x,
        freeSpace + (freeSpace + cellSize) * y
    )
    private val renderedFleets = mutableListOf<Container>()

    override fun updatePlanet(planet: Planet) {}
    override fun addFleets(fleets: List<MovingFleet>) {
        var shift = freeSpace
        fleets.groupBy { it.owner }.forEach { (owner, fleets) ->
            val c = Circle(radius = 10.0, fill = Colors[owner.color]).apply {
                addTo(container)
                text("${fleets.sumOf { it.count }}")
            }.xy(shift, freeSpace)
            shift += 10
            renderedFleets.add(c)
        }
    }

    override fun removeFleets() {
        renderedFleets.forEach { it.removeFromParent() }
        renderedFleets.clear()
    }
}
