import kotlin.math.max
import kotlin.math.min

data class Vector2D(
    val x: Int,
    val y: Int
) {
    fun upperRight(other: Vector2D) = Vector2D(max(x, other.x), max(y, other.y))

    fun lowerLeft(other: Vector2D) = Vector2D(min(x, other.x), min(y, other.y))

    operator fun compareTo(other: Vector2D) = when {
        y != other.y -> (y - other.y)
        else -> (x - other.x)
    }

    operator fun unaryMinus() = Vector2D(-x, -y)

    operator fun plus(other: Vector2D) = Vector2D(x + other.x, y + other.y)

    operator fun minus(other: Vector2D) = Vector2D(x - other.x, y - other.y)
    operator fun rangeTo(other: Vector2D): List<Vector2D> {
        return (0 until other.x + 1)
            .flatMap { x ->
                (0 until other.y + 1)
                    .map { y -> Vector2D(x, y) }
            }
    }

}

fun MapDirection.toUnitVector(): Vector2D {
    return when (this) {
        MapDirection.NORTH -> Vector2D(0, 1)
        MapDirection.EAST -> Vector2D(1, 0)
        MapDirection.SOUTH -> Vector2D(0, -1)
        MapDirection.WEST -> Vector2D(-1, 0)
    }
}
