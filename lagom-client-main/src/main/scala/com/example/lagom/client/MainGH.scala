package com.example.lagom.client

import java.net.URI

import com.example.hello.api.GithubService
import com.lightbend.lagom.scaladsl.client.{ LagomClientApplication, StaticServiceLocatorComponents }
import play.api.libs.ws.ahc.AhcWSComponents

import scala.concurrent.ExecutionContext.Implicits.global

object MainGH extends App {


  val clientApp = new LagomClientApplication("lagom-client-demo")
    with StaticServiceLocatorComponents
    with AhcWSComponents {
    override def staticServiceUri: URI = URI.create("https://api.github.com")
  }

  private val ghService = clientApp.serviceClient.implement[GithubService]

  ghService.getIssue("lagom", "lagom", 990).invoke().foreach {
    issue =>
      println(issue)
      clientApp.stop()
  }

}
