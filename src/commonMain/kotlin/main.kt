import com.soywiz.klock.*
import com.soywiz.klock.max
import com.soywiz.korge.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korio.async.*
import konquest.engine.*
import konquest.gui.*
import kotlin.random.*
import kotlin.system.*


const val screenSize = 800


suspend fun main() = Korge(width = screenSize, height = screenSize, bgcolor = Colors["#2b2b2b"]) {
    val random = Random(0)
    val width = 10
    val height = 10
    val planetsCount = 5
    val players = listOf(
        BalancedPlayer("BalancedPlayer1", color = "#008000", protection = 30),
        BalancedPlayer("BalancedPlayer2", color = "#FF0000", 20),
    )
    val galaxy = FutureGalaxy(
        width = width,
        height = height,
        planetsCount = planetsCount,
        random,
        players=players,
        navigator =StraightNavigator(),
        log = NoLog(),
        )

    val availablePlanets = (0 until galaxy.planets.size).toMutableList()
    players.forEach {
        val planetNumber = availablePlanets.removeAt(random.nextInt(availablePlanets.size))
        galaxy.planets[planetNumber].owner = it
    }

    val galaxyView = GalaxyView(galaxy, this, screenSize)
    galaxyView.render()


    val judge = FairJudge(galaxy)
    val (stepCount, time) = measureTimeWithResult {
        play(judge, galaxyView)
    }

    println("player ${judge.winner().name} win in $stepCount")
    println("game finished in ${time.milliseconds}")
    println("ms per step ${time.milliseconds/stepCount}")
}

suspend fun play(judge: Judge, galaxyView: GalaxyView): Int {
    val galaxy = judge.galaxy
    var stepNumber = 1
    while (judge.winner() is NobodyPlayer) {
        galaxy.log.info("${"-".repeat(10)} step $stepNumber ${"-".repeat(10)}")
        galaxy.planets.forEach {
            it.produce()
            galaxy.log.info("planet ${it.name} produce ${it.production} spaceships")
        }
        galaxy.players.forEach { player ->
            val step = player.step(galaxy)
            step.fleets.filter { it.count > 0 }.forEach {
                val fleet = MovingFleet(
                    owner = player,
                    home = it.source,
                    destination = it.destination,
                    count = it.count,
                    location = it.source.location,
                )
                galaxy.movingFleets.add(fleet)
                it.source.spaceships -= it.count
            }
        }

        galaxy.movingFleets.forEach {
            // move forward
            it.location = galaxy.navigator.next(it.location, it.destination.location)
            galaxy.log.info("player's ${it.owner.name} fleet move to (${it.location.x}, ${it.location.y})")

            if (it.location == it.destination.location) {
                judge.attack(it)
            }
        }
        galaxy.movingFleets.removeOnFinish()


        galaxyView.update()

        delay(1000.milliseconds)
        stepNumber++
    }
    return stepNumber
}
