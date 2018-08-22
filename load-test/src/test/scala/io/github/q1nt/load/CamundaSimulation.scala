package io.github.q1nt.load


import java.util.UUID

import com.typesafe.scalalogging.LazyLogging
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import scala.concurrent.duration._

class CamundaSimulation extends Simulation with LazyLogging {
  val httpProtocol = http
    .baseURL("http://172.18.195.61:9001")
    .inferHtmlResources()
    .acceptHeader("*/*")

  val headers_0 = Map(
    "Content-Type" -> "application/json",
    "accept-encoding" -> "gzip, deflate",
    "cache-control" -> "no-cache")

  private val createVPort: HttpRequestBuilder = http("Create vPort")
    .post("/api/vport")
    .headers(headers_0)
    .body(StringBody(session => s"""{ "foo":"${session("randomUUID").as[String]}" }""")).asJSON

  private val pollProvisioningState = asLongAs(session => session("vPortStatus").as[String] == "creating")(
    pause(1000.millis)
      .exec(
        http("Check vPort status")
          .get(session => s"/api/vport/${session("randomUUID").as[String]}")
          .check(jsonPath("$.status").saveAs("vPortStatus"))
      )
      .exec(session => {
        logger.debug(s"\n===========>>>>> vPort ${session("randomUUID").as[String]} status: ${session("vPortStatus").as[String]} <<<<<===========\n")
        session
      })
  )

  private val validateVPortStatus: HttpRequestBuilder = http("Validate vPort")
    .get(session => s"/api/vport/${session("randomUUID").as[String]}")
    .check(jsonPath("$.status").is("deployed"))

  val scn = scenario("Camunda POC Simulation")
    // Create vPort
    .exec(session => session.set("vPortStatus", "creating"))
    .exec(session => session.set("randomUUID", UUID.randomUUID().toString))
    .exec(createVPort)
    .exec(pollProvisioningState)
    .exec(validateVPortStatus)

  //Add 10 user immediately
  setUp(scn.inject(atOnceUsers(10))).protocols(httpProtocol)

  //Add users from 1 to 10 during 15 seconds
  //setUp(scn.inject(rampUsersPerSec(1) to 10 during (15 seconds))).protocols(httpProtocol)

  //Add 1 user per second during 15 seconds
  //setUp(scn.inject(constantUsersPerSec(1) during (15 seconds))).protocols(httpProtocol)

}
