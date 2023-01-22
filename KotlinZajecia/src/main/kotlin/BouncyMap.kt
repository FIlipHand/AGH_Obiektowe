class BouncyMap(
    width: Int,
    height: Int
) : IWorldMap {

    private var animalMap = mutableMapOf<Vector2D, Animal>()
    private val size = Vector2D(width, height)
    override fun canMoveTo(position: Vector2D) = (position > Vector2D(0, 0)) && (position < size)

    override fun place(animal: Animal): Boolean {
        val placed: Boolean
        if (isOccupied(animal.position)) {
            val nextPosition = animalMap.randomFreePosition(size) ?: animalMap.randomPosition()
            if (nextPosition != null) {
                animalMap.remove(animal.position)
                animalMap[nextPosition] = animal
                animal.position = nextPosition
                placed = true
            } else {
                placed = false
            }
        } else {
            animalMap[animal.position] = animal
            placed = true
        }
        return placed
    }

    override fun isOccupied(position: Vector2D) = animalMap.containsKey(position)

    override fun objectAt(position: Vector2D): Animal? = animalMap[position]

    override fun animals(): List<Animal> = animalMap.values.toList()
}