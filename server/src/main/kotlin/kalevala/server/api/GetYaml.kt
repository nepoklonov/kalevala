package kalevala.server.api

import com.charleskorn.kaml.Yaml
import io.ktor.routing.Route
import kalevala.common.*
import kotlinx.serialization.KSerializer
import java.io.File

@Suppress("UNCHECKED_CAST")
fun Route.startGetYamlAPI() = listenAndAutoRespond<Request.GetYaml>(Method.GetYaml) { request, _ ->
    val yamlText = File(request.yamlRef.getFileRefByName().path.trim('/')).readText()
    val yamlSerializer = request.yamlRef.serializer
    val yamlParsed = Yaml.default.parse(yamlSerializer, yamlText)
    Answer.ok(yamlSerializer as KSerializer<Any>, yamlParsed)
}