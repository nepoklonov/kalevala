package kalevala.server.api

import io.ktor.routing.Route
import kalevala.common.Answer
import kalevala.common.Method
import kalevala.common.Request
import kalevala.common.models.NewsWithSrc
import kalevala.common.models.Review
import kalevala.server.database.getAllNews
import kalevala.server.database.getAllReview
import kotlinx.serialization.list


fun Route.startNewsGetAllAPI() = listenAndAutoRespond<Request.NewsGetAll>(Method.NewsGetAll) { request, _ ->
    val news = getAllNews(request.width, request.height)
    Answer.ok(NewsWithSrc.serializer().list, news)
}

fun Route.startReviewsGetAllAPI() = listenAndAutoRespond<Request.ReviewsGetAll>(Method.ReviewsGetAll) { _, _ ->
    Answer.ok(Review.serializer().list, getAllReview())
}