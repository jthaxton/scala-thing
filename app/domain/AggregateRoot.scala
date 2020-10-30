
package domain

import akka.actor.ActorLogging
import domain.DomainEvent
import akka.persistence.PersistentActor
// import ddd.support.domain.error.AggregateRootNotInitializedException
import akka.actor.Status.Failure
// import ddd.support.domain.protocol.Acknowledged

trait TestException extends RuntimeException
class NotAuthorized extends TestException
trait AggregateState {
  type StateMachine = PartialFunction[DomainEvent, AggregateState]
  def apply: StateMachine
}

trait AggregateRoot[S <: AggregateState] extends PersistentActor with ActorLogging {

  type AggregateRootFactory = PartialFunction[DomainEvent, S]
  type EventHandler = DomainEvent => Unit
  private var stateOpt: Option[S] = None

  val factory: AggregateRootFactory

  override def receiveRecover: Receive = {
    case evt: DomainEvent => updateState(evt)
  }

  private def updateState(event: DomainEvent) {
    val nextState = if (initialized) state.apply(event) else factory.apply(event)
    stateOpt = Option(nextState.asInstanceOf[S])
  }

  def raise(event: DomainEvent)(implicit handler: EventHandler = handle) {
    persist(event) {
      persistedEvent => {
        log.info("Event persisted: {}", event)
        updateState(persistedEvent)
        handler(persistedEvent)
      }
    }
  }

  def handle(event: DomainEvent) {
    publish(event)
    sender() ! "HI"
  }

  def publish(event: DomainEvent) {
    context.system.eventStream.publish(event)
  }

  def initialized = stateOpt.isDefined

  protected def state = if (initialized) stateOpt.get else throw new NotAuthorized


  override def preRestart(reason: Throwable, message: Option[Any]) {
    sender() ! Failure(reason)
    super.preRestart(reason, message)
  }

}

