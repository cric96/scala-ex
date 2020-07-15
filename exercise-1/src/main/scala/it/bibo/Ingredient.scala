package it.bibo

sealed trait Ingredient {
  def name : String
}

case class Alcolic(alcol : Float, name : String) extends Ingredient

case class Food(name : String) extends Ingredient

case class Drink(name : String) extends Ingredient