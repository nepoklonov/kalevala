package kalevala.client.pages

import kalevala.client.elements.image.imageInDiv
import kalevala.client.gray50Color
import kalevala.client.gray70Color
import kalevala.client.gridArea
import kalevala.client.gridTemplateAreas
import kalevala.client.stucture.PageComponent
import kalevala.client.stucture.PageProps
import kalevala.client.stucture.PageState
import kalevala.common.interpretation.ImageDirs
import kalevala.common.quote
import kotlinx.css.*
import kotlinx.css.LinearDimension.Companion.auto
import kotlinx.css.properties.deg
import kotlinx.css.properties.rotate
import kotlinx.css.properties.transform
import react.RBuilder
import react.dom.p
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledP
import kotlin.browser.document
import kotlin.reflect.KProperty

fun RBuilder.news(title: String, subtitle: String, content: String, imageSrc: String) {
    styledDiv {
        css {
            display = Display.grid
            gridTemplateAreas("image title", "image subtitle", "image imageContent")
            gridTemplateRows = GridTemplateRows(auto, 1.fr, auto)
            gridTemplateColumns = GridTemplateColumns(2.fr, 3.fr)
            rowGap = RowGap(5.px.value)
            columnGap = ColumnGap(20.px.value)
        }
        imageInDiv(imageSrc, "cover") {
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
                gridArea = "imageContent"
                fontSize = 10.pt
            }
            +content
        }
    }
}


class MainComponent(props: PageProps) : PageComponent<PageState>(props) {
    init {
        document.title = "Земля Калевалы"
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
                    gridTemplateRows = GridTemplateRows(40.px, 1.fr, 40.px)
                    rowGap = RowGap(10.px.value)
                }
                imageInDiv((ImageDirs.design / "go-left.png").path, "contain") {
                    css.transform.rotate(90.deg)
                    css.opacity = 0.3
                }
                styledDiv {
                    css {
                        display = Display.grid
                        rowGap = RowGap(20.px.value)
                    }
                    news("Земля Калевалы — 2020", "Идёт приём заявок",
                        "На официальном сайте XIV Международного этнофестиваля ${"Земля Калевалы — 2020".quote()} идет прием заявок на участие в конкурсной и научно-деловой программе. Прими участие в крупнейшем ежегодном смотре творческих, научных, культурных и туристических достижений, посвященных прекрасной Карелии!",
                        "/uploads/images/news/copies/2019-11-21_18-15-43-418_4p1iwvy/400x288.png")
                    news("Районы побратимы", "",
                        "Доброй традицией ежегодных программ Международного этнофестиваля \"Земля Калевалы\" стали перекрестные дни культуры районов-побратимов Санкт-Петербурга и Республики Карелия. В 2020 году такими районами станут: Московский район Санкт-Петербурга и Калевальский район Республики Карелия. В рамках перекрестных дней культуры, состоятся передвижные выставки, показ тематических фильмов, выступления творческих коллективов.",
                        "/uploads/images/news/copies/2019-11-24_08-02-43-906_ihbuv9y/400x289.png")
                }
                imageInDiv((ImageDirs.design / "go-left.png").path, "contain") {
                    css.transform.rotate(270.deg)
                    css.opacity = 0.3
                    
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
                    +"Актуальная информация"
                }
                p {
                    +"Международный этнофестиваль «Земля Калевалы» проводится ежегодно с 2006 года. Название, тематическое наполнение и стилистическое решение основываются на творческом, культурном и историческом наследии карело-финского эпоса «Калевала» – литературного памятника мировой культуры, собранного и опубликованного в 1835 году Элиасом Леннротом, на основе собранных карельских и финских песен."
                }
                p {
                    +"Этнофестиваль объединяет государственные, общественные и частные организации и учреждения, творческие и научные коллективы, индивидуальных участников из северных регионов России и Республики Финляндия (геокультурного пространства Калевалы – эпической страны, в которой живут и действуют герои эпоса)."
                }
                p {
                    +"Старт конкурсной программы Этнофестиваля традиционно объявляется в сентябре текущего года, финал и награждение победителей – 28 февраля, в международный день Калевалы."
                }
            }
        }
    }
}