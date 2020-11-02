package domain

import akka.persistence.fsm._
import akka.persistence.{SaveSnapshotFailure, SaveSnapshotSuccess}
import akka.actor._
import domain._
import domain.events._
import scala.reflect._



 case class User()  {
  import User._

  // override def persistenceId: String = context.self.path.toString

  // override def onRecoveryCompleted(): Unit = log.info("Recovery completed!")

  // override def domainEventClassTag: ClassTag[DomainEvent] = classTag[DomainEvent]

  //   override def applyEvent(domainEvent: DomainEvent, todo: ToDo): Unit = {
  //   domainEvent match {
  //     case StartToDo(name) =>
  //       todo.start()
  //     case FinishToDo(name) =>
  //       todo.finish()
  //   }
  // }
}

object User {
  case class StartToDo(name: String)
  case class FinishToDo(name: String)
}