package domain.events

sealed trait DomainEvent

case class Added() extends DomainEvent
case class Completed() extends DomainEvent
case class Started() extends DomainEvent