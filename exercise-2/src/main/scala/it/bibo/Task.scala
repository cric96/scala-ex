package it.bibo

case class Task(hour : Double, biboFactor : Double, group : WorkingGroup, description : String = "") {
  require(biboFactor >= 0 && biboFactor <= 5)
}
