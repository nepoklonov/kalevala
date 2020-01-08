package kalevala.client.pages.cw

import kalevala.client.FrameComponent
import kalevala.client.indentedDiv
import kalevala.client.pages.join.Contest
import kalevala.common.interpretation.Documents
import kalevala.common.models.participants.FormType
import kotlinx.css.*
import kotlinx.html.ATarget
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.a
import react.dom.div
import react.dom.p
import styled.css
import styled.styledA
import styled.styledDiv

interface CWJoinState : RState {
    var contest: Boolean //TODO: rename Contest class to "Form"
}


class CWJoinComponent : RComponent<RProps, CWJoinState>() {
    override fun RBuilder.render() {
        p {
            +"Для подачи заявки на вступление в Организацию необходимо:"
        }
        indentedDiv {
            +"скачать бланк анкеты и заявления;"
        }
        indentedDiv {
            +"заполнить анкету и подписать заявление;"
        }
        indentedDiv {
            +"отсканировать или сфотографировать анкету и заявление;"
        }
        indentedDiv {
            +"загрузить через форму подачи сайта."
        }

        styledDiv {
            css {
                child("div") {
                    margin(10.px, 0.px)
                }
            }
            div {
                a(href=Documents.cwForm.path, target = ATarget.blank) {
                    +"Скачать бланк анкеты"
                }
            }
            div {
                a(href=Documents.cwApplication.path, target = ATarget.blank) {
                    +"Скачать бланк заявления"
                }
            }
            div {
                styledA(href = "#") {
                    attrs.onClickFunction = {
                        it.preventDefault()
                        setState {
                            contest = true
                        }
                    }
                    css {
                        fontSize = 18.px
                    }
                    +"Подать заявку на вступление"
                }
            }
        }

        if (state.contest) {
            child(FrameComponent::class) {
                attrs {
                    width = 60.pct
                    height = 80.pct
                    close = {
                        setState {
                            contest = false
                        }
                    }
                    content = {
                        css {
                            overflow = Overflow.auto
                        }
                        child(Contest::class) {
                            attrs {
                                formType = FormType.CWJoinForm
                            }
                        }
                    }
                }
            }
        }
    }
}