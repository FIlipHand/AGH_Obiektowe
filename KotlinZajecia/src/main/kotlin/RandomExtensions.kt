fun <T> MutableMap<Vector2D, T>.randomFreePosition(mapSize: Vector2D) =
    (Vector2D(0, 0)..mapSize)
        .filter { !this.containsKey(it) }
        .randomOrNull()

fun <Vector2D, V> MutableMap<Vector2D, V>.randomPosition() = this.keys.randomOrNull()
