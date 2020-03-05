package kalevala.client.stucture.header

import kalevala.client.RoutedProps
import kalevala.client.Section
import kalevala.client.elements.image.svgImage
import kalevala.client.parseAnswerBody
import kalevala.client.send
import kalevala.common.General
import kalevala.common.GeneralInfo
import kalevala.common.Request
import kalevala.common.getPluralForm
import kalevala.common.interpretation.Pages
import kalevala.common.interpretation.SVGImages
import kalevala.common.interpretation.x
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.css.properties.borderBottom
import react.RBuilder
import react.RComponent
import react.RState
import react.dom.br
import react.router.dom.navLink
import react.setState
import styled.css
import styled.styledDiv
import styled.styledP
import styled.styledSpan
import kotlin.browser.window
import kotlin.js.Date

val DAY_OF_THE_END = Date.UTC(2020, 1, 28).toLong()
val DAY_OF_THE_STOP = Date.UTC(2020, 1, 20).toLong()
const val msInDay = 1000 * 3600 * 24

//fun getDaysLeft() = (DAY_OF_THE_END / msInDay - Date.now().toLong() / msInDay).toInt()
//fun getAccessDaysLeft() = (DAY_OF_THE_STOP / msInDay - Date.now().toLong() / msInDay).toInt()

fun RBuilder.headerInfo(amount: Int, one: String, two: String, five: String) {
    styledDiv {
        css {
            margin(0.px, 0.px, 0.px, 30.px)
            fontSize = 14.px
            display = Display.inlineFlex
            alignItems = Align.flexEnd

        }
        styledSpan {
            css {
                fontSize = 40.px
                marginRight = 5.px
            }
            +amount.toString()
        }
        styledSpan {
            css {
                marginBottom = 6.px
            }
            amount.getPluralForm(one, two, five).toUpperCase().split('\n').forEach {
                +it
                br { }
            }
        }
    }
}

interface HeaderState : RState {
    var participantsAmount: Int
    var citiesAmount: Int
}

class HeaderComponent : RComponent<RoutedProps, HeaderState>() {
//    private val isMain
//        get() = props.current == Section.Main.url

    init {
        state.participantsAmount = 0
        state.citiesAmount = 0
        GlobalScope.launch {
            val generalInfo = Request.GetGeneralInfo().send().parseAnswerBody(GeneralInfo.serializer())
            if (state.participantsAmount != generalInfo.participantsAmount) setState {
                participantsAmount = generalInfo.participantsAmount
            }
            if (state.citiesAmount != generalInfo.citiesAmount) setState {
                citiesAmount = generalInfo.citiesAmount
            }
        }
    }

    override fun RBuilder.render() {

        styledDiv {
            css {
                margin(20.px, 0.px, 30.px, 0.px)
                width = 90.pct
                padding(20.px, 0.px)
                display = Display.flex
                justifyContent = JustifyContent.spaceBetween
                alignItems = Align.flexEnd
                borderBottom(1.px, BorderStyle.solid, Color.black)
                flexWrap = FlexWrap.wrap
            }
            styledDiv {
                css {
                    display = Display.flex
                    justifyContent = JustifyContent.spaceBetween
                    alignItems = Align.center
                }
                navLink(Pages.main.path) {
                    svgImage(SVGImages.mainLogo, 80 x 80)
                }
                styledDiv {
                    css {
                        whiteSpace = WhiteSpace.nowrap
                        marginLeft = 20.px
                    }
                    svgImage(SVGImages.mainTitle, 440 x 42)
                    styledP {
                        css {
                            marginTop = 10.px
                            fontSize = 24.px
                        }
                        +General.shortDescription
                    }
                }
            }
            styledDiv {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    alignItems = Align.flexEnd
                }
                styledDiv {
                    css {
                        display = Display.flex
                        justifyContent = JustifyContent.spaceBetween
                        fontSize = 24.px
                        marginTop = 15.px
                    }
//                    headerInfo(getDaysLeft(), "день\nдо финала", "дня\nдо финала", "дней\nдо финала")
//                    headerInfo(getAccessDaysLeft(), "день до завершения\n приёма работ", "дня до завершения\n" +
//                        " приёма работ", "дней до завершения\n" +
//                        " приёма работ")

                    styledDiv {
                        css {
                            margin(0.px, 0.px, 0.px, 30.px)
                            fontSize = 14.px
                            display = Display.inlineFlex
                            alignItems = Align.flexEnd

                        }
                        styledSpan {
                            css {
                                fontWeight = FontWeight.bold
                                marginBottom = 6.px
                            }
                            "Этнофестиваль\nзавершён".toUpperCase().split('\n').forEach {
                                +it
                                br { }
                            }
                        }
                    }

                    headerInfo(state.participantsAmount + 700, "участник", "участника", "участников")
                    headerInfo(state.citiesAmount, "населённый\nпункт", "населённых\nпункта", "населённых\nпунктов")
                }
            }
        }

        styledDiv {
            css {
                width = 100.pct
                position = Position.relative
                zIndex = 3
                padding(0.px, 30.px, 30.px, 30.px)
                display = Display.grid
                gridAutoFlow = GridAutoFlow.column
                gridAutoColumns = GridAutoColumns(1.fr)
                columnGap = ColumnGap(10.px.value)
                gridTemplateRows = GridTemplateRows(70.px, LinearDimension.auto, 1.fr)
            }
            Section.run {
                listOf(About, Join, Gallery, Karelia, Kalevala, Commonwealth).forEach {
                    child(HNavElement::class) {
                        attrs.section = it
                        attrs.current = props.current
                        attrs.content = if (it == Commonwealth) drawCommonwealth else ({})
                    }
                }
            }
        }
    }

    override fun componentDidMount() {
        window.addEventListener("resize", { forceUpdate() })
    }

    override fun componentWillUnmount() {
        window.removeEventListener("resize", { forceUpdate() })
    }
}
