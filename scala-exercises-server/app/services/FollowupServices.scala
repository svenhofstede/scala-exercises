package services

import models.FollowupModel
import services.messages._

import scala.concurrent.{Future, ExecutionContext}

trait FollowupServices {

  def create(request: CreateFollowupRequest): Future[CreateFollowupResponse]

}

class FollowupServicesImpl(implicit val executionContext: ExecutionContext) extends FollowupServices {

  override def create(request: CreateFollowupRequest): Future[CreateFollowupResponse] = {

    val result = for {
      followup <- FollowupModel.store.create(
        text = request.text)
    } yield CreateFollowupResponse(followup = followup)

    result recover {
      case e => throw new Exception(s"Followup creation error: ${e.getMessage}")
    }
  }

}