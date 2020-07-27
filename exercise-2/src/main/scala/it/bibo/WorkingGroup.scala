package it.bibo

trait WorkingGroup {
  def responsible : Worker
  def workers : Set[Worker]
}

case class StaticWorkingGroup(val responsible : Worker, workers : Set[Worker]) extends WorkingGroup
