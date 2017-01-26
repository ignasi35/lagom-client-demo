package com.example.hello.api

import akka.stream.scaladsl.Source
import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}


trait HttpbinService extends Service {

  override final def descriptor = {
    import Service._
    named("httpbin").withCalls(
      pathCall("/get", get _)
    ).withAutoAcl(true)
  }

  def get:ServiceCall[NotUsed, HeadersResponse]
}


/**
  * Invoking https://httpbin.org/get returns a JSON with all the request's headers
{
  "args": {},
  "headers": {
    "Accept-Encoding": "gzip, deflate, sdch, br",
    "Accept-Language": "en-US,en;q=0.8,es;q=0.6,ca;q=0.4",
    "Host": "httpbin.org",
    "Referer": "https://httpbin.org/",
    "Upgrade-Insecure-Requests": "1",
    "User-Agent": "..."
    },
  "origin": "11.23.24.198",
  "url": "https://httpbin.org/get"
  }
*/

case class HeadersResponse(headers: Map[String, String], origin: String, url: String)
object HeadersResponse {
    implicit val format: Format[HeadersResponse] = Json.format
}