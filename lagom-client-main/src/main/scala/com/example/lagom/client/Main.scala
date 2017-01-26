package com.example.lagom.client

import java.net.URI

import com.example.hello.api.HttpbinService
import com.lightbend.lagom.scaladsl.client.{LagomClientApplication, StaticServiceLocatorComponents}
import play.api.libs.ws.ahc.AhcWSComponents

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {


  val clientApp = new LagomClientApplication("lagom-client-demo")
    with StaticServiceLocatorComponents
    with AhcWSComponents {
    override def staticServiceUri: URI = URI.create("https://httpbin.org")
  }

  private val httpbinService = clientApp.serviceClient.implement[HttpbinService]

  httpbinService.get.invoke().foreach {
    headersResponse =>
      println(headersResponse)
      clientApp.stop()
  }

}
