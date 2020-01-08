package kalevala.client

import kalevala.client.stucture.RootComponent
import kotlinx.css.*
import kotlinx.html.CommonAttributeGroupFacade
import org.w3c.dom.events.Event
import react.RElementBuilder
import react.RProps
import react.dom.render
import react.router.dom.browserRouter
import react.router.dom.route
import react.router.dom.switch
import styled.StyledComponents
import styled.injectGlobal
import kotlin.browser.document
import kotlin.js.Date

const val scale = 1.0

fun getISOSTime() = Date()
    .toISOString()
    .replace(':', '-')
    .replace('.', '-')
    .replace('T', '_')
    .replace("Z", "")

interface RoutedProps : RProps {
    var current: String
}

var CommonAttributeGroupFacade.onMouseEnterFunction : (Event) -> Unit
    get()  = throw UnsupportedOperationException("You can't read variable onMouseEnter")
    set(newValue) {consumer.onTagEvent(this, "onmouseenter", newValue)}

var CommonAttributeGroupFacade.onMouseLeaveFunction : (Event) -> Unit
    get()  = throw UnsupportedOperationException("You can't read variable onMouseLeave")
    set(newValue) {consumer.onTagEvent(this, "onmouseleave", newValue)}

fun RElementBuilder<RProps>.primitiveRoute(page: PageInfo, keyWords: List<String>) {
    route(page.url, exact = true) {
        child(RootComponent::class) {
            attrs.current = page.url
            attrs.pageComponent = page.pageClass
            attrs.pageTitle = page.title
            attrs.keyWords = keyWords
        }
    }
}

fun RElementBuilder<RProps>.primitiveRoute(page: PageInfo) = primitiveRoute(page, listOf())

fun RElementBuilder<RProps>.primitiveRoute(page: PageInfo, vararg keyWords: String) = primitiveRoute(page, keyWords.asList())

//fun RElementBuilder<RProps>.primitiveRoute(section: Section) = primitiveRoute(section.self)

fun main() {
    StyledComponents.injectGlobal {
        h1 {
            fontSize = 55.px
            fontWeight = FontWeight.bold
        }
        h2 {
            fontSize = 30.px
//            margin(20.px, 0.px, 20.px, 30.px)
        }
        h3 {
            fontSize = 20.px
            margin(20.px, 0.px, 10.px, 0.px)
        }
    }
    render(document.getElementById("js-response")) {
        browserRouter {
            switch {
                listOf(Section.About, Section.Join, Section.Gallery, Section.Karelia, Section.Kalevala).forEach {
                    (it.pages).forEach(::primitiveRoute)
                }
                Section.Join.pages.forEach { primitiveRoute(it, "new") }
                primitiveRoute(Section.Main.self)
                primitiveRoute(Section.Admin.self)
                primitiveRoute(Section.Kalevala.self)
                primitiveRoute(Section.Commonwealth.self)
            }
        }
    }
}