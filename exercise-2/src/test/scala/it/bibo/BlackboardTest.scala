package it.bibo

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.util.Success

class BlackboardTest extends AnyFlatSpec with Matchers {
  import BlackboardTest._

  "A blackboard" should "add new category" in {
    val blackboard = Blackboard.empty()
    blackboard.addCategory("bibo") shouldBe true
  }

  it should "not allow inserting the same category multiple times" in {
    val blackboard = Blackboard.empty()
    blackboard.addCategory("bibo")
    blackboard.addCategory("bibo") shouldBe false
  }

  it should "allow to add different categories" in {
    val blackboard = Blackboard.empty()
    blackboard.addCategory("bib")
    blackboard.addCategory("futy")
  }

  it should "allow to add new task" in {
    val blackboard = Blackboard.empty()
    blackboard.addCategory("bibo")
    blackboard.addTask(taskA, "bibo") shouldBe Success()
    blackboard.getCategory(taskA) shouldBe Some("bibo")
  }

  it should "not allow adding a task in unsupported category" in {
    val blackboard = Blackboard.empty()
    blackboard.addTask(taskA, "bibo").isFailure shouldBe true
  }

  it should "not allow adding the same task multiple times" in {
    val blackboard = Blackboard.empty()
    blackboard.addCategory("bibo")
    blackboard.addTask(taskA, "bibo")
    blackboard.addTask(taskA, "bibo").isFailure shouldBe true
  }

  it should "allow to move a task among categories" in {
    val blackboard = Blackboard.empty()
    blackboard.addCategory("bibo")
    blackboard.addCategory("futy")
    blackboard.addTask(taskA, "bibo")
    blackboard.moveTaskTo(taskA, "futy") shouldBe Success()
    blackboard.getCategory(taskA) shouldBe Some("futy")
  }

  it should "not allow to move task in an unkwon category" in {
    val blackboard = Blackboard.empty()
    blackboard.addCategory("bibo")
    blackboard.addTask(taskA, "bibo")
    blackboard.moveTaskTo(taskA, "futy").isFailure shouldBe true
  }

  it should "not allow to move a task not inserted in the blackboard" in {
    val blackboard = Blackboard.empty()
    blackboard.addCategory("bibo")
    blackboard.moveTaskTo(taskA, "bibo").isFailure shouldBe true
  }

  it should "not allow to move a task in the same category" in {
    val blackboard = Blackboard.empty()
    blackboard.addCategory("bibo")
    blackboard.addTask(taskA, "bibo")
    blackboard.moveTaskTo(taskA, "bibo").isFailure shouldBe true
  }
}

object BlackboardTest {
  val bestMan = Worker("mone", "letizi")
  val recchia = Worker("pedro", "petreti")
  val myDama = Worker("lemmy", "del luffarello")

  val group = StaticWorkingGroup(bestMan, Set(recchia, myDama))

  val taskA = Task(10, 4, group, "a task very very bibo")
  val taskB = Task(10, 1, group, "task tasketti ranseghetti")
  val taskC = Task(1, 5, group, "task spaghetti futonetti")
}