package services

import javax.inject.Inject
import play.mvc._
import play.libs.ws._
import java.util.concurrent.CompletionStage
import java.util

// import play.libs.Json;
// import com.fasterxml.jackson.databind.JsonNode;

class SoapClient(//implements WSBodyReadables, WSBodyWritables
                 val ws: Nothing) {
  final private val baseSoapUrl = "https://api.payamak-panel.com/post/"
  final private val _sendOp = "send.asmx"
  final private val _receiveOp = "receive.asmx"
  final private val _contactsOp = "contacts.asmx"
  final private val _actionsOp = "actions.asmx"
  final private val _scheduleOp = "schedule.asmx"
  final private val _ticketsOp = "tickets.asmx"
  final private val _usersOp = "users.asmx"
  private var username = null
  private var password = null

  def initCred(username: Nothing, password: Nothing): Unit = {
    this.username = username
    this.password = password
  }

  def SendSimpleSMS2(to: Nothing, from: Nothing, msg: Nothing, flash: Boolean): Nothing = {
    val _func = "SendSimpleSMS2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + msg + "</text><isflash>" + Boolean.toString(flash) + "</isflash></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def SendSimpleSMS(to: Array[Nothing], from: Nothing, msg: Nothing, flash: Nothing): Nothing = {
    val _to = "<string>" + String.join("</string><string>", to) + "</string>"
    val _func = "SendSimpleSMS"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + from + "</from><text>" + msg + "</text><isflash>" + Boolean.toString(flash) + "</isflash></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def SendSms(to: Array[Nothing], from: Nothing, msg: Nothing, flash: Boolean, udh: Nothing, recid: Array[Long]): Nothing = {
    val _to = "<string>" + String.join("</string><string>", to) + "</string>"
    val _recid = "<long>" + String.join("</long><long>", Arrays.toString(recid)) + "</long>"
    val _func = "SendSms"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + from + "</from><text>" + msg + "</text><isflash>" + Boolean.toString(flash) + "</isflash><udh>" + udh + "</udh><recId>" + _recid + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def SendWithDomain(to: Nothing, from: Nothing, msg: Nothing, flash: Boolean, domain: Nothing): Nothing = {
    val _func = "SendWithDomain"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + msg + "</text><isflash>" + Boolean.toString(flash) + "</isflash><domainName>" + domain + "</domainName></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def getMessages(location: Int, from: Nothing, index: Int, count: Int): Nothing = {
    val _func = "getMessages"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetSmsPrice(irancellCount: Int, mtnCount: Int, from: Nothing, text: Nothing): Nothing = {
    val _func = "GetSmsPrice"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><irancellCount>" + String.valueOf(irancellCount) + "</irancellCount><mtnCount>" + String.valueOf(mtnCount) + "</mtnCount><from>" + from + "</from><text>" + text + "</text></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMultiDelivery2(recId: Nothing): Nothing = {
    val _func = "GetMultiDelivery2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMultiDelivery(recId: Nothing): Nothing = {
    val _func = "GetMultiDelivery"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetInboxCount(isRead: Boolean): Nothing = {
    val _func = "GetInboxCount"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><isRead>" + Boolean.toString(isRead) + "</isRead></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetDelivery2(recId: Nothing): Nothing = {
    val _func = "GetDelivery2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetDelivery(recId: Nothing): Nothing = {
    val _func = "GetDelivery"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetDeliveries3(recIds: Array[Nothing]): Nothing = {
    val _recids = "<string>" + String.join("</string><string>", recIds) + "</string>"
    val _func = "GetDeliveries3"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><recId>" + _recids + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetDeliveries2(recId: Nothing): Nothing = {
    val _func = "GetDeliveries2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><recId>" + recId + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetDeliveries(recIds: Array[Long]): Nothing = {
    val _recids = "<long>" + String.join("</long><long>", Arrays.toString(recIds)) + "</long>"
    val _func = "GetDeliveries"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><recId>" + _recids + "</recId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetCredit: Nothing = {
    val _func = "GetCredit"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _sendOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  //Receive API Operations
  def RemoveMessages2(location: Int, msgIds: Nothing): Nothing = {
    val _func = "RemoveMessages2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><msgIds>" + msgIds + "</msgIds></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  //use Received or Sent or Removed or Deleted for location in the next method
  def RemoveMessages(location: Nothing, msgIds: Nothing): Nothing = {
    val _func = "RemoveMessages"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + location + "</location><msgIds>" + msgIds + "</msgIds></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUsersMessagesByDate(location: Int, from: Nothing, index: Int, count: Int, dateFrom: Nothing, dateTo: Nothing): Nothing = {
    val _func = "GetUsersMessagesByDate"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetOutBoxCount: Nothing = {
    val _func = "GetOutBoxCount"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesWithChangeIsRead(location: Int, from: Nothing, index: Int, count: Int, isRead: Boolean, changeIsRead: Boolean): Nothing = {
    val _func = "GetMessagesWithChangeIsRead"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count><isRead>" + Boolean.toString(isRead) + "</isRead><changeIsRead>" + Boolean.toString(changeIsRead) + "</changeIsRead></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesReceptions(msgId: Int, fromRows: Int): Nothing = {
    val _func = "GetMessagesReceptions"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><msgId>" + String.valueOf(msgId) + "</msgId><fromRows>" + String.valueOf(fromRows) + "</fromRows></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesFilterByDate(location: Int, from: Nothing, index: Int, count: Int, dateFrom: Nothing, dateTo: Nothing, isRead: Boolean): Nothing = {
    val _func = "GetMessagesFilterByDate"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo><isRead>" + Boolean.toString(isRead) + "</isRead></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesByDate(location: Int, from: Nothing, index: Int, count: Int, dateFrom: Nothing, dateTo: Nothing): Nothing = {
    val _func = "GetMessagesByDate"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesAfterIDJson(location: Int, from: Nothing, count: Int, msgId: Int): Nothing = {
    val _func = "GetMessagesAfterIDJson"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><count>" + String.valueOf(count) + "</count><msgId>" + String.valueOf(msgId) + "</msgId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesAfterID(location: Int, from: Nothing, count: Int, msgId: Int): Nothing = {
    val _func = "GetMessagesAfterID"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><count>" + String.valueOf(count) + "</count><msgId>" + String.valueOf(msgId) + "</msgId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessages(location: Int, from: Nothing, index: Int, count: Int): Nothing = {
    val _func = "GetMessages"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessageStr(location: Int, from: Nothing, index: Int, count: Int): Nothing = {
    val _func = "GetMessageStr"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><location>" + String.valueOf(location) + "</location><from>" + from + "</from><index>" + String.valueOf(index) + "</index><count>" + String.valueOf(count) + "</count></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ChangeMessageIsRead(msgIds: Nothing): Nothing = {
    val _func = "ChangeMessageIsRead"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><msgIds>" + msgIds + "</msgIds></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _receiveOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  //Users API Operations
  def AddPayment(name: Nothing, family: Nothing, bankName: Nothing, code: Nothing, amount: Double, cardNumber: Nothing): Nothing = {
    val _func = "AddPayment"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><name>" + name + "</name><family>" + family + "</family><bankName>" + bankName + "</bankName><code>" + code + "</code><amount>" + String.valueOf(amount) + "</amount><cardNumber>" + cardNumber + "</cardNumber></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddUser(productId: Int, descriptions: Nothing, mobileNumber: Nothing, emailAddress: Nothing, nationalCode: Nothing, name: Nothing, family: Nothing, corporation: Nothing, phone: Nothing, fax: Nothing, address: Nothing, postalCode: Nothing, certificateNumber: Nothing): Nothing = {
    val _func = "AddUser"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><productId>" + String.valueOf(productId) + "</productId><descriptions>" + descriptions + "</descriptions><mobileNumber>" + mobileNumber + "</mobileNumber><emailAddress>" + emailAddress + "</emailAddress><nationalCode>" + nationalCode + "</nationalCode><name>" + name + "</name><family>" + family + "</family><corporation>" + corporation + "</corporation><phone>" + phone + "</phone><fax>" + fax + "</fax><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><certificateNumber>" + certificateNumber + "</certificateNumber></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddUserComplete(productId: Int, descriptions: Nothing, mobileNumber: Nothing, emailAddress: Nothing, nationalCode: Nothing, name: Nothing, family: Nothing, corporation: Nothing, phone: Nothing, fax: Nothing, address: Nothing, postalCode: Nothing, certificateNumber: Nothing, country: Int, province: Int, city: Int, howFindUs: Nothing, commercialCode: Nothing, saleId: Nothing, recommanderId: Nothing): Nothing = {
    val _func = "AddUserComplete"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><productId>" + String.valueOf(productId) + "</productId><descriptions>" + descriptions + "</descriptions><mobileNumber>" + mobileNumber + "</mobileNumber><emailAddress>" + emailAddress + "</emailAddress><nationalCode>" + nationalCode + "</nationalCode><name>" + name + "</name><family>" + family + "</family><corporation>" + corporation + "</corporation><phone>" + phone + "</phone><fax>" + fax + "</fax><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><certificateNumber>" + certificateNumber + "</certificateNumber><country>" + String.valueOf(country) + "</country><province>" + String.valueOf(province) + "</province><city>" + String.valueOf(city) + "</city><howFindUs>" + howFindUs + "</howFindUs><commercialCode>" + commercialCode + "</commercialCode><saleId>" + saleId + "</saleId><recommanderId>" + recommanderId + "</recommanderId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddUserWithLocation(productId: Int, descriptions: Nothing, mobileNumber: Nothing, emailAddress: Nothing, nationalCode: Nothing, name: Nothing, family: Nothing, corporation: Nothing, phone: Nothing, fax: Nothing, address: Nothing, postalCode: Nothing, certificateNumber: Nothing, country: Int, province: Int, city: Int): Nothing = {
    val _func = "AddUserWithLocation"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><productId>" + String.valueOf(productId) + "</productId><descriptions>" + descriptions + "</descriptions><mobileNumber>" + mobileNumber + "</mobileNumber><emailAddress>" + emailAddress + "</emailAddress><nationalCode>" + nationalCode + "</nationalCode><name>" + name + "</name><family>" + family + "</family><corporation>" + corporation + "</corporation><phone>" + phone + "</phone><fax>" + fax + "</fax><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><certificateNumber>" + certificateNumber + "</certificateNumber><country>" + String.valueOf(country) + "</country><province>" + String.valueOf(province) + "</province><city>" + String.valueOf(city) + "</city></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddUserWithMobileNumber(productId: Int, mobileNumber: Nothing, firstName: Nothing, lastName: Nothing, email: Nothing): Nothing = {
    val _func = "AddUserWithMobileNumber"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><productId>" + String.valueOf(productId) + "</productId><mobileNumber>" + mobileNumber + "</mobileNumber><firstName>" + firstName + "</firstName><lastName>" + lastName + "</lastName><email>" + email + "</email></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddUserWithUserNameAndPass(targetUserName: Nothing, targetUserPassword: Nothing, productId: Int, descriptions: Nothing, mobileNumber: Nothing, emailAddress: Nothing, nationalCode: Nothing, name: Nothing, family: Nothing, corporation: Nothing, phone: Nothing, fax: Nothing, address: Nothing, postalCode: Nothing, certificateNumber: Nothing): Nothing = {
    val _func = "AddUserWithUserNameAndPass"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUserName>" + targetUserName + "</targetUserName><targetUserPassword>" + targetUserPassword + "</targetUserPassword><productId>" + String.valueOf(productId) + "</productId><descriptions>" + descriptions + "</descriptions><mobileNumber>" + mobileNumber + "</mobileNumber><emailAddress>" + emailAddress + "</emailAddress><nationalCode>" + nationalCode + "</nationalCode><name>" + name + "</name><family>" + family + "</family><corporation>" + corporation + "</corporation><phone>" + phone + "</phone><fax>" + fax + "</fax><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><certificateNumber>" + certificateNumber + "</certificateNumber></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AuthenticateUser: Nothing = {
    val _func = "AuthenticateUser"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ChangeUserCredit(amount: Double, description: Nothing, targetUsername: Nothing, GetTax: Boolean): Nothing = {
    val _func = "ChangeUserCredit"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><amount>" + String.valueOf(amount) + "</amount><description>" + description + "</description><targetUsername>" + targetUsername + "</targetUsername><GetTax>" + Boolean.toString(GetTax) + "</GetTax></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ChangeUserCredit2(amount: Nothing, description: Nothing, targetUsername: Nothing, GetTax: Boolean): Nothing = {
    val _func = "ChangeUserCredit2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><amount>" + amount + "</amount><description>" + description + "</description><targetUsername>" + targetUsername + "</targetUsername><GetTax>" + Boolean.toString(GetTax) + "</GetTax></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ChangeUserCreditBySeccondPass(ausername: Nothing, seccondPassword: Nothing, amount: Double, description: Nothing, targetUsername: Nothing, GetTax: Boolean): Nothing = {
    val _func = "ChangeUserCreditBySeccondPass"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + ausername + "</username><seccondPassword>" + seccondPassword + "</seccondPassword><amount>" + String.valueOf(amount) + "</amount><description>" + description + "</description><targetUsername>" + targetUsername + "</targetUsername><GetTax>" + Boolean.toString(GetTax) + "</GetTax></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def DeductUserCredit(ausername: Nothing, apassword: Nothing, amount: Double, description: Nothing): Nothing = {
    val _func = "DeductUserCredit"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + ausername + "</username><password>" + apassword + "</password><amount>" + String.valueOf(amount) + "</amount><description>" + description + "</description></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ForgotPassword(mobileNumber: Nothing, emailAddress: Nothing, targetUsername: Nothing): Nothing = {
    val _func = "ForgotPassword"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><mobileNumber>" + mobileNumber + "</mobileNumber><emailAddress>" + emailAddress + "</emailAddress><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetCities(provinceId: Int): Nothing = {
    val _func = "GetCities"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><provinceId>" + String.valueOf(provinceId) + "</provinceId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetEnExpireDate: Nothing = {
    val _func = "GetEnExpireDate"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetExpireDate: Nothing = {
    val _func = "GetExpireDate"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetProvinces: Nothing = {
    val _func = "GetProvinces"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserBasePrice(targetUsername: Nothing): Nothing = {
    val _func = "GetUserBasePrice"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserByUserID(pass: Nothing, userId: Int): Nothing = {
    val _func = "GetUserByUserID"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><pass>" + pass + "</pass><userId>" + String.valueOf(userId) + "</userId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserCredit(targetUsername: Nothing): Nothing = {
    val _func = "GetUserCredit"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserCreditBySecondPass(secondPassword: Nothing, targetUsername: Nothing): Nothing = {
    val _func = "GetUserCreditBySecondPass"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><secondPassword>" + secondPassword + "</secondPassword><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserDetails(targetUsername: Nothing): Nothing = {
    val _func = "GetUserDetails"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserIsExist(targetUsername: Nothing): Nothing = {
    val _func = "GetUserIsExist"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserNumbers: Nothing = {
    val _func = "GetUserNumbers"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserTransactions(targetUsername: Nothing, creditType: Nothing, dateFrom: Nothing, dateTo: Nothing, keyword: Nothing): Nothing = {
    val _func = "GetUserTransactions"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername><creditType>" + creditType + "</creditType><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo><keyword>" + keyword + "</keyword></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserWallet: Nothing = {
    val _func = "GetUserWallet"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUserWalletTransaction(dateFrom: Nothing, dateTo: Nothing, count: Int, startIndex: Int, payType: Nothing, PayLoc: Nothing): Nothing = {
    val _func = "GetUserWalletTransaction"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><dateFrom>" + dateFrom + "</dateFrom><dateTo>" + dateTo + "</dateTo><count>" + String.valueOf(count) + "</count><startIndex>" + String.valueOf(startIndex) + "</startIndex><payType>" + payType + "</payType><payLoc>" + PayLoc + "</payLoc></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetUsers: Nothing = {
    val _func = "GetUsers"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def HasFilter(text: Nothing): Nothing = {
    val _func = "HasFilter"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><text>" + text + "</text></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveUser(targetUsername: Nothing): Nothing = {
    val _func = "RemoveUser"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUsername>" + targetUsername + "</targetUsername></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RevivalUser(targetUsername: Nothing): Nothing = {
    val _func = "RevivalUser"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><targetUserName>" + targetUsername + "</targetUserName></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _usersOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  //Contact API Operations
  def AddContact(groupIds: Nothing, firstname: Nothing, lastname: Nothing, nickname: Nothing, corporation: Nothing, mobilenumber: Nothing, phone: Nothing, fax: Nothing, birthdate: Nothing, email: Nothing, gender: Int, province: Int, city: Int, address: Nothing, postalCode: Nothing, additionaldate: Nothing, additionaltext: Nothing, descriptions: Nothing): Nothing = {
    val _func = "AddContact"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupIds>" + groupIds + "</groupIds><firstname>" + firstname + "</firstname><lastname>" + lastname + "</lastname><nickname>" + nickname + "</nickname><corporation>" + corporation + "</corporation><mobilenumber>" + mobilenumber + "</mobilenumber><phone>" + phone + "</phone><fax>" + fax + "</fax><birthdate>" + birthdate + "</birthdate><email>" + email + "</email><gender>" + String.valueOf(gender) + "</gender><province>" + String.valueOf(province) + "</province><city>" + String.valueOf(city) + "</city><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><additionaldate>" + additionaldate + "</additionaldate><additionaltext>" + additionaltext + "</additionaltext><descriptions>" + descriptions + "</descriptions></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddContactEvents(contactId: Int, eventName: Nothing, eventType: Int, eventDate: Nothing): Nothing = {
    val _func = "AddContactEvents"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId><eventName>" + eventName + "</eventName><eventDate>" + eventDate + "</eventDate><eventType>" + String.valueOf(eventType) + "</eventType></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddGroup(groupName: Nothing, Descriptions: Nothing, showToChilds: Boolean): Nothing = {
    val _func = "AddGroup"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupName>" + groupName + "</groupName><Descriptions>" + Descriptions + "</Descriptions><showToChilds>" + Boolean.toString(showToChilds) + "</showToChilds></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ChangeContact(contactId: Int, mobilenumber: Nothing, firstname: Nothing, lastname: Nothing, nickname: Nothing, corporation: Nothing, phone: Nothing, fax: Nothing, email: Nothing, gender: Int, province: Int, city: Int, address: Nothing, postalCode: Nothing, additionaltext: Nothing, descriptions: Nothing, contactStatus: Int): Nothing = {
    val _func = "ChangeContact"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId><mobilenumber>" + mobilenumber + "</mobilenumber><firstname>" + firstname + "</firstname><lastName>" + lastname + "</lastname><nickname>" + nickname + "</nickname><corporation>" + corporation + "</corporation><phone>" + phone + "</phone><fax>" + fax + "</fax><email>" + email + "</email><gender>" + String.valueOf(gender) + "</gender><province>" + String.valueOf(province) + "</province><city>" + String.valueOf(city) + "</city><address>" + address + "</address><postalCode>" + postalCode + "</postalCode><additionaltext>" + additionaltext + "</additionaltext><descriptions>" + descriptions + "</descriptions><contactStatus>" + String.valueOf(contactStatus) + "</contactStatus></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def ChangeGroup(groupId: Int, groupName: Nothing, Descriptions: Nothing, showToChilds: Boolean, groupStatus: Int): Nothing = {
    val _func = "ChangeGroup"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupId>" + String.valueOf(groupId) + "</groupId><groupName>" + groupName + "</groupName><Descriptions>" + Descriptions + "</Descriptions><showToChilds>" + Boolean.toString(showToChilds) + "</showToChilds><groupStatus>" + String.valueOf(groupStatus) + "</groupStatus></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def CheckMobileExistInContact(mobileNumber: Nothing): Nothing = {
    val _func = "CheckMobileExistInContact"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><mobileNumber>" + mobileNumber + "</mobileNumber></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetContactEvents(contactId: Int): Nothing = {
    val _func = "GetContactEvents"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetContacts(groupId: Int, keyword: Nothing, from: Int, count: Int): Nothing = {
    val _func = "GetContacts"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupId>" + String.valueOf(groupId) + "</groupId><keyword>" + keyword + "</keyword><from>" + String.valueOf(from) + "</from><count>" + String.valueOf(count) + "</count></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetContactsByID(contactId: Int, status: Int): Nothing = {
    val _func = "GetContactsByID"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId><status>" + String.valueOf(status) + "</status></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetGroups: Nothing = {
    val _func = "GetGroups"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def MergeGroups(originGroupId: Int, destinationGroupId: Int, deleteOriginGroup: Boolean): Nothing = {
    val _func = "MergeGroups"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><originGroupId>" + String.valueOf(originGroupId) + "</originGroupId><destinationGroupId>" + String.valueOf(destinationGroupId) + "</destinationGroupId><deleteOriginGroup>" + Boolean.toString(deleteOriginGroup) + "</deleteOriginGroup></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveContact(mobilenumber: Nothing): Nothing = {
    val _func = "RemoveContact"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><mobilenumber>" + mobilenumber + "</mobilenumber></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveContactByContactID(contactId: Int): Nothing = {
    val _func = "RemoveContactByContactID"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><contactId>" + String.valueOf(contactId) + "</contactId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveGroup(groupId: Int): Nothing = {
    val _func = "RemoveGroup"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><groupId>" + String.valueOf(groupId) + "</groupId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _contactsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  //ACtions API Operations
  def AddBranch(branchName: Nothing, owner: Int): Nothing = {
    val _func = "AddBranch"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branchName>" + branchName + "</branchName><owner>" + String.valueOf(owner) + "</owner></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddBulk(from: Nothing, branch: Int, bulkType: Int, title: Nothing, message: Nothing, rangeFrom: Nothing, rangeTo: Nothing, DateToSend: Nothing, requestCount: Int, rowFrom: Int): Nothing = {
    val _func = "AddBulk"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><from>" + from + "</from><branch>" + String.valueOf(branch) + "</branch><bulkType>" + String.valueOf(bulkType) + "</bulkType><title>" + title + "</title><message>" + message + "</message><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo><DateToSend>" + DateToSend + "</DateToSend><requestCount>" + String.valueOf(requestCount) + "</requestCount><rowFrom>" + String.valueOf(rowFrom) + "</rowFrom></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddBulk2(from: Nothing, branch: Int, bulkType: Int, title: Nothing, message: Nothing, rangeFrom: Nothing, rangeTo: Nothing, DateToSend: Nothing, requestCount: Int, rowFrom: Int): Nothing = {
    val _func = "AddBulk2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><from>" + from + "</from><branch>" + String.valueOf(branch) + "</branch><bulkType>" + String.valueOf(bulkType) + "</bulkType><title>" + title + "</title><message>" + message + "</message><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo><DateToSend>" + DateToSend + "</DateToSend><requestCount>" + String.valueOf(requestCount) + "</requestCount><rowFrom>" + String.valueOf(rowFrom) + "</rowFrom></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddNewBulk(from: Nothing, branch: Int, bulkType: Int, title: Nothing, message: Nothing, rangeFrom: Nothing, rangeTo: Nothing, DateToSend: Nothing, requestCount: Int, rowFrom: Int): Nothing = {
    val _func = "AddNewBulk"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><from>" + from + "</from><branch>" + String.valueOf(branch) + "</branch><bulkType>" + String.valueOf(bulkType) + "</bulkType><title>" + title + "</title><message>" + message + "</message><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo><DateToSend>" + DateToSend + "</DateToSend><requestCount>" + String.valueOf(requestCount) + "</requestCount><rowFrom>" + String.valueOf(rowFrom) + "</rowFrom></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddNumber(branchId: Int, mobileNumbers: Array[Nothing]): Nothing = {
    val _mobileNumbers = "<string>" + String.join("</string><string>", mobileNumbers) + "</string>"
    val _func = "AddNumber"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branchId>" + String.valueOf(branchId) + "</branchId><mobileNumbers>" + _mobileNumbers + "</mobileNumbers></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetBranchs(owner: Int): Nothing = {
    val _func = "GetBranchs"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><owner>" + String.valueOf(owner) + "</owner></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetBulk: Nothing = {
    val _func = "GetBulk"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetBulkCount(branch: Int, rangeFrom: Nothing, rangeTo: Nothing): Nothing = {
    val _func = "GetBulkCount"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branch>" + String.valueOf(branch) + "</branch><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetBulkReceptions(bulkId: Int, fromRows: Int): Nothing = {
    val _func = "GetBulkReceptions"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><bulkId>" + String.valueOf(bulkId) + "</bulkId><fromRows>" + String.valueOf(fromRows) + "</fromRows></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetBulkStatus(bulkId: Int, sent: Int, failed: Int, status: Int): Nothing = {
    val _func = "GetBulkStatus"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><bulkId>" + String.valueOf(bulkId) + "</bulkId><sent>" + String.valueOf(sent) + "</sent><failed>" + String.valueOf(failed) + "</failed><status>" + String.valueOf(status) + "</status></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMessagesReceptions(msgId: Long, fromRows: Int): Nothing = {
    val _func = "GetMessagesReceptions"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><msgId>" + String.valueOf(msgId) + "</msgId><fromRows>" + String.valueOf(fromRows) + "</fromRows></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetMobileCount(branch: Int, rangeFrom: Nothing, rangeTo: Nothing): Nothing = {
    val _func = "GetMobileCount"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branch>" + String.valueOf(branch) + "</branch><rangeFrom>" + rangeFrom + "</rangeFrom><rangeTo>" + rangeTo + "</rangeTo></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetSendBulk: Nothing = {
    val _func = "GetSendBulk"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetTodaySent: Nothing = {
    val _func = "GetTodaySent"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetTotalSent: Nothing = {
    val _func = "GetTotalSent"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveBranch(branchId: Int): Nothing = {
    val _func = "RemoveBranch"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><branchId>" + String.valueOf(branchId) + "</branchId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveBulk(bulkId: Int): Nothing = {
    val _func = "RemoveBulk"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><bulkId>" + String.valueOf(bulkId) + "</bulkId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def SendMultipleSMS(to: Array[Nothing], from: Nothing, text: Array[Nothing], isflash: Boolean, udh: Nothing, recId: Array[Long], status: Nothing): Nothing = {
    val _to = "<string>" + String.join("</string><string>", to) + "</string>"
    val _text = "<string>" + String.join("</string><string>", text) + "</string>"
    val _recId = "<long>" + String.join("</long><long>", Arrays.toString(recId)) + "</long>"
    val _func = "SendMultipleSMS"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + from + "</from><text>" + _text + "</text><isflash>" + Boolean.toString(isflash) + "</isflash><udh>" + udh + "</udh><recId>" + _recId + "</recId><status>" + status + "</status></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def SendMultipleSMS2(to: Array[Nothing], from: Array[Nothing], text: Array[Nothing], isflash: Boolean, udh: Nothing, recId: Array[Long], status: Nothing): Nothing = {
    val _to = "<string>" + String.join("</string><string>", to) + "</string>"
    val _text = "<string>" + String.join("</string><string>", text) + "</string>"
    val _from = "<string>" + String.join("</string><string>", from) + "</string>"
    val _recId = "<long>" + String.join("</long><long>", Arrays.toString(recId)) + "</long>"
    val _func = "SendMultipleSMS2"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + _from + "</from><text>" + _text + "</text><isflash>" + Boolean.toString(isflash) + "</isflash><udh>" + udh + "</udh><recId>" + _recId + "</recId><status>" + status + "</status></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def UpdateBulkDelivery(bulkId: Int): Nothing = {
    val _func = "UpdateBulkDelivery"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><bulkId>" + String.valueOf(bulkId) + "</bulkId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _actionsOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  //Schedule API Operations
  def AddMultipleSchedule(to: Array[Nothing], from: Nothing, text: Array[Nothing], isflash: Boolean, scheduleDateTime: Array[Nothing], period: Nothing): Nothing = {
    val _to = "<string>" + String.join("</string><string>", to) + "</string>"
    val _text = "<string>" + String.join("</string><string>", text) + "</string>"
    val _schDates = "<dateTime>" + String.join("</dateTime><dateTime>", scheduleDateTime) + "</dateTime>"
    val _func = "AddMultipleSchedule"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + _to + "</to><from>" + from + "</from><text>" + _text + "</text><isflash>" + Boolean.toString(isflash) + "</isflash><scheduleDateTime>" + _schDates + "</scheduleDateTime><period>" + period + "</period></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddNewUsance(to: Nothing, from: Nothing, text: Nothing, isflash: Boolean, scheduleStartDateTime: Nothing, countrepeat: Int, scheduleEndDateTime: Nothing, periodType: Nothing): Nothing = {
    val _func = "AddNewUsance"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + text + "</text><isflash>" + Boolean.toString(isflash) + "</isflash><scheduleStartDateTime>" + scheduleStartDateTime + "</scheduleStartDateTime><countrepeat>" + String.valueOf(countrepeat) + "</countrepeat><periodType>" + periodType + "</periodType></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddSchedule(to: Nothing, from: Nothing, text: Nothing, isflash: Boolean, scheduleDateTime: Nothing, period: Nothing): Nothing = {
    val _func = "AddSchedule"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + text + "</text><isflash>" + Boolean.toString(isflash) + "</isflash><scheduleDateTime>" + scheduleDateTime + "</scheduleDateTime><period>" + period + "</period></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def AddUsance(to: Nothing, from: Nothing, text: Nothing, isflash: Boolean, scheduleStartDateTime: Nothing, repeatAfterDays: Int, scheduleEndDateTime: Nothing): Nothing = {
    val _func = "AddUsance"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><to>" + to + "</to><from>" + from + "</from><text>" + text + "</text><isflash>" + Boolean.toString(isflash) + "</isflash><scheduleStartDateTime>" + scheduleStartDateTime + "</scheduleStartDateTime><repeatAfterDays>" + String.valueOf(repeatAfterDays) + "</repeatAfterDays><scheduleEndDateTime>" + scheduleEndDateTime + "</scheduleEndDateTime></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def GetScheduleStatus(scheduleId: Int): Nothing = {
    val _func = "GetScheduleStatus"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><scheduleId>" + String.valueOf(scheduleId) + "</scheduleId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveSchedule(scheduleId: Int): Nothing = {
    val _func = "RemoveSchedule"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><scheduleId>" + String.valueOf(scheduleId) + "</scheduleId></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }

  def RemoveScheduleList(scheduleIdList: Array[Int]): Nothing = {
    val _list = "<int>" + String.join("</int><int>", Arrays.toString(scheduleIdList)) + "</int>"
    val _func = "RemoveScheduleList"
    val wsReq = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><" + _func + " xmlns=\"http://tempuri.org/\"><username>" + username + "</username><password>" + password + "</password><scheduleIdList>" + _list + "</scheduleIdList></" + _func + "></soap:Body></soap:Envelope>"
    ws.url(baseSoapUrl + _scheduleOp).setHeader("Content-Type", "text/xml; charset=utf-8").post(wsReq)
  }
}
