package kalevala.server.api.join

import io.ktor.routing.Route
import kalevala.common.Answer
import kalevala.common.Method
import kalevala.common.Request
import kalevala.common.models.InputField
import kalevala.server.database.createModel
import kalevala.server.api.listenAndAutoRespond
import kotlinx.serialization.list

fun Route.startGetModelAPI() = listenAndAutoRespond<Request.GetModel>(Method.GetModel) { request, _ ->
    val klass = request.formName.klass
    val list = mutableListOf<InputField>()
    klass.createModel().fields.forEach { f ->
        list += f.inputField
    }
    list.sortBy { it.order }
    Answer.ok(InputField.serializer().list, list.toList())
}