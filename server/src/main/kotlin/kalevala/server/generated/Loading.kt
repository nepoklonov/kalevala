package kalevala.server.generated

import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import kalevala.server.Level
import kalevala.server.UserSession
import kotlinx.html.*

fun Route.generateLoadingHTML(path: String) {
    get(path) {
        if (call.sessions.get<UserSession>() == null) {
            call.sessions.set(UserSession(Level.JustSomeone))
        }
        call.respondHtml {
            head {
                link(rel = "stylesheet", href = "/styles.css", type = "text/css")
                link(rel = "icon", href = "/images/design/main-logo.png", type = "image/png")
                meta(name = "yandex-verification", content = "bb87c21ee1d9d79a")
            }
            body("wrapper") {
                div("wrapper") {
                    id = "js-response"
                    div("wrapper wrapper-loading") {
                        div("js-loading") {
                            +"Loading..."
                        }
                    }
                }
                script(src = "/main.bundle.js") {}
            }
        }
    }
}