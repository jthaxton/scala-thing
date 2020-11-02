package domain

import akka.persistence.fsm._
import akka.persistence.{SaveSnapshotFailure, SaveSnapshotSuccess}
import akka.actor._
import domain._
import scala.reflect._

object ToDo {
}

case class ToDo() {
  var state : String = "not_started"
  def start() : String = {
    state = "started"
    state
  }

  def complete() : String = {
    state = "completed"
    state
  }

  def getState() : String = {
    state
  }
}