class Animal(
    var position: Vector2D,
    private var orientation: MapDirection = MapDirection.NORTH,
    private var map: IWorldMap
) {
    init {
        map.place(this)
    }

    override fun toString(): String {
        return orientation.toString()
    }

    fun move(direction: MoveDirection) {
        val newPosition: Vector2D
        when (direction) {
            MoveDirection.RIGHT -> orientation = orientation.next()
            MoveDirection.LEFT -> orientation = orientation.previous()
            MoveDirection.FORWARD -> {
                newPosition = position + orientation.toUnitVector()
                if (map.canMoveTo(newPosition)) {
                    position = newPosition
                    map.place(this)
                }
            }

            MoveDirection.BACKWARD -> {
                newPosition = position - orientation.toUnitVector()
                println(map.isOccupied(newPosition))

                if (map.canMoveTo(newPosition)) {
                    position = newPosition
                    map.place(this)
                }
            }
        }
    }
}
