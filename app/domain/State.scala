package domain

import akka.persistence.fsm.PersistentFSM.FSMState

sealed trait State extends FSMState

case object CompletedState extends State {
  override def identifier: String = "Completed"
}

case object StartedState extends State {
  override def identifier: String = "Started"
}

case object NotStartedState extends State {
  override def identifier: String = "Not Started"
}