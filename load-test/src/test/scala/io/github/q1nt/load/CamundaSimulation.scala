package io.github.q1nt.load

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CamundaSimulation extends Simulation {
  val httpProtocol = http
    .baseURL("http://172.18.195.61:9001")
    .inferHtmlResources()
    .acceptHeader("*/*")

  val headers_0 = Map(
    "Content-Type" -> "application/json",
    "accept-encoding" -> "gzip, deflate",
    "cache-control" -> "no-cache")

  var randomId: String = ""

  var scn = scenario("Provisioning vPort")
    .exec(session => {
      randomId = UUID.randomUUID().toString
      session
    })
    .exec(http("Create vPort")
      .post("/api/vport")
      .headers(headers_0)
      .body(StringBody(session => s"""{ "foo": "${randomId}" }""")).asJSON
      .check(status is 202))
    .pause(15)
    .exec(http("Get vPoer")
      .get(session => s"/api/vport/${ randomId}")
      .headers(headers_0)
      .check(
        jsonPath("$.status").saveAs("status")
      ))
    .exec(session => {
      println(s"=========================!!!!!!!vPort ${randomId} Status!!!!!!!===========================")
      println(session("status").as[String])
      println("=========================\n")
      session
    })

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
