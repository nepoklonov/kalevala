package kalevala.client.pages.cw

import kalevala.client.gray50Color
import kalevala.client.pages.NewsState
import kalevala.client.redH3
import kalevala.client.send
import kalevala.common.Request
import kalevala.common.models.NewsWithSrc
import kotlinx.css.*
import kotlinx.serialization.list
import react.*
import styled.*

class CWNews: RComponent<RProps, NewsState>() {
    init {
        state.news = listOf()
        Request.CWNewsGetAll(300, 0).send(NewsWithSrc.serializer().list) {
            setState {
                news = it
            }
        }
    }

    override fun RBuilder.render() {
        state.news.forEach {
            styledDiv {
                css {
                    margin(30.px)
                }
                redH3 {
                    +it.news.header
                }
                styledSpan {
                    css {
                        color = gray50Color
                    }
                    +it.news.date.split('.').reversed().joinToString(".") { it }
                }
                styledP {
                    css {
                        margin(10.px, 0.px)
                    }
                    +it.news.content
                }
                if (it.src != "") styledImg (src = it.src) {
                    css {
                        width = 400.px
                    }
                }
            }
        }
    }
}