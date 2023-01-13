package konquest.gui

import com.soywiz.korge.view.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import konquest.engine.*
import kotlin.math.*


class GalaxyView(private val galaxy: Galaxy, private val stage: Stage, private val screenSize: Int) {
    private val freeSpace = 5
    private val maxDimention = max(galaxy.width, galaxy.height)
    private val gridSize = (screenSize.toDouble() - maxDimention * freeSpace) / maxDimention

    private val cells: List<List<Cell>> = buildList {
        for (x in 0 until galaxy.width) {
            add(buildList {
                for (y in 0 until galaxy.height) {
                    val planet = galaxy.planet(x, y)
                    if (planet != null) {
                        add(PlanetSpace(x, y, cellSize = gridSize, planet_ = planet, freeSpace = freeSpace))
                    } else {
                        add(EmptySpace(x, y, cellSize = gridSize, freeSpace = freeSpace))
                    }
                }
            })
        }
    }

    private val cellsWithFleets = mutableListOf<Cell>()

    suspend fun Stage.background() {
        image(resourcesVfs["background.jpg"].readBitmap()) {
            size(screenSize, screenSize)
        }.centerOn(this)
    }

    suspend fun render() {
        stage.background()

        stage.graphics {
            cells.forEach { row -> row.forEach { cell -> it.addChild(cell.container) } }
        }.centerOn(stage)
    }


    fun update() {
        galaxy.planets.forEach {
            cells[it.location.x][it.location.y].updatePlanet(it)
        }
        cellsWithFleets.forEach {
            it.removeFleets()
        }
        cellsWithFleets.clear()
        galaxy.movingFleets.groupByLocation().forEach { (coord, fleets) ->
            val cell = cells[coord.x][coord.y]
            cell.addFleets(fleets)
            cellsWithFleets.add(cell)
        }
    }
}
