package kalevala.client.pages.about

import kalevala.client.indentedDiv
import kalevala.client.redH3
import kalevala.client.stucture.PageProps
import kalevala.client.stucture.StaticPage
import kalevala.common.interpretation.ImageDirs
import kotlinx.css.*
import kotlinx.html.H3
import react.RBuilder
import react.dom.h3
import react.dom.p
import styled.*


fun RBuilder.centerImage(vararg src: String) = styledDiv {
    css {
        width = 100.pct
        display = Display.flex
        justifyContent = JustifyContent.center
        height = 200.px
    }
    src.forEach {
        styledImg(src = (ImageDirs.history file it).path) {
            css {
                height = 100.pct
            }
        }
    }
}

inline fun RBuilder.bigH3(crossinline content: StyledDOMBuilder<H3>.() -> Unit) = redH3 {
    css {
        fontSize = 30.px
    }
    content()
}

class History(props: PageProps) : StaticPage(props) {
    override fun StyledDOMBuilder<*>.page() {

        styledDiv {
            css {
                padding(0.px, 30.px)
                child("p") {
                    margin(10.px, 0.px)
                }
                child("h3") {
                    marginTop = 30.px
                    textAlign = TextAlign.center
                }
            }

            bigH3 {
                +"Символы этнофестиваля"
            }

            redH3 {
                +"Эмблема"
            }

            centerImage("1.jpg", "2.jpg")

            p {
                +"Эмблема Этнофестиваля разработана дизайнером Кристиной Мельник. За основу взят стилизованный в традициях народного орнамента карельский ковш, как символ полноты и изобилия. Внутри стилизованного ковша четыре разноцветных сегмента, символизирующие основные направления Этнофестиваля: научное, творческое, туристическое и экологическое."
            }

            redH3 {
                +"Рукописная Калевала"
            }

            centerImage("3.jpg")

            p {
                +"Каждый участник Этнофестиваля может вписать пером строку карело-финского эпоса «Калевала» в уникальный фолиант"
            }

            redH3 {
                +"Калевальское древо желаний"
            }

            centerImage("4.jpg")

            p {
                +"Повязать символическую ленточку на Калевальское древо желаний, загадав при этом самое заветное желание – одна из традиций Этнофестиваля"
            }

            redH3 {
                +"Кантеле"
            }

            centerImage("5.jpg")

            p {
                +"Звучание Кантеле – карельского и финского народного  струнного инструмента – неотъемлемая часть программы Этнофестиваля"
            }

            redH3 {
                +"Герои Калевалы"
            }

            centerImage("6.jpg")

            p {
                +"Персонажи Калевалы – Вяйнямейнен, Айно, Ильмаринен, Лемминкяйнен, Ёукахайнен традиционно встречают гостей Этнофестиваля"
            }

            bigH3 {
                +"Краткое описание"
            }

            p {
                +"Международный этнофестиваль «Земля Калевалы» (далее – Этнофестиваль) проводится ежегодно с 2006 года по инициативе организационного комитета. "
            }

            p {
                +"Название этнофестиваля, тематическое наполнение и стилистическое решение основываются на творческом, культурном и историческом наследии карело-финского эпоса «Калевала» – литературного памятника мировой культуры,  собранного и опубликованного в 1835 году Элиасом Леннротом (Elias Lönnrot, 1802–1884), на основе собранных карельских и финских песен."
            }

            p {
                +"Этнофестиваль объединяет государственные, общественные и частные организации и учреждения, творческие и научные коллективы, индивидуальных участников из северных регионов России и Республики Финляндия  (геокультурного пространства Калевалы – эпической страны, в которой живут и действуют герои эпоса), а также широкие круги участников и партнеров, в своей профессиональной и творческой деятельности работающих над тематикой эпоса, сохранения и популяризации самобытной материальной и духовной культуры Карелии, экологических, туристических и исследовательских проектах в регионе. "
            }

            p {
                +"Старт конкурсной программы Этнофестиваля традиционно объявляется в сентябре текущего года, финал и награждение победителей – 28 февраля, в международный день Калевалы (фин. Kalevalan päivä, швед. Kalevaladagen)."
            }

            p {
                +"Фестиваль состоит из основной и параллельной программы, научно-деловой, экспедиционной и творческой части. Основная программа включает в себя: церемонию награждение победителей конкурсов, проводимых в рамках Этнофестиваля, научную-деловую и творческую часть, проводится в Санкт-Петербурге. Параллельная программа включает в себя выставки, конференции, круглые столы, экспедиции, концерты и иные формы научной и творческой работы, проводимые под эгидой Этнофестиваля в течение года. "
            }

            p {
                +" Научно-деловая часть Этнофестиваля включает в себя серию круглых столов, конференций, издательскую деятельность. В рамках экспедиционной части Этнофестиваля организуются   исследовательские выезды и экспедиции, международные и межрегиональные перекрестные визиты. Творческая часть Этнофестиваля включает в себя: выставочную и концертную программу, мастер-классы. "
            }

            bigH3 {
                +"Цель этнофестиваля"
            }

            p {
                +"Сохранение и популяризация самобытной материальной и духовной культуры, а также лучших образцов современного творческого, научного, туристического, экологического  и образовательного потенциала территорий эпической «Земли Калевалы» на основе системного международного и межрегионального сотрудничества в формате Этнофестиваля."
            }

            bigH3 {
                +"Задачи этнофестиваля"
            }

            indentedDiv { +"Историко-культурная:" }

            p {
                +"знакомство широкой общественности, и в особенности молодого поколения с самобытной историей, культурным достоянием Карелии, наследием карело-финского эпоса «Калевала»."
            }

            indentedDiv { +"Просветительская:" }

            p {
                +"широкая популяризация историко-культурного, этнографического, туристического, спортивного потенциала Карелии."
            }

            indentedDiv { +"Миротворческая:" }

            p {
                +"вклад в укрепление добрососедских деловых и творческих контактов территорий и эпического региона «Калевалы» (Санкт-Петербург, Ленинградская область, Республика Карелия, Республика Финляндия», северных стран и территорий проживания финно-угорских народов."
            }

            indentedDiv { +"Имиджевая:" }

            p {
                +"представление на региональном и международном уровне Карелии, как современного, активно развивающегося и открытого к сотрудничеству региона."
            }

            indentedDiv { +"Эколого-туристическая:" }

            p {
                +"предоставление актуальной информации о туристическом кластере региона, экологических проектах и программах, формирование бережного отношения к богатству природного мира Карелии, привлечение внимание к экологическим проблемам региона, широкая популяризация туристического и рекреационного потенциала."
            }

            indentedDiv { +"Творческая" }

            p {
                +"выявление и поддержка талантливых деятелей культуры и искусства, творческих групп и коллективов, раскрывающих в своем творчестве тему Карелии и карело-финского эпоса «Калевала»."
            }

            indentedDiv { +"Методическая" }

            p {
                +"Сбор, систематизация и внедрение передовых методик, проектных разработок и программ, раскрывающих потенциал Карелии карело-финского эпоса «Калевала»."
            }
        }
    }

}