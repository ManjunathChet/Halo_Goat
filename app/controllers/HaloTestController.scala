package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global
import haloapi.Halo5Api


class HaloTestController @Inject() (api: Halo5Api) extends Controller {
  def index = Action.async {
    api.getMatchesForUser().map(Ok(_))
  }
}
