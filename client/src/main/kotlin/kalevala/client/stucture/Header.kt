package kalevala.client.stucture

import kalevala.client.*
import kalevala.client.Section.Companion.About
import kalevala.client.Section.Companion.Commonwealth
import kalevala.client.Section.Companion.Gallery
import kalevala.client.Section.Companion.Join
import kalevala.client.Section.Companion.Kalevala
import kalevala.client.Section.Companion.Karelia
import kalevala.client.elements.image.scaledImage
import kalevala.common.General
import kalevala.common.GeneralInfo
import kalevala.common.Request
import kalevala.common.getPluralForm
import kalevala.common.interpretation.Images
import kalevala.common.interpretation.Pages
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
import kotlin.js.Date

//val months = arrayOf(
//    "января",
//    "февраля",
//    "марта",
//    "апреля",
//    "мая",
//    "июня",
//    "июля",
//    "августа",
//    "сентября",
//    "октября",
//    "ноября",
//    "декабря"
//)

//data class Today(val day: String, val month: String, val year: String)

//fun today(): Today = Date().let {
//    Today(it.getDate().toString(), months[it.getMonth()], it.getFullYear().toString())
//}

val DAY_OF_THE_END = Date.UTC(2020, 2, 10).toLong()
const val msInDay = 1000 * 3600 * 24

fun getDaysLeft() = (DAY_OF_THE_END / msInDay - Date.now().toLong() / msInDay).toInt()

fun RBuilder.headerInfo(amount: Int, one: String, two: String, five: String) {
    styledDiv {
        css {
            margin(0.px, 0.px, 0.px, 50.px)
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
    private val isMain
        get() = props.current == Section.Main.url

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
                    scaledImage(Images.mainLogo)
                }
                styledDiv {
                    css {
                        whiteSpace = WhiteSpace.nowrap
                        marginLeft = 20.px
                    }
                    scaledImage(Images.mainTitle)
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
                    headerInfo(getDaysLeft(), "день\nдо финала", "дня\nдо финала", "дней\nдо финала")
                    headerInfo(state.participantsAmount, "участник", "участника", "участников")
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
