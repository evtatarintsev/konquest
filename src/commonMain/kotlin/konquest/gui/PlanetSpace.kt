package konquest.gui

import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korio.async.*
import konquest.engine.*


class PlanetSpace(
    x: Int,
    y: Int,
    cellSize: Double,
    freeSpace: Int,
    planet_: Planet,
) : EmptySpace(x, y, cellSize, freeSpace) {
    private val planet: ObservableProperty<Planet> = ObservableProperty(planet_)

    init {
        Circle(radius = cellSize / 2 - freeSpace, fill = Colors[planet.value.owner.color]).apply {
            planet.observe {
                fill = Colors[planet.value.owner.color]
            }
            centerOn(container)
            addTo(container)
            text(planet.value.name, color = Colors.BLACK)
                .alignTopToTopOf(this, padding = freeSpace)
                .alignLeftToLeftOf(this, padding = freeSpace)
            text(planet.value.spaceships.toString(), color = Colors.BLACK) {
                planet.observe {
                    text = it.spaceships.toString()
                }
            }
                .alignBottomToBottomOf(this, padding = freeSpace)
                .alignRightToRightOf(this, padding = freeSpace)
        }
    }

    override fun updatePlanet(planet: Planet) {
        this.planet.value = planet
    }
}
