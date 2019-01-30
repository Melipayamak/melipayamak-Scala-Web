package services

import javax.inject.Inject
import play.mvc._
import play.libs.ws._
import java.util.concurrent.CompletionStage
import play.libs.Json
import com.fasterxml.jackson.databind.JsonNode


class RestClient(//implements WSBodyReadables, WSBodyWritables
                 val ws: WSClient) {
  final private val baseRestUrl = "https://rest.payamak-panel.com/api/SendSMS/"
  private var username = ""
  private var password = ""

  def initCred(username: String, password: String): Unit = {
    this.username = username
    this.password = password
  }

  def SendSMS(to: String, from: String, text: String, flash: Boolean): CompletionStage[WSResponse] = {
    val json = Json.newObject.put("username", username).put("password", password).put("to", to).put("from", from).put("text", text).put("isFlash", flash.toString())
    ws.url(baseRestUrl + "SendSMS").addHeader("Content-Type", "application/json").post(json)
    //.thenApply(response ->
    //ok("result: " + response.getBody())//.asJson().findValues("full_name")
    //);    
  }
  
  def SendByBaseNumber(text: String, to: String, bodyId: Int): CompletionStage[WSResponse] = {
    val json = Json.newObject.put("username", username).put("password", password).put("text", text).put("to", to).put("bodyId", String.valueOf(bodyId))
    ws.url(baseRestUrl + "BaseServiceNumber").addHeader("Content-Type", "application/json").post(json)
  }

  def GetDeliveries2(recId: Long): CompletionStage[WSResponse] = {
    val json = Json.newObject.put("username", username).put("password", password).put("recId", String.valueOf(recId))
    ws.url(baseRestUrl + "GetDeliveries2").addHeader("Content-Type", "application/json").post(json)
  }

  def GetMessages(location: Int, from: String, index: String, count: Boolean): CompletionStage[WSResponse] = {
    val json = Json.newObject.put("username", username).put("password", password).put("location", String.valueOf(location)).put("from", from).put("index", index).put("count", count.toString())
    ws.url(baseRestUrl + "GetMessages").addHeader("Content-Type", "application/json").post(json)
  }

  def GetCredit: CompletionStage[WSResponse] = {
    val json = Json.newObject.put("username", username).put("password", password)

    ws.url(baseRestUrl + "GetCredit").addHeader("Content-Type", "application/json").post(json)
  }

  def GetBasePrice(recId: Long): CompletionStage[WSResponse] = {
    val json = Json.newObject.put("username", username).put("password", password)
    ws.url(baseRestUrl + "GetBasePrice").addHeader("Content-Type", "application/json").post(json)
  }

  def GetUserNumbers(recId: Long): CompletionStage[WSResponse] = {
    val json = Json.newObject.put("username", username).put("password", password)
    ws.url(baseRestUrl + "GetUserNumbers").addHeader("Content-Type", "application/json").post(json)
  }
}
