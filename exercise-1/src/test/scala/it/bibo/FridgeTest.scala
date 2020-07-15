package it.bibo

import org.scalatest.flatspec.AnyFlatSpec
import Context._
import org.scalatest.matchers.should.Matchers

import scala.util.{Failure, Success}

class FridgeTest extends AnyFlatSpec with Matchers{
  "a fridge" should " be immutable" in {
    val fridge = Fridge()
    fridge.insertIngredient(lemon, 1)
    fridge.contains(lemon) shouldBe false
  }

  "a fridge" should "contains ingredients" in {
    baseFridge.contains(lemon) shouldBe true
  }

  "a fridge" should "know the ingredient quantity" in {
    baseFridge.containsAtLeast(lemon, 1) shouldBe true
    baseFridge.containsAtLeast(lemon, 2) shouldBe true
    baseFridge.containsAtLeast(lemon, 3) shouldBe true
    baseFridge.containsAtLeast(lemon, 4) shouldBe false
  }

  "a fridge" should "be consumed" in {
    val consumedFridge = baseFridge.useIngredient(lemon, 2)
    consumedFridge match {
      case Success(fridge) =>
        fridge.containsAtLeast(lemon, 1) shouldBe true
        fridge.containsAtLeast(lemon, 2) shouldBe false
      case Failure(_) => fail()
    }
  }
}
