package kalevala.client.pages

import kalevala.client.gray50Color
import kalevala.client.redH3
import kalevala.client.send
import kalevala.client.stucture.PageProps
import kalevala.client.stucture.PageState
import kalevala.client.stucture.StandardPageComponent
import kalevala.common.Request
import kalevala.common.models.NewsWithSrc
import kotlinx.css.*
import kotlinx.html.P
import kotlinx.html.id
import kotlinx.html.stream.createHTML
import kotlinx.serialization.list
import react.createElement
import react.createRef
import react.dom.InnerHTML
import react.dom.html
import react.setState
import styled.*
import kotlin.browser.document

interface NewsState : PageState {
    var news: List<NewsWithSrc>
}

class NewsComponent(props: PageProps) : StandardPageComponent<NewsState>(props) {
    init {
        state.news = listOf()
        Request.NewsGetAll(400, 0).send(NewsWithSrc.serializer().list) {
            setState {
                news = it
            }
        }
    }
    override fun StyledDOMBuilder<*>.page() {
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
                styledDiv {
                    css {
                        margin(10.px, 0.px)
                    }
                    attrs["dangerouslySetInnerHTML"] = InnerHTML(it.news.content)

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