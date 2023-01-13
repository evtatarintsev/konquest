package konquest.gui

import com.soywiz.korge.view.*
import konquest.engine.*


interface Cell {
    val container: Container
    fun updatePlanet(planet: Planet)
    fun addFleets(fleets: List<MovingFleet>)
    fun removeFleets()
}


