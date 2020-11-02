package domain

import akka.persistence.fsm._
import akka.persistence.{SaveSnapshotFailure, SaveSnapshotSuccess}
import akka.actor._
import domain._
import domain.events._
import scala.reflect._
import domain.commands._

class ToDoHandler extends PersistentFSM[State, ToDo, DomainEvent] {

  override def persistenceId: String = context.self.path.toString

  override def onRecoveryCompleted(): Unit = log.info("Recovery completed!")

  override def domainEventClassTag: ClassTag[DomainEvent] = classTag[DomainEvent]


  val newToDo = new ToDo()
  startWith(NotStartedState, newToDo)

  when(NotStartedState) {
    case Event(Start(), _) => 
      println("HERE")
      log.info("Todo started")
      goto(StartedState) applying Started()
    case Event(Complete(), _) =>
      log.info("Todo Finished")
      goto(CompletedState) applying Completed()
    case _ => 
      goto(NotStartedState)
  }
  
  override def applyEvent(domainEvent: DomainEvent, data: ToDo): ToDo = {
    domainEvent match {
      case (Started()) =>
        data
      case (Completed()) =>
        data
    }
  }

}