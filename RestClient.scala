package services

import javax.inject.Inject
import play.mvc._
import play.libs.ws._
import java.util.concurrent.CompletionStage
import play.libs.Json
import com.fasterxml.jackson.databind.JsonNode


class RestClient(//implements WSBodyReadables, WSBodyWritables
                 val ws: Nothing) {
  final private val baseRestUrl = "https://rest.payamak-panel.com/api/SendSMS/"
  private var username = null
  private var password = null

  def initCred(username: Nothing, password: Nothing): Unit = {
    this.username = username
    this.password = password
  }

  def SendSMS(to: Nothing, from: Nothing, text: Nothing, flash: Boolean): Nothing = {
    val json = Json.newObject.put("username", username).put("password", password).put("to", to).put("from", from).put("text", text).put("isFlash", Boolean.toString(flash))
    ws.url(baseRestUrl + "SendSMS").addHeader("Content-Type", "application/json").post(json)
    //.thenApply(response ->
    //ok("result: " + response.getBody())//.asJson().findValues("full_name")
    //);    
  }

  def GetDeliveries2(recId: Long): Nothing = {
    val json = Json.newObject.put("username", username).put("password", password).put("recId", String.valueOf(recId))
    ws.url(baseRestUrl + "GetDeliveries2").addHeader("Content-Type", "application/json").post(json)
  }

  def GetMessages(location: Int, from: Nothing, index: Nothing, count: Boolean): Nothing = {
    val json = Json.newObject.put("username", username).put("password", password).put("location", String.valueOf(location)).put("from", from).put("index", index).put("count", Boolean.toString(count))
    ws.url(baseRestUrl + "GetMessages").addHeader("Content-Type", "application/json").post(json)
  }

  def GetCredit: Nothing = {
    val json = Json.newObject.put("username", username).put("password", password)

    ws.url(baseRestUrl + "GetCredit").addHeader("Content-Type", "application/json").post(json)
  }

  def GetBasePrice(recId: Long): Nothing = {
    val json = Json.newObject.put("username", username).put("password", password)
    ws.url(baseRestUrl + "GetBasePrice").addHeader("Content-Type", "application/json").post(json)
  }

  def GetUserNumbers(recId: Long): Nothing = {
    val json = Json.newObject.put("username", username).put("password", password)
    ws.url(baseRestUrl + "GetUserNumbers").addHeader("Content-Type", "application/json").post(json)
  }
}
