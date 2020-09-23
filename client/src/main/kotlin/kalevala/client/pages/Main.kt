package kalevala.client.pages

import kalevala.client.*
import kalevala.client.elements.image.imageInDiv
import kalevala.client.stucture.PageComponent
import kalevala.client.stucture.PageProps
import kalevala.client.stucture.PageState
import kalevala.common.Request
import kalevala.common.interpretation.ImageDirs
import kalevala.common.interpretation.Pages
import kalevala.common.models.NewsWithSrc
import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import kotlinx.css.properties.deg
import kotlinx.css.properties.rotate
import kotlinx.html.ATarget
import kotlinx.html.js.onClickFunction
import kotlinx.serialization.list
import org.w3c.dom.HTMLElement
import org.w3c.dom.get
import react.RBuilder
import react.dom.InnerHTML
import react.dom.a
import react.dom.p
import react.setState
import styled.*
import kotlin.browser.document
import kotlin.browser.window
import kotlin.math.max
import kotlin.math.min

fun RBuilder.news(title: String, subtitle: String, content: String, imageSrc: String) {
    styledDiv {
        css {
            display = Display.grid
            gridTemplateAreas("image title", "image subtitle", "image imageContent")
            gridTemplateRows = GridTemplateRows(auto, 1.fr, auto)
            gridTemplateColumns = GridTemplateColumns(2.fr, 3.fr)
            rowGap = RowGap(5.px.value)
            columnGap = ColumnGap(20.px.value)
            //height = 100.px
            margin(15.px, 0.px)
        }
        imageInDiv(imageSrc, "contain") {
            css {
                gridArea = "image"
            }
        }
        styledDiv {
            css {
                gridArea = "title"
                fontWeight = FontWeight.bold
            }
            +title
        }
        styledDiv {
            css {
                gridArea = "subtitle"
                fontSize = 12.pt
                color = gray70Color
            }
            +subtitle
        }
        styledDiv {
            css {
                overflow = Overflow.hidden
                gridArea = "imageContent"
                fontSize = 10.pt
            }
            attrs["dangerouslySetInnerHTML"] = InnerHTML(content.match("^(([^.?!])|(\\S[.?!]\\S))*[.?!]*")?.get(0)
                ?: "")
        }
    }
}

interface MainState : PageState {
    var news: List<NewsWithSrc>
    var index: Int
}

class MainComponent(props: PageProps) : PageComponent<MainState>(props) {
    private val amount = 5

    init {
        document.title = "Земля Калевалы"
        state.index = 0
        state.news = listOf()
        Request.NewsGetAll(600, 0).send(NewsWithSrc.serializer().list) {
            setState {
                news = it
            }
        }
    }

