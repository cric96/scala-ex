package it.bibo

import scala.util.{Failure, Success, Try}

class Shaker(private var fridge: Fridge) {
  private def isRecipeProducible(recipe: Recipe) : Boolean = {
    recipe.ingredients.forall { case (ingredient, quantity) => fridge.containsAtLeast(ingredient, quantity)}
  }

  private def prepareRecipe(recipe: Recipe) : Unit = {
    for(ingredientInRecipe <- recipe.ingredients) {
      val (ingredient, quantity) = ingredientInRecipe
      fridge.useIngredient(ingredient, quantity) match {
        case Success(fridgeConsumed) => fridge = fridgeConsumed
        case _ =>
      }

    }
  }

  def produce(recipe: Recipe) : Try[Cocktail] = {
    if(isRecipeProducible(recipe)) {
      prepareRecipe(recipe)
      Success(recipe.cocktail)
    } else {
      Failure(new IllegalArgumentException("no more ingredient for this recipe"))
    }
  }
}
