package it.bibo

import scala.util.{Failure, Success, Try}

trait Blackboard {
  def addCategory(category : String) : Boolean
  def getCategory(task : Task) : Option[String]
  def addTask(task: Task, category : String) : Try[Unit]
  def moveTaskTo(task : Task, category : String) : Try[Unit]
}

private object Blackboard {
  class BlackboardImpl(private var map : Map[String, Set[Task]]) extends Blackboard {
    override def addCategory(category: String): Boolean = if(map.contains(category)) {
      false
    } else {
      map += category -> Set()
      true
    }

    override def addTask(task: Task, category: String): Try[Unit] = map.get(category) match {
      case Some(tasks) if tasks.contains(task) => Failure(new IllegalStateException("task already present"))
      case Some(tasks) =>
        map += category -> (tasks + task)
        Success()
      case None => Failure(new IllegalStateException("category not present"))

    }

    override def moveTaskTo(task: Task, category: String): Try[Unit] = map.get(category) match {
      case Some(tasks) if isTaskMoveable(task, category) =>
        map += category -> (tasks + task)
        val startCategory = getCategory(task).get
        map += startCategory -> (map(startCategory) + task)
        Success()
      case Some(tasks) => Failure(new IllegalStateException("cannot move task"))
      case None =>  Failure(new IllegalStateException("category not present"))
    }

    override def getCategory(task : Task) : Option[String] = map.find(_._2.contains(task)).map(_._1)

    def isTaskMoveable(task : Task, targetCategory : String) : Boolean = getCategory(task) match {
      case Some(`targetCategory`) => false
      case Some(_) => true
      case _ => false
    }
  }
}


