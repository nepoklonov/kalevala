package kalevala.client.pages

import kalevala.client.FrameComponent
import kalevala.client.gray20Color
import kalevala.client.gray50Color
import kalevala.client.pages.join.Contest
import kalevala.client.send
import kalevala.client.stucture.PageProps
import kalevala.client.stucture.StandardPageComponent
import kalevala.client.stucture.YamlListState
import kalevala.client.stucture.updateYamlListState
import kalevala.common.Request
import kalevala.common.interpretation.ImageDirs
import kalevala.common.interpretation.YamlRef
import kalevala.common.models.Review
import kalevala.common.models.participants.FormType
import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import kotlinx.serialization.list
import kotlinx.serialization.serializer
import react.dom.p
import react.setState
import styled.*

interface GreetingsState : YamlListState<String> {
    var contest: Boolean //TODO: rename Contest class to "Form"
    var reviews: List<Review>
}

class Greetings(pageProps: PageProps) : StandardPageComponent<GreetingsState>(pageProps) {
    init {
        state.contest = false
        Request.GetYaml(YamlRef.GreetingsYaml).send(String.serializer().list, ::updateYamlListState)
        Request.ReviewsGetAll().send(Review.serializer().list) {
            setState {
                reviews = it
            }
        }
    }

    override fun StyledDOMBuilder<*>.page() {
        if (state.yaml != undefined) {
            styledDiv {
                css {
                    display = Display.flex
                    flexWrap = FlexWrap.wrap
                    justifyContent = JustifyContent.spaceBetween
                }
                state.yaml.forEach {
                    styledImg(src = (ImageDirs.greetings file it).path) {
                        css {
                            height = 300.px
                            margin(20.px)
                        }
                    }
                }

                styledSpan {
                    css {
                        width = 100.pct
                        fontSize = 18.px
                    }
                    styledA(href = "#") {
                        attrs.onClickFunction = {
                            it.preventDefault()
                            setState {
                                contest = true
                            }
                        }
                        +"Оставить отзыв"
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
                                        formType = FormType.ReviewForm
                                    }
                                }
                            }
                        }
                    }
                }

                if (state.reviews != undefined) {
                    state.reviews.forEach {
                        styledDiv {
                            css {
                                width = 400.px
                                margin(20.px)
                                padding(20.px, 40.px, 20.px, 15.px)
                                backgroundColor = gray20Color
                            }
                            styledP {
                                css {
                                    fontWeight = FontWeight.bold
                                    fontSize = 14.pt
                                }
                                +it.fio
                            }
                            p {
                                +it.review
                            }
                        }
                    }
                }
            }
        }
    }
}