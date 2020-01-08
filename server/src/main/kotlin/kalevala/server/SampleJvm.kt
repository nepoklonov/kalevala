package kalevala.server

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.html.respondHtml
import io.ktor.http.content.file
import io.ktor.http.content.files
import io.ktor.http.content.static
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.sessions.*
import io.ktor.util.KtorExperimentalAPI
import kalevala.common.randomString
import kalevala.server.api.startAPI
import kalevala.server.database.startDB
import kalevala.server.generated.generateLoadingHTML
import kalevala.server.generated.generateStylesCSS
import kotlinx.html.*
import java.io.File

fun Route.openFolder(folderName: String) {
    static("/$folderName") {
        files(folderName)
    }
}

fun Route.openFolders(vararg folderNames: String) {
    for (folderName in folderNames) {
        openFolder(folderName)
    }
}

@KtorExperimentalAPI
fun Application.main() {
    println("Server started!")
    startDB()
    startImagesConnection()
    install(CORS) {
        anyHost()
        allowCredentials = true
    }
    install(Sessions) {
        cookie<UserSession> ("user-session", SessionStorageMemory()) {
            cookie.path = "/"
        }
    }
    routing {
        openFolders("images", "smi", "documents", "fonts", "yaml", "uploads")

        static("/") {
            file("main.bundle.js")
        }

        generateLoadingHTML("{...}")

        generateStylesCSS("/styles.css")

        startAPI()
    }
}