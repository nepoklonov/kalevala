package kalevala.server.api.join

import io.ktor.routing.Route
import kalevala.common.Answer
import kalevala.common.Method
import kalevala.common.Request
import kalevala.common.models.InputField
import kalevala.common.models.participants.AnyForm
import kalevala.common.models.participants.FormType
import kalevala.server.api.listenAndAutoRespond
import kalevala.server.database.createModel
import kalevala.server.database.getModelTable
import kalevala.server.database.loggedTransaction
import kotlinx.serialization.list
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

@Suppress("UNCHECKED_CAST")
fun Route.startGetModelAPI() = listenAndAutoRespond<Request.GetModel>(Method.GetModel) { request, _ ->
    val klass = request.formName.klass
    val list = mutableListOf<InputField>()
    val values = loggedTransaction {
        request.id?.let {
            klass.getModelTable().selectModels { it[AnyForm::id] eq request.id!! }.firstOrNull()
        }
    }
    klass.createModel().fields.forEach { f ->
        val inputField = f.inputField
        if (values != null) {
            inputField.value = (klass as KClass<AnyForm>).memberProperties.find { it.name == f.inputField.name }!!.get(values).toString()
            if (request.formName == FormType.NewsForm && inputField.name == "date") {
                inputField.value = inputField.value.split('.').reversed().joinToString(".") { it }
            }
        }
        list += inputField
    }
    list.sortBy { it.order }
    Answer.ok(InputField.serializer().list, list.toList())
}