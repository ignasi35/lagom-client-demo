package com.example.hello.api

import akka.stream.scaladsl.Source
import akka.{ Done, NotUsed }
import com.lightbend.lagom.scaladsl.api.{ Service, ServiceCall }
import play.api.libs.json.{ Format, Json }


trait GithubService extends Service {

  override final def descriptor = {
    import Service._
    named("github").withCalls(
      pathCall(
        //        "/repos/:owner/:repo/issues?milestone", getIssues _,
        "/repos/:owner/:repo/issues/:issue", getIssue _
      )
      // Invoking /issues permits using different GH Media Types. We're defaulting to 'application/vnd.github.VERSION.full+json'
      // TODO: Create a message serializer that reads JSON even though the MediaType is 'application/vnd.github.VERSION.full+json'
      //
    ).withAutoAcl(true)
  }

  // https://developer.github.com/v3/issues/#list-issues-for-a-repository
  //  def getIssues(owner: String, repo: String, milestone: Option[String] = None): ServiceCall[NotUsed, Seq[Issue]]
  def getIssue(owner: String, repo: String, issue: Int): ServiceCall[NotUsed, Issue]
}


/**
  * GH issues cotain a lot more intofmation than we need now. This mapping is s subset of the delivered fields to
  * complete our task at hand.
  */
case class Issue(
                  number: Int,
                  state: String, // TODO: create an enum for this.
                  title: String,
                  labels: Seq[Label],
                  milestone: Milestone
                )

object Issue {
  implicit val format: Format[Issue] = Json.format
}

case class Milestone(
                      title: String
                    )

object Milestone {
  implicit val format: Format[Milestone] = Json.format
}

case class Label(
                  name: String
                )

object Label {
  implicit val format: Format[Label] = Json.format
}