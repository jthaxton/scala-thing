package domain.events

import domain.DomainEvent

case class TestEvent(data: String) extends Serializable with DomainEvent

object TestEvent {
  def build(data: String) : TestEvent = {
    TestEvent(data)
  }
}