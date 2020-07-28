package it.bibo

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TaskTest extends AnyFlatSpec with Matchers {
  val foo = StaticWorkingGroup(Worker("foo", "fooffetti"), Set())
  "A task" should "has correct bibo factor" in {
    assertThrows[IllegalArgumentException] {
      Task(10, -1, foo)
    }
    assertThrows[IllegalArgumentException] {
      Task(10, 6, foo)
    }
    //a way to check
    (0 to 5) foreach { Task(10, _, foo) }
  }
}
