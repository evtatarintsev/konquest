package konquest.engine

import kotlin.test.*


class MovingFleetTest {
    private val location = Coordinates(0, 0)
    private val home = Planet("", location = location, owner = NobodyPlayer, spaceships = 10)

    @Test
    fun attack_equal_power_remain_zero() {
        // arrange
        val fleet = MovingFleet(
            owner = NobodyPlayer,
            home = home,
            destination = home,
            count = 10,
            location = location,
        )
        val expectedResult = AttackResult(planetLeft = 0, fleetLeft = 0)

        // act
        val result = fleet.attack()

        // assert
        assertEquals(expectedResult, result)
    }
    @Test
    fun attack_planet_win() {
        // arrange
        val fleet = MovingFleet(
            owner = NobodyPlayer,
            home = home,
            destination = home,
            count = 5,
            location = location,
        )
        val expectedResult = AttackResult(planetLeft = 5, fleetLeft = 0)

        // act
        val result = fleet.attack()

        // assert
        assertEquals(expectedResult, result)
    }
    @Test
    fun attack_fleet_win() {
        // arrange
        val fleet = MovingFleet(
            owner = NobodyPlayer,
            home = home,
            destination = home,
            count = 15,
            location = location,
        )
        val expectedResult = AttackResult(planetLeft = 0, fleetLeft = 5)

        // act
        val result = fleet.attack()

        // assert
        assertEquals(expectedResult, result)
    }
}
