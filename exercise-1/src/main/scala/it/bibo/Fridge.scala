package it.bibo

import scala.util.{Failure, Success, Try}

trait Fridge {
  def insertIngredient(ingredient: Ingredient, quantity : Int) : Fridge
  def contains(ingredient: Ingredient) : Boolean
  def containsAtLeast(ingredient: Ingredient, quantity : Int) : Boolean
  def useIngredient(ingredient: Ingredient, quantity : Int) : Try[Fridge]
}

object Fridge {
  def apply(): Fridge = new FridgeImpl(Map.empty)
  class FridgeImpl(ingredientMap : Map[Ingredient, Int]) extends Fridge {
    override def insertIngredient(ingredient: Ingredient, quantity: Int): Fridge = {
      require(quantity > 0)
      val updatedIngredientValue = ingredientMap.getOrElse(ingredient, 0) + quantity
      new FridgeImpl(ingredientMap + (ingredient -> updatedIngredientValue))
    }

    override def contains(ingredient: Ingredient): Boolean = ingredientMap.contains(ingredient)

    override def containsAtLeast(ingredient: Ingredient, quantity: Int): Boolean = {
      require(quantity > 0)
      ingredientMap.getOrElse(ingredient, 0) >= quantity
    }

    override def useIngredient(ingredient: Ingredient, quantity: Int): Try[Fridge] = {
      require(quantity > 0)
      if(containsAtLeast(ingredient, quantity)) {
        val updatedQuantity = ingredientMap(ingredient) - quantity
        if(updatedQuantity > 0) {
          Success(new FridgeImpl(ingredientMap + (ingredient -> updatedQuantity)))
        } else {
          Success(new FridgeImpl(ingredientMap - ingredient))
        }

      } else {
        Failure(new IllegalArgumentException("can't use " + ingredient))
      }
    }
  }
}