package domain.handlers

import domain.events.TestEvent
import domain.commands.TestCommand
import domain.AggregateRoot
import akka.actor._
import domain.TestAggregate

class TestHandler

object TestHandler {
  def call(command: TestCommand) : Unit = {
    val event = TestEvent.build(command.data)
    val system = ActorSystem("MySystem")
    // TestAggregate().publish(event)
    // val myActor = system.actorOf(Props[TestAggregate](), name = "myactor")
    system.eventStream.publish(event)
    return null
  }
}