package it.bibo

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import Context._

import scala.util.{Failure, Success}
class ShakerTest extends AnyFlatSpec with Matchers {
  "a shaker" should "produce cocktail until it has ingredient" in {
    val shaker = new Shaker(baseFridge)
    val cocktail = shaker.produce(waterAndLemonRecipe)
    cocktail match {
      case Success(lemonDrink) => lemonDrink shouldBe waterAndLemon
      case Failure(_) => fail()
    }
    //consume the fridge
    (0 to 10) foreach {_ => shaker.produce(waterAndLemonRecipe)}
    shaker.produce(waterAndLemonRecipe) match {
      case Success(_) => fail()
      case _ => succeed
    }
  }
}
