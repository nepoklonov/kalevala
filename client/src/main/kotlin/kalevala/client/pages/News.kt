package kalevala.client.pages

import kalevala.client.*
import kalevala.client.pages.join.Contest
import kalevala.client.stucture.PageProps
import kalevala.client.stucture.PageState
import kalevala.client.stucture.StandardPageComponent
import kalevala.common.AnswerType
import kalevala.common.Request
import kalevala.common.models.NewsWithSrc
import kalevala.common.models.participants.FormType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.css.properties.TextDecorationLine
import kotlinx.css.properties.textDecoration
import kotlinx.html.js.onClickFunction
import kotlinx.serialization.list
import react.dom.InnerHTML
import react.setState
import styled.*

interface NewsState : PageState {
    var news: List<NewsWithSrc>
    var isAdmin: Boolean
    var editId: Int?
}

class NewsComponent(props: PageProps) : StandardPageComponent<NewsState>(props) {
    init {
        state.news = listOf()
        state.isAdmin = false
        Request.NewsGetAll(400, 0).send(NewsWithSrc.serializer().list) {
            setState {
                news = it
            }
        }
        GlobalScope.launch {
            val r = Request.AdminCheck().send().parseAnswer().answerType == AnswerType.OK
            setState {
                isAdmin = r
            }
        }
    }

    override fun StyledDOMBuilder<*>.page() {
        state.news.forEach { news ->
            styledDiv {
                css {
                    margin(30.px)
                }
                redH3 {
                    +news.news.header
                    if (state.isAdmin) {
                        styledSpan {
                            css {
                                marginLeft = 10.px
                                color = gray50Color
                                textDecoration(TextDecorationLine.underline)
                                fontStyle = FontStyle.italic
                                cursor = Cursor.pointer
                            }
                            attrs.onClickFunction = {
                                setState { editId = news.news.id }
                            }
                            +"редактировать"
                        }
                    }
                }
                styledSpan {
                    css {
                        color = gray50Color
                    }
                    +news.news.date.split('.').reversed().joinToString(".") { it }
                }
                styledDiv {
                    css {
                        margin(10.px, 0.px)
                    }
                    attrs["dangerouslySetInnerHTML"] = InnerHTML(news.news.content)

                }
                if (news.src != "") styledImg(src = news.src) {
                    css {
                        width = 400.px
                    }
                }
            }
        }

        if (state.editId != null) {
            child(FrameComponent::class) {
                attrs {
                    width = 60.pct
                    height = 80.pct
                    close = {
                        setState {
                            editId = null
                        }
                    }
                    content = {
                        child(Contest::class) {
                            attrs {
                                editId = state.editId
                                formType = FormType.NewsForm
                            }
                        }
                    }
                }
            }
        }
    }
}
