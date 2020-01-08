package kalevala.server.api

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.content.PartData
import io.ktor.http.content.readAllParts
import io.ktor.http.content.streamProvider
import io.ktor.request.receiveMultipart
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.post
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.util.pipeline.PipelineContext
import kalevala.common.*
import kalevala.common.interpretation.Pages
import kalevala.common.models.participants.FormType
import kalevala.server.Level
import kalevala.server.UserSession
import kalevala.server.api.join.*
import kalevala.server.database.fier
import kalevala.server.database.getAmount
import kalevala.server.database.getCities
import kalevala.server.liveFile
import kotlinx.io.IOException
import kotlinx.serialization.serializer
import javax.security.auth.login.AppConfigurationEntry

inline fun <reified T : Request> List<PartData>.receiveForm(): T {
    var result: String? = null
    forEach { part ->
        if (part is PartData.FormItem) {
            if (part.name!! == "request") result = part.value
            part.dispose()
        }
    }
    return result?.run {
        try {
            deserialize<T>()
        } catch (e: Exception) {
            error("Request type doesn't match received content")
        }
    } ?: error("Request key is missing")
}

fun List<PartData>.receiveFiles() =
    asSequence().filterIsInstance<PartData.FileItem>().map { part ->
        val directory = Pages.Uploads.temp.liveFile
        val file = createTempFile("!temp" - part.originalFileName!!, directory = directory)
        part.streamProvider().use { its ->
            file.outputStream().buffered().use { bufferedOutputStream ->
                its.copyTo(bufferedOutputStream)
            }
        }
        part.dispose()
        file.name
    }.toList()

suspend fun PipelineContext<Unit, ApplicationCall>.respond(answer: Answer) {
    call.respondText(answer.serialize())
}

inline fun <reified T : Request> Route.listen(method: Method, crossinline action: suspend PipelineContext<Unit, ApplicationCall>.(T, List<String>) -> Unit) = post(method.methodName) {
    val allParts = call.receiveMultipart().readAllParts()
    action(allParts.receiveForm(), allParts.receiveFiles())
}

inline fun <reified T : Request> Route.listenAndAutoRespond(method: Method, crossinline action: PipelineContext<Unit, ApplicationCall>.(T, List<String>) -> Answer) = post(method.methodName) {
    try {
        val allParts = call.receiveMultipart().readAllParts()
        val answer = action(allParts.receiveForm(), allParts.receiveFiles())
        respond(answer)
    } catch (e: IOException) {
        println("f 5928")
        println(e)
        Answer.ok(Int.serializer(), -5928)
    } catch (e: IllegalStateException) {
        println("f 5929")
        println(e)
        Answer.ok(Int.serializer(), -5929)
    }
}

fun PipelineContext<Unit, ApplicationCall>.isAdmin() =
    call.sessions.get<UserSession>()?.let { it.level hasRights Level.Moderator } == true

inline fun <reified T : Request> Route.adminListenAndAutoRespond(
    method: Method,
    crossinline action: PipelineContext<Unit, ApplicationCall>.(T, List<String>) -> Answer
) = listenAndAutoRespond<T>(method) { request, files ->
    if (isAdmin()) {
        action(request, files)
    } else {
        Answer.accessDenied()
    }
}

fun Route.startAPI() {
    startGetHeaderAmountAPI()

    startGetYamlAPI()

    startGetModelAPI()
    startFileUploadAPI()
    startFormSendAPI()

    startImagesGetVersionAPI()
    startImagesGetAllAPI()
    startImagesGetInfoAPI()
    startImagesGetOriginalAPI()

    startEthnoTourGetAllAPI()

    startAboutPhotosAPI()
    startStaticImagesGetVersionAPI()

    startNewsGetAllAPI()
    startReviewsGetAllAPI()

    startAdminAPI()
}

fun Route.startGetHeaderAmountAPI() = listenAndAutoRespond<Request.GetGeneralInfo>(Method.GetGeneralInfo) { _, _ ->
    val info = FormType.values().run {
        val cities = mutableSetOf<String>()
        forEach { cities += getCities(it) }
        println(cities.toString())
        GeneralInfo(
            sumBy { getAmount(it) },
            cities.size
        )
    }
    fier()
    Answer.ok(GeneralInfo.serializer(), info)
}
