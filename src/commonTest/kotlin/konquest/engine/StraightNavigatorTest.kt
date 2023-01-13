package konquest.engine


import kotlin.test.*

class StraightNavigatorTest {
    @Test
    fun `no need to move return empty path`() {
        // arrange
        val navigator = StraightNavigator()

        // act
        val actualPath = navigator.path(Coordinates(0, 0), Coordinates(0, 0))

        // assert
        assertEquals(emptyList(), actualPath)
    }

    @Test
    fun `find path to right bottom`() {
        // arrange
        val navigator = StraightNavigator()
        val expectedPath = listOf(
            Coordinates(1, 0),
            Coordinates(2, 0),
            Coordinates(2, 1),
            Coordinates(2, 2),
        )

        // act
        val actualPath = navigator.path(Coordinates(0, 0), Coordinates(2, 2))

        // assert
        assertEquals(expectedPath, actualPath)
    }
    @Test
    fun `find path to left bottom`() {
        // arrange
        val navigator = StraightNavigator()
        val expectedPath = listOf(
            Coordinates(1, 0),
            Coordinates(0, 0),
            Coordinates(0, 1),
            Coordinates(0, 2),
        )

        // act
        val actualPath = navigator.path(Coordinates(2, 0), Coordinates(0, 2))

        // assert
        assertEquals(expectedPath, actualPath)
    }

    @Test
    fun `find path to right top`() {
        // arrange
        val navigator = StraightNavigator()
        val expectedPath = listOf(
            Coordinates(1, 2),
            Coordinates(2, 2),
            Coordinates(2, 1),
            Coordinates(2, 0),
        )

        // act
        val actualPath = navigator.path(Coordinates(0, 2), Coordinates(2, 0))

        // assert
        assertEquals(expectedPath, actualPath)
    }

    @Test
    fun `find path to left top`() {
        // arrange
        val navigator = StraightNavigator()
        val expectedPath = listOf(
            Coordinates(1, 2),
            Coordinates(0, 2),
            Coordinates(0, 1),
            Coordinates(0, 0),
        )

        // act
        val actualPath = navigator.path(Coordinates(2, 2), Coordinates(0, 0))

        // assert
        assertEquals(expectedPath, actualPath)
    }

    @Test
    fun `can find path outside`() {
        // arrange
        val navigator = StraightNavigator()
        val expectedPath = listOf(
            Coordinates(-1, 0),
            Coordinates(-1, -1),
        )

        // act
        val actualPath = navigator.path(Coordinates(0, 0), Coordinates(-1, -1))

        // assert
        assertEquals(expectedPath, actualPath)
    }
}
