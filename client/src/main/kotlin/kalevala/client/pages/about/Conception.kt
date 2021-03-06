package kalevala.client.pages.about

import kalevala.client.indentedDiv
import kalevala.client.highlightedSpan
import kalevala.client.redH3
import kalevala.client.stucture.PageProps
import kalevala.client.stucture.StaticPage
import kalevala.common.interpretation.Documents
import kotlinx.css.*
import react.dom.h3
import react.dom.p
import styled.StyledDOMBuilder
import styled.css
import styled.styledA
import styled.styledDiv

class Conception(props: PageProps) : StaticPage(props) {
    override fun StyledDOMBuilder<*>.page() {

        styledDiv {
            css {
                padding(0.px, 30.px)
                child("p") {
                    margin(10.px, 0.px)
                }
            }
            styledA(href = Documents.conceptionPDF.path) {
                css {
                    marginBottom = 20.px
                }
                +"Скачать «Концепцию этнофестиваля \"Земля Калевалы — 2021\".pdf»"
            }
            redH3 {
                +"Краткое описание"
            }
            p {
                +"Международный этнофесиваль «Земля Калевалы» проводится ежегодно, начиная с 2006 года и является самой крупной совместной инициативой Санкт-Петербурга, Ленинградской области и Республики Карелия, посвященной истории, самобытной культуре и традициям Карелии и выдающемуся памятнику культуры – карело-финскому эпосу «Калевала»."
            }
            p {
                +"Название этнофестиваля, тематическое наполнение и стилистическое решение основываются на творческом, культурном и историческом наследии карело-финского эпоса «Калевала» – литературного памятника мировой культуры, собранного и опубликованного в 1835 году Элиасом Леннротом (Elias Lönnrot, 1802–1884), на основе собранных карельских и финских песен."
            }
            p {
                +"Этнофестиваль объединяет государственные, общественные и частные организации и учреждения, творческие и научные коллективы, индивидуальных участников из северных регионов России и Республики Финляндия (геокультурного пространства Калевалы – эпической страны, в которой живут и действуют герои эпоса), а также широкие круги участников и партнеров, в своей профессиональной и творческой деятельности работающих над тематикой эпоса, сохранения и популяризации самобытной материальной и духовной культуры Карелии, экологических, туристических и исследовательских проектах в регионе."
            }
            p {
                +"Этнофестиваль состоит из основной и параллельной программы, научно-деловой, экспедиционной и творческой части. Основная программа включает в себя: церемонию награждение победителей конкурсов, проводимых в рамках Этнофестиваля, научную-деловую и творческую часть, проводится в Санкт-Петербурге. Параллельная программа включает в себя выставки, конференции, круглые столы, экспедиции, концерты и иные формы научной и творческой работы, проводимые под эгидой Этнофестиваля в течение года."
            }
            p {
                +"Научно-деловая часть Этнофестиваля включает в себя серию круглых столов, конференций, издательскую деятельность. В рамках экспедиционной части Этнофестиваля организуются исследовательские выезды и экспедиции, международные и межрегиональные перекрестные визиты. Творческая часть Этнофестиваля включает в себя: выставочную и концертную программу, мастер-классы."
            }
            redH3 {
                +"Цель Этнофестиваля"
            }
            p {
                highlightedSpan {
                    +"Привлечение внимания к сохранению и популяризации самобытной материальной и духовной культуры, а также лучших образцов современного творческого, научного, туристического, экологического и образовательного потенциала Карелии на основе системного международного и межрегионального сотрудничества в формате Этнофестиваля."
                }
            }
            redH3 {
                +"Задачи"
            }
            indentedDiv {
                +"Историко-культурная: знакомство широкой общественности, и в особенности молодого поколения с самобытной историей, культурным достоянием Карелии, наследием карело-финского эпоса «Калевала»"
            }
            indentedDiv {
                +"Просветительская: широкая популяризация историко-культурного, этнографического, туристического, спортивного потенциала Карелии"
            }
            indentedDiv {
                +"Имиджевая: представление на федеральном уровне Карелии, как современного, активно развивающегося и открытого к сотрудничеству региона"
            }
            indentedDiv {
                +"Творческая: выявление и поддержка талантливых деятелей культуры и искусства, творческих групп и коллективов, раскрывающих в своем творчестве тему Карелии и карело-финского эпоса «Калевала»"
            }
            indentedDiv {
                +"Методическая: сбор, систематизация и внедрение передовых методик, проектных разработок и программ, раскрывающих потенциал Карелии карело-финского эпоса «Калевала»"
            }
        }
    }

}