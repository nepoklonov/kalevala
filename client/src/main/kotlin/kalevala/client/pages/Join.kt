package kalevala.client.pages

import kalevala.client.FrameComponent
import kalevala.client.MainStyles
import kalevala.client.pages.join.Contest
import kalevala.client.pages.join.getContestInfo
import kalevala.client.stucture.PageProps
import kalevala.client.stucture.PageState
import kalevala.client.stucture.StandardPageComponent
import kalevala.common.div
import kalevala.common.interpretation.Documents
import kalevala.common.interpretation.ImageDirs
import kalevala.common.interpretation.Pages
import kalevala.common.models.participants.FormType
import kotlinx.css.*
import kotlinx.html.ATarget
import kotlinx.html.SPAN
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.setState
import styled.*

fun getFormType(current: String): FormType {
    return when (current) {
        Pages.Join.ethnoTour.path -> FormType.EthnoTour
        Pages.Join.faces.path -> FormType.Faces
        Pages.Join.sunCountry.path -> FormType.SunCountry
        Pages.Join.ethnoMotive.path -> FormType.EthnoMotive
        Pages.Join.scientific.path -> FormType.Scientific
        Pages.Join.scientific.path / "article" -> FormType.Article
        Pages.Join.organize.path -> FormType.Organize
        else -> console.log(current).run { error("what??") }
    }
}


fun RBuilder.orangeSpan(block: StyledDOMBuilder<SPAN>.() -> Unit) = styledSpan {
    css {
        +MainStyles.highlightedText
    }
    block()
}

typealias ContestA = RBuilder.(text: String, StyledDOMBuilder<SPAN>.() -> Unit) -> Unit

interface JoinState : PageState {
    var adding: Boolean
    var addType: String
}

fun RBuilder.textInButton(text: String) {
    styledButton {
        css {
            display = Display.block
            backgroundColor = Color.transparent
            padding(5.px)
            cursor = Cursor.pointer
            fontSize = 18.pt
        }
        +text
    }
}

class JoinComponent(props: PageProps) : StandardPageComponent<JoinState>(props) {
    init {
        state.adding = false
        state.addType = ""
    }

    private val aOfficialPDF: ContestA
        get() = { text, block ->
            styledSpan {
                block()
                styledA(href = Documents.getOfficialPDF(props.current), target = ATarget.blank) {
                    textInButton(text)
                }
            }
        }

    private val aJoin: ContestA
        get() = { text, block ->
            styledSpan {
                block()
                styledA(href = "#") {
                    attrs.onClickFunction = {
                        it.preventDefault()
                        setState {
                            adding = true
                            addType = props.current
                        }
                    }
                    textInButton(text)
                }
            }
        }

    private val secondJoin: ContestA
        get() = { text, block ->
            styledSpan {
                block()
                styledA(href = "#") {
                    attrs.onClickFunction = {
                        it.preventDefault()
                        setState {
                            adding = true
                            addType = props.current / "article"
                            console.log(111)
                        }
                    }
                    textInButton(text)
                }
            }
        }

    override fun StyledDOMBuilder<*>.page() {
        styledDiv {
            css {
                display = Display.flex
                alignItems = Align.flexEnd
            }

            ImageDirs.getPoster(props.current)?.let {
                styledImg(src = it) {
                    css {
                        width = 30.pct
                        marginRight = 30.px
                    }
                }
            }

            styledDiv {
                css {
                    display = Display.inlineFlex
                    flexWrap = FlexWrap.wrap
                    fontSize = 20.pt
                    child("span") {
                        margin(5.px, 20.px)
                    }
                    child("span a") {
                        color = Color.darkSlateBlue
                        hover {
                            color = Color.black
                        }
                    }
                }
                if (getFormType(props.current) != FormType.Organize) {
                    aOfficialPDF("Скачать положение") {}
                }
                when (getFormType(props.current)) {
                    FormType.Scientific -> {
                        aJoin("Зарегистрироваться") {}
                        secondJoin("Подать статью в сборник") {}
                    }
                    FormType.Organize -> aJoin("Заполнить заявку") {}
                    else -> aJoin("Участвовать") {}
                }
            }
        }
        styledDiv {
            css {
                padding(0.px, 30.px)
                child("p") {
                    margin(10.px, 0.px)
                }
                child("h3") {
                    marginTop = 20.px
                    textAlign = TextAlign.center
                }
            }
            getContestInfo(aOfficialPDF, aJoin, props.current)
        }

        if (state.adding) {
            child(FrameComponent::class) {
                attrs {
                    width = 60.pct
                    height = 80.pct
                    close = {
                        setState {
                            adding = false
                        }
                    }
                    content = {
                        css {
                            overflow = Overflow.auto
                        }
                        child(Contest::class) {
                            attrs {
                                formType = getFormType(state.addType)
                            }
                        }
                    }
                }
            }
        }
    }
}