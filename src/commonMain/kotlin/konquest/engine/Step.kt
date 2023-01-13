package konquest.engine

data class Fleet(
    val source: Planet,
    val destination: Planet,
    val count: Int,
)

data class Step (
    val fleets: List<Fleet>
)
