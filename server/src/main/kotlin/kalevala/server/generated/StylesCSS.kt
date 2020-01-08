package kalevala.server.generated

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.css.*
import kotlinx.css.properties.LineHeight
import kalevala.common.General

suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}

fun Route.generateStylesCSS(path: String) {
    get(path) {
        call.respondCss {
            rule("@font-face") {
                fontFamily = "'PFBagueSansPro-Light'"
                put("src", "url('/fonts/PFBagueSansPro-Light.otf')")
//                    "url('/fonts/kalevala.woff') format('woff'), " +
//                    "url('/fonts/kalevala.svg') format('svg')")
            }
            rule("*") {
                fontFamily = "'PFBagueSansPro-Light'"
                fontWeight = FontWeight.normal
                margin = "0"
                padding = "0"
                lineHeight = LineHeight("110%")
                letterSpacing = 90.pct
            }
            body {
                fontSize = General.defaultFontSize.px
            }
            rule(".wrapper") {
                width = 100.pct
                height = 100.pct
                display = Display.flex
                justifyContent = JustifyContent.center
            }
            rule(".wrapper-loading") {
                alignItems = Align.center
            }
            rule(".js-loading") {
                display = Display.flex
                color = Color("#008899")
            }
            rule("#js-response") {
                position = Position.absolute
                width = 100.pct
            }
        }
    }
}