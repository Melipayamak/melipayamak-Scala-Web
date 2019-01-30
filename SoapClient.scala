package services

import javax.inject.Inject
import play.mvc._
import play.libs.ws._
import java.util.concurrent.CompletionStage
import java.util

// import play.libs.Json;
// import com.fasterxml.jackson.databind.JsonNode;

class SoapClient(//implements WSBodyReadables, WSBodyWritables
                 val ws: WSClient) {
  final private val baseSoapUrl = "https://api.payamak-panel.com/post/"
  final private val _sendOp = "send.asmx"
  final private val _receiveOp = "receive.asmx"
  final private val _contactsOp = "contacts.asmx"
  final private val _actionsOp = "actions.asmx"
  final private val _scheduleOp = "schedule.asmx"
  final private val _ticketsOp = "tickets.asmx"
  final private val _usersOp = "users.asmx"
  private var username = ""
  private var password = ""

  def initCred(username: String, password: String): Unit = {
    this.username = username
    this.password = password
  }

  def SendSimpleSMS2(to: String, from: String, msg: String, flash: Boolean): CompletionStage[WSResponse] = {
    val _func = "SendSimpleSMS2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + msg + "</text><isflash>" + flash.toString() + "</isflash></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def SendSimpleSMS(to: Array[String], from: String, msg: String, flash: Boolean): CompletionStage[WSResponse] = {
    val _to = "<string>" + to.mkString("</string><string>") + "</string>"
    val _func = "SendSimpleSMS"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + from + "</from><text>" + msg + "</text><isflash>" + flash.toString() + "</isflash></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def SendSms(to: Array[String], from: String, msg: String, flash: Boolean, udh: String, recid: Array[Long]): CompletionStage[WSResponse] = {
    val _to = "<string>" + to.mkString("</string><string>") + "</string>"
    val _recid = "<long>" + recid.mkString("</long><long>") + "</long>"
    val _func = "SendSms"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + from + "</from><text>" + msg + "</text><isflash>" + flash.toString() + "</isflash><udh>" + udh + "</udh><recId>" + _recid + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def SendWithDomain(to: String, from: String, msg: String, flash: Boolean, domain: String): CompletionStage[WSResponse] = {
    val _func = "SendWithDomain"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + msg + "</text><isflash>" + flash.toString() + "</isflash><domainName>" + domain + "</domainName></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def SendByBaseNumber(text: Array[String], to: String, bodyId: Int): CompletionStage[WSResponse] = {
    val _func = "SendByBaseNumber"
    val _text = "<string>" + text.mkString("</string><string>") + "</string>"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><text>" + _text + "</text><to>" + to + "</to><bodyId>" + bodyId + "</bodyId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def SendByBaseNumber2(text: String, to: String, bodyId: Int): CompletionStage[WSResponse] = {
    val _func = "SendByBaseNumber2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><text>" + text + "</text><to>" + to + "</to><bodyId>" + bodyId + "</bodyId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }
  
  def getMessages(location: Int, from: String, index: Int, count: Int): CompletionStage[WSResponse] = {
    val _func = "getMessages"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetSmsPrice(irancellCount: Int, mtnCount: Int, from: String, text: String): CompletionStage[WSResponse] = {
    val _func = "GetSmsPrice"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><irancellCount>" + String.valueOf(irancellCount) + "</irancellCount><mtnCount>" + String.valueOf(mtnCount) + "</mtnCount><from>" + from + "</from><text>" + text + "</text></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMultiDelivery2(recId: String): CompletionStage[WSResponse] = {
    val _func = "GetMultiDelivery2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMultiDelivery(recId: String): CompletionStage[WSResponse] = {
    val _func = "GetMultiDelivery"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetInboxCount(isRead: Boolean): CompletionStage[WSResponse] = {
    val _func = "GetInboxCount"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><isRead>" + isRead.toString() + "</isRead></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetDelivery2(recId: String): CompletionStage[WSResponse] = {
    val _func = "GetDelivery2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetDelivery(recId: String): CompletionStage[WSResponse] = {
    val _func = "GetDelivery"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetDeliveries3(recIds: Array[String]): CompletionStage[WSResponse] = {
    val _recids = "<string>" + recIds.mkString("</string></string>") + "</string>"
    val _func = "GetDeliveries3"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><recId>" + _recids + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetDeliveries2(recId: String): CompletionStage[WSResponse] = {
    val _func = "GetDeliveries2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetDeliveries(recIds: Array[Long]): CompletionStage[WSResponse] = {
    val _recids = "<long>" + recIds.mkString("</long><long>") + "</long>"
    val _func = "GetDeliveries"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><recId>" + _recids + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetCredit: CompletionStage[WSResponse] = {
    val _func = "GetCredit"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  //Receive API Operations
  def RemoveMessages2(location: Int, msgIds: String): CompletionStage[WSResponse] = {
    val _func = "RemoveMessages2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><msgIds>" + msgIds + "</msgIds></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  //use Received or Sent or Removed or Deleted for location in the next method
  def RemoveMessages(location: String, msgIds: String): CompletionStage[WSResponse] = {
    val _func = "RemoveMessages"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + location + "</location><msgIds>" + msgIds + "</msgIds></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUsersMessagesByDate(location: Int, from: String, index: Int, count: Int, dateFrom: String, dateTo: String): CompletionStage[WSResponse] = {
    val _func = "GetUsersMessagesByDate"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetOutBoxCount: CompletionStage[WSResponse] = {
    val _func = "GetOutBoxCount"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesWithChangeIsRead(location: Int, from: String, index: Int, count: Int, isRead: Boolean, changeIsRead: Boolean): CompletionStage[WSResponse] = {
    val _func = "GetMessagesWithChangeIsRead"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count><isRead>" + isRead.toString() + "</isRead><changeIsRead>" + changeIsRead.toString() + "</changeIsRead></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesReceptions(msgId: Int, fromRows: Int): CompletionStage[WSResponse] = {
    val _func = "GetMessagesReceptions"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><msgId>" + String.valueOf(msgId) + "</msgId><fromRows>" + String.valueOf(fromRows) + "</fromRows></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesFilterByDate(location: Int, from: String, index: Int, count: Int, dateFrom: String, dateTo: String, isRead: Boolean): CompletionStage[WSResponse] = {
    val _func = "GetMessagesFilterByDate"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo><isRead>" + isRead.toString() + "</isRead></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesByDate(location: Int, from: String, index: Int, count: Int, dateFrom: String, dateTo: String): CompletionStage[WSResponse] = {
    val _func = "GetMessagesByDate"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesAfterIDJson(location: Int, from: String, count: Int, msgId: Int): CompletionStage[WSResponse] = {
    val _func = "GetMessagesAfterIDJson"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><count>" + String.valueOf(count) + "</count><msgId>" + String.valueOf(msgId) + "</msgId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesAfterID(location: Int, from: String, count: Int, msgId: Int): CompletionStage[WSResponse] = {
    val _func = "GetMessagesAfterID"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><count>" + String.valueOf(count) + "</count><msgId>" + String.valueOf(msgId) + "</msgId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessages(location: Int, from: String, index: Int, count: Int): CompletionStage[WSResponse] = {
    val _func = "GetMessages"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessageStr(location: Int, from: String, index: Int, count: Int): CompletionStage[WSResponse] = {
    val _func = "GetMessageStr"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ChangeMessageIsRead(msgIds: String): CompletionStage[WSResponse] = {
    val _func = "ChangeMessageIsRead"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><msgIds>" + msgIds + "</msgIds></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  //Users API Operations
  def AddPayment(name: String, family: String, bankName: String, code: String, amount: Double, cardNumber: String): CompletionStage[WSResponse] = {
    val _func = "AddPayment"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><name>" + name + "</name><family>" + family + "</family><bankName>" + bankName + "</bankName><code>" + code + "</code><amount>" + String.valueOf(amount) + "</amount><cardNumber>" + cardNumber + "</cardNumber></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddUser(productId: Int, descriptions: String, mobileNumber: String, emailAddress: String, nationalCode: String, name: String, family: String, corporation: String, phone: String, fax: String, address: String, postalCode: String, certificateNumber: String): CompletionStage[WSResponse] = {
    val _func = "AddUser"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><productId>" + String.valueOf(productId) + "</productId><descriptions>" + descriptions + "</descriptions><mobileNumber>" + mobileNumber + "</mobileNumber><emailAddress>" + emailAddress + "</emailAddress><nationalCode>" + nationalCode + "</nationalCode><name>" + name + "</name><family>" + family + "</family><corporation>" + corporation + "</corporation><phone>" + phone + "</phone><fax>" + fax + "</fax><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><certificateNumber>" + certificateNumber + "</certificateNumber></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddUserComplete(productId: Int, descriptions: String, mobileNumber: String, emailAddress: String, nationalCode: String, name: String, family: String, corporation: String, phone: String, fax: String, address: String, postalCode: String, certificateNumber: String, country: Int, province: Int, city: Int, howFindUs: String, commercialCode: String, saleId: String, recommanderId: String): CompletionStage[WSResponse] = {
    val _func = "AddUserComplete"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><productId>" + String.valueOf(productId) + "</productId><descriptions>" + descriptions + "</descriptions><mobileNumber>" + mobileNumber + "</mobileNumber><emailAddress>" + emailAddress + "</emailAddress><nationalCode>" + nationalCode + "</nationalCode><name>" + name + "</name><family>" + family + "</family><corporation>" + corporation + "</corporation><phone>" + phone + "</phone><fax>" + fax + "</fax><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><certificateNumber>" + certificateNumber + "</certificateNumber><country>" + String.valueOf(country) + "</country><province>" + String.valueOf(province) + "</province><city>" + String.valueOf(city) + "</city><howFindUs>" + howFindUs + "</howFindUs><commercialCode>" + commercialCode + "</commercialCode><saleId>" + saleId + "</saleId><recommanderId>" + recommanderId + "</recommanderId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddUserWithLocation(productId: Int, descriptions: String, mobileNumber: String, emailAddress: String, nationalCode: String, name: String, family: String, corporation: String, phone: String, fax: String, address: String, postalCode: String, certificateNumber: String, country: Int, province: Int, city: Int): CompletionStage[WSResponse] = {
    val _func = "AddUserWithLocation"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><productId>" + String.valueOf(productId) + "</productId><descriptions>" + descriptions + "</descriptions><mobileNumber>" + mobileNumber + "</mobileNumber><emailAddress>" + emailAddress + "</emailAddress><nationalCode>" + nationalCode + "</nationalCode><name>" + name + "</name><family>" + family + "</family><corporation>" + corporation + "</corporation><phone>" + phone + "</phone><fax>" + fax + "</fax><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><certificateNumber>" + certificateNumber + "</certificateNumber><country>" + String.valueOf(country) + "</country><province>" + String.valueOf(province) + "</province><city>" + String.valueOf(city) + "</city></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddUserWithMobileNumber(productId: Int, mobileNumber: String, firstName: String, lastName: String, email: String): CompletionStage[WSResponse] = {
    val _func = "AddUserWithMobileNumber"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><productId>" + String.valueOf(productId) + "</productId><mobileNumber>" + mobileNumber + "</mobileNumber><firstName>" + firstName + "</firstName><lastName>" + lastName + "</lastName><email>" + email + "</email></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddUserWithUserNameAndPass(targetUserName: String, targetUserPassword: String, productId: Int, descriptions: String, mobileNumber: String, emailAddress: String, nationalCode: String, name: String, family: String, corporation: String, phone: String, fax: String, address: String, postalCode: String, certificateNumber: String): CompletionStage[WSResponse] = {
    val _func = "AddUserWithUserNameAndPass"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUserName>" + targetUserName + "</targetUserName><targetUserPassword>" + targetUserPassword + "</targetUserPassword><productId>" + String.valueOf(productId) + "</productId><descriptions>" + descriptions + "</descriptions><mobileNumber>" + mobileNumber + "</mobileNumber><emailAddress>" + emailAddress + "</emailAddress><nationalCode>" + nationalCode + "</nationalCode><name>" + name + "</name><family>" + family + "</family><corporation>" + corporation + "</corporation><phone>" + phone + "</phone><fax>" + fax + "</fax><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><certificateNumber>" + certificateNumber + "</certificateNumber></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AuthenticateUser: CompletionStage[WSResponse] = {
    val _func = "AuthenticateUser"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ChangeUserCredit(amount: Double, description: String, targetUsername: String, GetTax: Boolean): CompletionStage[WSResponse] = {
    val _func = "ChangeUserCredit"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><amount>" + String.valueOf(amount) + "</amount><description>" + description + "</description><targetUsername>" + targetUsername + "</targetUsername><GetTax>" + GetTax.toString() + "</GetTax></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ChangeUserCredit2(amount: String, description: String, targetUsername: String, GetTax: Boolean): CompletionStage[WSResponse] = {
    val _func = "ChangeUserCredit2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><amount>" + amount + "</amount><description>" + description + "</description><targetUsername>" + targetUsername + "</targetUsername><GetTax>" + GetTax.toString() + "</GetTax></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ChangeUserCreditBySeccondPass(ausername: String, seccondPassword: String, amount: Double, description: String, targetUsername: String, GetTax: Boolean): CompletionStage[WSResponse] = {
    val _func = "ChangeUserCreditBySeccondPass"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + ausername + "</username><seccondPassword>" + seccondPassword + "</seccondPassword><amount>" + String.valueOf(amount) + "</amount><description>" + description + "</description><targetUsername>" + targetUsername + "</targetUsername><GetTax>" + GetTax.toString() + "</GetTax></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def DeductUserCredit(ausername: String, apassword: String, amount: Double, description: String): CompletionStage[WSResponse] = {
    val _func = "DeductUserCredit"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + ausername + "</username><password>" + apassword + "</password><amount>" + String.valueOf(amount) + "</amount><description>" + description + "</description></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ForgotPassword(mobileNumber: String, emailAddress: String, targetUsername: String): CompletionStage[WSResponse] = {
    val _func = "ForgotPassword"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><mobileNumber>" + mobileNumber + "</mobileNumber><emailAddress>" + emailAddress + "</emailAddress><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetCities(provinceId: Int): CompletionStage[WSResponse] = {
    val _func = "GetCities"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><provinceId>" + String.valueOf(provinceId) + "</provinceId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetEnExpireDate: CompletionStage[WSResponse] = {
    val _func = "GetEnExpireDate"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetExpireDate: CompletionStage[WSResponse] = {
    val _func = "GetExpireDate"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetProvinces: CompletionStage[WSResponse] = {
    val _func = "GetProvinces"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserBasePrice(targetUsername: String): CompletionStage[WSResponse] = {
    val _func = "GetUserBasePrice"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserByUserID(pass: String, userId: Int): CompletionStage[WSResponse] = {
    val _func = "GetUserByUserID"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><pass>" + pass + "</pass><userId>" + String.valueOf(userId) + "</userId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserCredit(targetUsername: String): CompletionStage[WSResponse] = {
    val _func = "GetUserCredit"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserCreditBySecondPass(secondPassword: String, targetUsername: String): CompletionStage[WSResponse] = {
    val _func = "GetUserCreditBySecondPass"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><secondPassword>" + secondPassword + "</secondPassword><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserDetails(targetUsername: String): CompletionStage[WSResponse] = {
    val _func = "GetUserDetails"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserIsExist(targetUsername: String): CompletionStage[WSResponse] = {
    val _func = "GetUserIsExist"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserNumbers: CompletionStage[WSResponse] = {
    val _func = "GetUserNumbers"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserTransactions(targetUsername: String, creditType: String, dateFrom: String, dateTo: String, keyword: String): CompletionStage[WSResponse] = {
    val _func = "GetUserTransactions"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername><creditType>" + creditType + "</creditType><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo><keyword>" + keyword + "</keyword></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserWallet: CompletionStage[WSResponse] = {
    val _func = "GetUserWallet"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserWalletTransaction(dateFrom: String, dateTo: String, count: Int, startIndex: Int, payType: String, PayLoc: String): CompletionStage[WSResponse] = {
    val _func = "GetUserWalletTransaction"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo><count>" + String.valueOf(count) + "</count><startIndex>" + String.valueOf(startIndex) + "</startIndex><payType>" + payType + "</payType><payLoc>" + PayLoc + "</payLoc></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUsers: CompletionStage[WSResponse] = {
    val _func = "GetUsers"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def HasFilter(text: String): CompletionStage[WSResponse] = {
    val _func = "HasFilter"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><text>" + text + "</text></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveUser(targetUsername: String): CompletionStage[WSResponse] = {
    val _func = "RemoveUser"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RevivalUser(targetUsername: String): CompletionStage[WSResponse] = {
    val _func = "RevivalUser"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUserName>" + targetUsername + "</targetUserName></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  //Contact API Operations
  def AddContact(groupIds: String, firstname: String, lastname: String, nickname: String, corporation: String, mobilenumber: String, phone: String, fax: String, birthdate: String, email: String, gender: Int, province: Int, city: Int, address: String, postalCode: String, additionaldate: String, additionaltext: String, descriptions: String): CompletionStage[WSResponse] = {
    val _func = "AddContact"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupIds>" + groupIds + "</groupIds><firstname>" + firstname + "</firstname><lastname>" + lastname + "</lastname><nickname>" + nickname + "</nickname><corporation>" + corporation + "</corporation><mobilenumber>" + mobilenumber + "</mobilenumber><phone>" + phone + "</phone><fax>" + fax + "</fax><birthdate>" + birthdate + "</birthdate><email>" + email + "</email><gender>" + String.valueOf(gender) + "</gender><province>" + String.valueOf(province) + "</province><city>" + String.valueOf(city) + "</city><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><additionaldate>" + additionaldate + "</additionaldate><additionaltext>" + additionaltext + "</additionaltext><descriptions>" + descriptions + "</descriptions></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddContactEvents(contactId: Int, eventName: String, eventType: Int, eventDate: String): CompletionStage[WSResponse] = {
    val _func = "AddContactEvents"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId><eventName>" + eventName + "</eventName><eventDate>" + eventDate + "</eventDate><eventType>" + String.valueOf(eventType) + "</eventType></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddGroup(groupName: String, Descriptions: String, showToChilds: Boolean): CompletionStage[WSResponse] = {
    val _func = "AddGroup"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupName>" + groupName + "</groupName><Descriptions>" + Descriptions + "</Descriptions><showToChilds>" + showToChilds.toString() + "</showToChilds></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ChangeContact(contactId: Int, mobilenumber: String, firstname: String, lastname: String, nickname: String, corporation: String, phone: String, fax: String, email: String, gender: Int, province: Int, city: Int, address: String, postalCode: String, additionaltext: String, descriptions: String, contactStatus: Int): CompletionStage[WSResponse] = {
    val _func = "ChangeContact"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId><mobilenumber>" + mobilenumber + "</mobilenumber><firstname>" + firstname + "</firstname><lastName>" + lastname + "</lastname><nickname>" + nickname + "</nickname><corporation>" + corporation + "</corporation><phone>" + phone + "</phone><fax>" + fax + "</fax><email>" + email + "</email><gender>" + String.valueOf(gender) + "</gender><province>" + String.valueOf(province) + "</province><city>" + String.valueOf(city) + "</city><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><additionaltext>" + additionaltext + "</additionaltext><descriptions>" + descriptions + "</descriptions><contactStatus>" + String.valueOf(contactStatus) + "</contactStatus></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ChangeGroup(groupId: Int, groupName: String, Descriptions: String, showToChilds: Boolean, groupStatus: Int): CompletionStage[WSResponse] = {
    val _func = "ChangeGroup"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupId>" + String.valueOf(groupId) + "</groupId><groupName>" + groupName + "</groupName><Descriptions>" + Descriptions + "</Descriptions><showToChilds>" + showToChilds.toString() + "</showToChilds><groupStatus>" + String.valueOf(groupStatus) + "</groupStatus></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def CheckMobileExistInContact(mobileNumber: String): CompletionStage[WSResponse] = {
    val _func = "CheckMobileExistInContact"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><mobileNumber>" + mobileNumber + "</mobileNumber></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetContactEvents(contactId: Int): CompletionStage[WSResponse] = {
    val _func = "GetContactEvents"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetContacts(groupId: Int, keyword: String, from: Int, count: Int): CompletionStage[WSResponse] = {
    val _func = "GetContacts"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupId>" + String.valueOf(groupId) + "</groupId><keyword>" + keyword + "</keyword><from>" + String.valueOf(from) + "</from><count>" + String.valueOf(count) + "</count></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetContactsByID(contactId: Int, status: Int): CompletionStage[WSResponse] = {
    val _func = "GetContactsByID"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId><status>" + String.valueOf(status) + "</status></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetGroups: CompletionStage[WSResponse] = {
    val _func = "GetGroups"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def MergeGroups(originGroupId: Int, destinationGroupId: Int, deleteOriginGroup: Boolean): CompletionStage[WSResponse] = {
    val _func = "MergeGroups"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><originGroupId>" + String.valueOf(originGroupId) + "</originGroupId><destinationGroupId>" + String.valueOf(destinationGroupId) + "</destinationGroupId><deleteOriginGroup>" + deleteOriginGroup.toString() + "</deleteOriginGroup></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveContact(mobilenumber: String): CompletionStage[WSResponse] = {
    val _func = "RemoveContact"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><mobilenumber>" + mobilenumber + "</mobilenumber></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveContactByContactID(contactId: Int): CompletionStage[WSResponse] = {
    val _func = "RemoveContactByContactID"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveGroup(groupId: Int): CompletionStage[WSResponse] = {
    val _func = "RemoveGroup"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupId>" + String.valueOf(groupId) + "</groupId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  //ACtions API Operations
  def AddBranch(branchName: String, owner: Int): CompletionStage[WSResponse] = {
    val _func = "AddBranch"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branchName>" + branchName + "</branchName><owner>" + String.valueOf(owner) + "</owner></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddBulk(from: String, branch: Int, bulkType: Int, title: String, message: String, rangeFrom: String, rangeTo: String, DateToSend: String, requestCount: Int, rowFrom: Int): CompletionStage[WSResponse] = {
    val _func = "AddBulk"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><from>" + from + "</from><branch>" + String.valueOf(branch) + "</branch><bulkType>" + String.valueOf(bulkType) + "</bulkType><title>" + title + "</title><message>" + message + "</message><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo><DateToSend>" + DateToSend + "</DateToSend><requestCount>" + String.valueOf(requestCount) + "</requestCount><rowFrom>" + String.valueOf(rowFrom) + "</rowFrom></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddBulk2(from: String, branch: Int, bulkType: Int, title: String, message: String, rangeFrom: String, rangeTo: String, DateToSend: String, requestCount: Int, rowFrom: Int): CompletionStage[WSResponse] = {
    val _func = "AddBulk2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><from>" + from + "</from><branch>" + String.valueOf(branch) + "</branch><bulkType>" + String.valueOf(bulkType) + "</bulkType><title>" + title + "</title><message>" + message + "</message><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo><DateToSend>" + DateToSend + "</DateToSend><requestCount>" + String.valueOf(requestCount) + "</requestCount><rowFrom>" + String.valueOf(rowFrom) + "</rowFrom></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddNewBulk(from: String, branch: Int, bulkType: Int, title: String, message: String, rangeFrom: String, rangeTo: String, DateToSend: String, requestCount: Int, rowFrom: Int): CompletionStage[WSResponse] = {
    val _func = "AddNewBulk"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><from>" + from + "</from><branch>" + String.valueOf(branch) + "</branch><bulkType>" + String.valueOf(bulkType) + "</bulkType><title>" + title + "</title><message>" + message + "</message><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo><DateToSend>" + DateToSend + "</DateToSend><requestCount>" + String.valueOf(requestCount) + "</requestCount><rowFrom>" + String.valueOf(rowFrom) + "</rowFrom></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddNumber(branchId: Int, mobileNumbers: Array[String]): CompletionStage[WSResponse] = {
    val _mobileNumbers = "<string>" + mobileNumbers.mkString("</string><string>") + "</string>"
    val _func = "AddNumber"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branchId>" + String.valueOf(branchId) + "</branchId><mobileNumbers>" + _mobileNumbers + "</mobileNumbers></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetBranchs(owner: Int): CompletionStage[WSResponse] = {
    val _func = "GetBranchs"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><owner>" + String.valueOf(owner) + "</owner></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetBulk: CompletionStage[WSResponse] = {
    val _func = "GetBulk"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetBulkCount(branch: Int, rangeFrom: String, rangeTo: String): CompletionStage[WSResponse] = {
    val _func = "GetBulkCount"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branch>" + String.valueOf(branch) + "</branch><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetBulkReceptions(bulkId: Int, fromRows: Int): CompletionStage[WSResponse] = {
    val _func = "GetBulkReceptions"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><bulkId>" + String.valueOf(bulkId) + "</bulkId><fromRows>" + String.valueOf(fromRows) + "</fromRows></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetBulkStatus(bulkId: Int, sent: Int, failed: Int, status: Int): CompletionStage[WSResponse] = {
    val _func = "GetBulkStatus"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><bulkId>" + String.valueOf(bulkId) + "</bulkId><sent>" + String.valueOf(sent) + "</sent><failed>" + String.valueOf(failed) + "</failed><status>" + String.valueOf(status) + "</status></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesReceptions(msgId: Long, fromRows: Int): CompletionStage[WSResponse] = {
    val _func = "GetMessagesReceptions"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><msgId>" + String.valueOf(msgId) + "</msgId><fromRows>" + String.valueOf(fromRows) + "</fromRows></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMobileCount(branch: Int, rangeFrom: String, rangeTo: String): CompletionStage[WSResponse] = {
    val _func = "GetMobileCount"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branch>" + String.valueOf(branch) + "</branch><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetSendBulk: CompletionStage[WSResponse] = {
    val _func = "GetSendBulk"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetTodaySent: CompletionStage[WSResponse] = {
    val _func = "GetTodaySent"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetTotalSent: CompletionStage[WSResponse] = {
    val _func = "GetTotalSent"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveBranch(branchId: Int): CompletionStage[WSResponse] = {
    val _func = "RemoveBranch"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branchId>" + String.valueOf(branchId) + "</branchId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveBulk(bulkId: Int): CompletionStage[WSResponse] = {
    val _func = "RemoveBulk"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><bulkId>" + String.valueOf(bulkId) + "</bulkId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def SendMultipleSMS(to: Array[String], from: String, text: Array[String], isflash: Boolean, udh: String, recId: Array[Long], status: String): CompletionStage[WSResponse] = {
    val _to = "<string>" + to.mkString("</string><string>") + "</string>"
    val _text = "<string>" + text.mkString("</string><string>") + "</string>"
    val _recId = "<long>" + recId.mkString("</long><long>") + "</long>"
    val _func = "SendMultipleSMS"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + from + "</from><text>" + _text + "</text><isflash>" + isflash.toString() + "</isflash><udh>" + udh + "</udh><recId>" + _recId + "</recId><status>" + status + "</status></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def SendMultipleSMS2(to: Array[String], from: Array[String], text: Array[String], isflash: Boolean, udh: String, recId: Array[Long], status: String): CompletionStage[WSResponse] = {
    val _to = "<string>" + to.mkString("</string><string>") + "</string>"
    val _text = "<string>" + text.mkString("</string><string>") + "</string>"
    val _from = "<string>" + from.mkString("</string><string>") + "</string>"
    val _recId = "<long>" + recId.mkString("</long><long>") + "</long>"
    val _func = "SendMultipleSMS2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + _from + "</from><text>" + _text + "</text><isflash>" + isflash.toString() + "</isflash><udh>" + udh + "</udh><recId>" + _recId + "</recId><status>" + status + "</status></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def UpdateBulkDelivery(bulkId: Int): CompletionStage[WSResponse] = {
    val _func = "UpdateBulkDelivery"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><bulkId>" + String.valueOf(bulkId) + "</bulkId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  //Schedule API Operations
  def AddMultipleSchedule(to: Array[String], from: String, text: Array[String], isflash: Boolean, scheduleDateTime: Array[String], period: String): CompletionStage[WSResponse] = {
    val _to = "<string>" + to.mkString("</string><string>") + "</string>"
    val _text = "<string>" + text.mkString("</string><string>") + "</string>"
    val _schDates = "<dateTime>" + scheduleDateTime.mkString("</dateTime><dateTime>") + "</dateTime>"
    val _func = "AddMultipleSchedule"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + from + "</from><text>" + _text + "</text><isflash>" + isflash.toString() + "</isflash><scheduleDateTime>" + _schDates + "</scheduleDateTime><period>" + period + "</period></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddNewUsance(to: String, from: String, text: String, isflash: Boolean, scheduleStartDateTime: String, countrepeat: Int, scheduleEndDateTime: String, periodType: String): CompletionStage[WSResponse] = {
    val _func = "AddNewUsance"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + text + "</text><isflash>" + isflash.toString() + "</isflash><scheduleStartDateTime>" + scheduleStartDateTime + "</scheduleStartDateTime><countrepeat>" + String.valueOf(countrepeat) + "</countrepeat><periodType>" + periodType + "</periodType></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddSchedule(to: String, from: String, text: String, isflash: Boolean, scheduleDateTime: String, period: String): CompletionStage[WSResponse] = {
    val _func = "AddSchedule"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + text + "</text><isflash>" + isflash.toString() + "</isflash><scheduleDateTime>" + scheduleDateTime + "</scheduleDateTime><period>" + period + "</period></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddUsance(to: String, from: String, text: String, isflash: Boolean, scheduleStartDateTime: String, repeatAfterDays: Int, scheduleEndDateTime: String): CompletionStage[WSResponse] = {
    val _func = "AddUsance"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + text + "</text><isflash>" + isflash.toString() + "</isflash><scheduleStartDateTime>" + scheduleStartDateTime + "</scheduleStartDateTime><repeatAfterDays>" + String.valueOf(repeatAfterDays) + "</repeatAfterDays><scheduleEndDateTime>" + scheduleEndDateTime + "</scheduleEndDateTime></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetScheduleStatus(scheduleId: Int): CompletionStage[WSResponse] = {
    val _func = "GetScheduleStatus"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><scheduleId>" + String.valueOf(scheduleId) + "</scheduleId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveSchedule(scheduleId: Int): CompletionStage[WSResponse] = {
    val _func = "RemoveSchedule"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><scheduleId>" + String.valueOf(scheduleId) + "</scheduleId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveScheduleList(scheduleIdList: Array[Int]): CompletionStage[WSResponse] = {
    val _list = "<int>" + scheduleIdList.mkString("</int><int>") + "</int>"
    val _func = "RemoveScheduleList"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><scheduleIdList>" + _list + "</scheduleIdList></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }
}
