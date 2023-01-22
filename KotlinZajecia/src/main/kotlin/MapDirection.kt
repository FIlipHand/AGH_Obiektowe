enum class MapDirection {
    NORTH, EAST, SOUTH, WEST;

    fun next() = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }

    fun previous() = when (this) {
        NORTH -> WEST
        EAST -> NORTH
        SOUTH -> EAST
        WEST -> SOUTH
    }

    override fun toString() = when (this) {
        NORTH -> "^"
        EAST -> ">"
        SOUTH -> "v"
        WEST -> "<"

    }
}
