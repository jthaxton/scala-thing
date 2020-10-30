package controllers

import domain.AggregateRoot
import domain.DomainEvent
import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import akka.actor._
import domain.commands.TestCommand
import domain.handlers.TestHandler
import akka.persistence.query.PersistenceQuery
/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def test() = Action { implicit request: Request[AnyContent] =>
    Ok(Json.obj("data" -> "This was successful", "errors" -> List[String]()))
    // Ok(views.html.test())
  }

  def events() = Action { implicit request: Request[AnyContent] =>
      val system = ActorSystem("MySystem")

  // println(  PersistenceQuery(system).readJournalFor["id"]("akka.persistence.query.my-read-journal")
// )


    Ok(views.html.test())
  }
  private def callWithTest() : Unit = {
    val id = "TestStream$1"
    val command = TestCommand(id)

    TestHandler.call(command)
    return null
  }
}
