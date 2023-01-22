import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MapTest : ExpectSpec() {

    init {
        expect("Animal moved one step forward") {
            val mapka = BouncyMap(10, 8)
            val krowa = Animal(position = Vector2D(2, 2), map = mapka)
            krowa.move(MoveDirection.FORWARD)
            krowa.position shouldBe Vector2D(2, 3)
        }
    }

    init {
        expect("Animal can't move over map border") {
            val mapka = BouncyMap(10, 8)
            val zuk = Animal(position = Vector2D(3, 8), map = mapka)
            zuk.move(MoveDirection.FORWARD)
            zuk.position shouldBe Vector2D(3, 8)
        }
    }

    init {
        expect("Animal bounced to random position") {
            val mapka = BouncyMap(10, 8)
            val kon = Animal(position = Vector2D(3, 5), map = mapka)
            val kon2 = Animal(position = Vector2D(3, 4), map = mapka)
            kon.move(MoveDirection.BACKWARD)
            kon.position shouldNotBe kon2.position
        }
    }

    init {
        expect("Animal can move even in a full map") {
            val park = BouncyMap(1, 1)
            val pies1 = Animal(position = Vector2D(0, 0), map = park)
            val pies2 = Animal(position = Vector2D(0, 1), map = park)
            val pies3 = Animal(position = Vector2D(1, 0), map = park)
            val pies4 = Animal(position = Vector2D(1, 1), map = park)

            val wiewiorka = Animal(position = Vector2D(0, 0), map = park, orientation = MapDirection.SOUTH)
            wiewiorka.move(MoveDirection.FORWARD)
            wiewiorka shouldBeIn park.animals()
        }
    }

}
