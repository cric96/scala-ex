package it.bibo

import com.sun.org.apache.bcel.internal.classfile.ConstantClass

object Context {
  val wine = Alcolic(0.13f, "vinoh")
  val water = Drink("water")
  val lemon = Food("lemon")

  val waterAndLemon = Cocktail("water and lemon", Fruity)
  val waterAndLemonRecipe = Recipe(Map(water -> 1, lemon -> 2), waterAndLemon)

  val wineStretchedWithWater = Cocktail("wine stretched with water", Dry)
  val wineStretchedWithWaterRecipe = Recipe(Map(water -> 2, wine -> 1), wineStretchedWithWater)

  val baseFridge = Fridge()
    .insertIngredient(wine, 2)
    .insertIngredient(lemon, 3)
    .insertIngredient(water, 3)
}
