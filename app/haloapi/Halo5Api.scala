package haloapi

import javax.inject._

import com.google.inject.ConfigurationException
import play.api.{Configuration, Play}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.ws.{WSClient, WSResponse}

class Halo5Api @Inject() (ws: WSClient, configuration: Configuration) {
  val primaryKey = configuration.getString("halo.api.primary").getOrElse(throw new RuntimeException("Halo 5 API primary key not configured"))
  val subscriptionKeyHeader = "Ocp-Apim-Subscription-Key"

  def getMatchesForUser(gamerTag: String) = {
    val urlTemplate = s"https://www.haloapi.com/stats/h5/players/$gamerTag/matches"
    val request = ws.url(urlTemplate).withHeaders(subscriptionKeyHeader -> primaryKey)
    request.get().map(transformMatchResponse)
  }

  def transformMatchResponse(response: WSResponse) = {
    response.json
  }
}