    override fun StyledDOMBuilder<*>.page() {
        styledDiv {
            css {
                display = Display.grid
                gridTemplateColumns = GridTemplateColumns(1.fr, 1.fr)
                columnGap = ColumnGap(7.pct.value)
                height = 100.pct
            }
            styledDiv {
                css {
                    display = Display.grid
                    gridTemplateRows = GridTemplateRows(40.px, auto, 40.px)
                    rowGap = RowGap(10.px.value)
                    alignContent = Align.flexStart
                }
                imageInDiv((ImageDirs.design / "go-left.png").path, "contain", height = 100.pct, width = 100.pct) {
                    css {
                        transform.rotate(90.deg)
                        if (state.index > 0) {
                            opacity = 0.6
                            hover {
                                opacity = 1.0
                            }
                            cursor = Cursor.pointer
                        } else {
                            opacity = 0.3
                        }
                    }
                    attrs.onClickFunction = {
                        setState { index = max(0, index - 1) }
                    }
                }
                styledDiv {
                    css {
                        display = Display.grid
                        rowGap = RowGap(20.px.value)
                    }
                    if (state.news != undefined && state.news.isNotEmpty()) {
                        state.news.subList(max(state.index, 0), min(state.index + amount, state.news.size - 1)).forEach {
                            news(it.news.header, "", it.news.content, it.src)
                        }
                    }
                }
                imageInDiv((ImageDirs.design / "go-left.png").path, "contain", height = 100.pct, width = 100.pct) {
                    css {
                        transform.rotate(270.deg)
                        if (state.index + amount < state.news.size - 1) {
                            cursor = Cursor.pointer
                            opacity = 0.6
                            hover {
                                opacity = 1.0
                            }
                        } else {
                            opacity = 0.3
                        }
                    }
                    attrs.onClickFunction = {
                        setState { index = min(state.news.size - 1 - amount, index + 1) }
                    }
                }
            }
            styledDiv {
                css {
                    display = Display.grid
                    rowGap = RowGap(15.px.value)
                    height = LinearDimension.fitContent
                }
                styledP {
                    css {
                        fontSize = 18.pt
                        textAlign = TextAlign.center
                    }
                    +"Этнофестиваль приглашает!"
                }
                p {
                    +"В 2021 году Международному этнофестивалю «Земля Калевалы» исполняется 15 лет. За эти годы проект завоевал любовь и интерес среди жителей Республики Карелия и других регионов России, зарубежных государств."
                }
                p {
                    +"Организационный комитет дает старт подготовке программы 15-го юбилейного Этнофестиваля. По сложившейся традиции, в конце сентября, стартует обширная конкурсная программа, начинается формирование научно-деловой, интерактивной и концертно-выставочной программы, которая пройдет в феврале-мае на площадках Карелии, Москвы, Санкт-Петербурга и Ленинградской области."
                }
                p {
                    +"Церемония открытия Этнофестиваля и награждение лауреатов состоится в Санкт-Петербурге 26 февраля 2021 года."
                }
                p {
                    +"Организационный комитет открывает прием предложений в основную и параллельную программу фестиваля, которые можно направить на электронный адрес: kalevfest@yandex.ru"
                }
                p {
                    +"Сделаем вместе предстоящий юбилейный фестивальный год насыщенным, интересным и ярким!"
                }
                p {
                    +"#ЗемляКалевалы #15летсКарелией #Новаяшкола #kalevalafest #kalevalamaa"
                }
//                p {
//                    +"Открыта регистрация на церемонию торжественного открытия XIV Международного этнофестиваля «Земля Калевалы-2020», приуроченного к 100-летию Республики Карелия и международному дню карело-финского эпоса \"Калевала\"!"
//                }
//                p {
//                    +"Старт насыщенной фестивальной программы состоится 28 февраля (пятница) 2020 года в 12.30 в самом сердце Санкт-Петербурга - Атриуме Комендантского дома Петропавловской крепости."
//                }
//                p {
//                    +"Искусство и культура Карелии станет еще ближе гостям этнофестиваля благодаря интерактивным программам и мастер-классам по народным ремёслам.Состоится награждение лауреатов Этнофестиваля, выступления лучших творческих коллективов Республики Карелия, презентация выставочной и научно-деловой программы."
//                }
//                p {
//                    +"Гостями Этнофестиваля в Санкт-Петербурге станут более 200 творческих лидеров из Карелии, представители государств Скандинавии и Балтии. Ожидается участие официальных лиц Правительства Республики Карелия и Санкт-Петербурга."
//                }
//                styledA(target = ATarget.blank, href = "https://docs.google.com/forms/d/e/1FAIpQLSfCdvp2YVQAlZnIHZjNpPu3QvzH8R80h05CePopy6d3Z6Zm5g/viewform?vc=0&c=0&w=1") {
//                    css {
//                        textAlign = TextAlign.center
//                    }
//                    styledButton {
//                        css {
//                            backgroundColor = Color("#ffddaa")
//                            color = kalevalaRedColor
//                            fontSize = 25.pt
//                            padding(20.px)
//                        }
//                        +"Онлайн-регистрация на церемонию"
//                    }
//                }
                p {
                    +"Для участия в конкурсной и научно-деловой программе Этнофестиваля достаточно:"

                }
                indentedDiv {
                    css {
                        margin(2.px, 0.px)
                    }
                    +"выбрать в разделе "
                    a(href = Pages.main.path) {
                        attrs.onClickFunction = { e ->
                            e.preventDefault()
                            window.scrollTo(0.0, 0.0)
                            document.getElementsByClassName("h-join")[0]?.let { hJoin ->
                                animate(3000.0, arcTiming.inOut()) {
                                    (hJoin as HTMLElement).style.backgroundColor =
                                        getPageColor(Pages.join, false)
                                            .mix(RGBA(255, 230, 180), it)
                                            .toColor().value
                                }
                            }
                        }
                        +"\"Принять участие\""
                    }
                    +" интересующий конкурс или раздел научно-деловой программы"
                }
                indentedDiv {
                    css {
                        margin(2.px, 0.px)
                    }
                    +"заполнить простую анкету и приложить работу."
                }
                styledImg(src = "https://sun9-61.userapi.com/oqGLjke0fZyLU3WSHAkRdJU95n2b77KtorMEyg/12B5kUc2b6g.jpg") {
                    css {
                        marginTop = 10.px
                        width = 100.pct
                    }
                }
            }
        }
    }
}