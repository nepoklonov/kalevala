package kalevala.client.pages.kalevala

import kalevala.client.gray70Color
import kalevala.client.indentedDiv
import kalevala.client.pages.cw.*
import kalevala.client.stucture.PageProps
import kalevala.client.stucture.PageState
import kalevala.client.stucture.StandardPageComponent
import kalevala.common.interpretation.ImageDirs
import kotlinx.css.*
import kotlinx.html.ATarget
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.a
import styled.*
import kotlin.reflect.KClass

interface CommonwealthState : PageState {
    var subsection: CWSection?
}

enum class CWSection(val title: String, val klass: KClass<out RComponent<RProps, out RState>>) {
    About("Об организации", CWAbout::class),
    Team("Состав", CWTeam::class),
    Symbols("Награды и отличительные знаки", CWSymbols::class),
    News("Новости", CWNews::class),
    Join("Вступить", CWJoinComponent::class),
    Contacts("Контакты", CWContacts::class),
}

class CWContacts : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        indentedDiv {
            +"Официальная группа ВК"
            a(href = "https://vk.com/sodryzestvo_karelia", target = ATarget.blank) {
                +"vk.com/sodryzestvo_karelia"
            }
        }
        indentedDiv {
            +"E-mail: vgkarelia@yandex.ru"
        }
    }
}

class CommonwealthComponent(pageProps: PageProps) : StandardPageComponent<CommonwealthState>(pageProps) {
    init {
        state.subsection = null
    }

    override fun StyledDOMBuilder<*>.page() {
        styledDiv {
            styledDiv {
                css {
                    width = 100.pct
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    alignContent = Align.center
                    alignItems = Align.center
                }
                styledImg(src = (ImageDirs.commonwealth file "logo.png").path) {
                    css {
                        width = 100.px
                    }
                }
                styledSpan {
                    css {
                        margin(10.px, 0.px, 30.px, 0.px)
                    }
                    +"Межрегиональная общественная организация «Карельское Содружество»"
                }
            }
            styledDiv {
                css {
                    width = 100.pct
                    display = Display.flex
                    justifyContent = JustifyContent.spaceAround
                }
                CWSection.values().forEach { s ->
                    styledA(href = "#") {
                        css {
                            color = gray70Color
                        }
                        attrs.onClickFunction = {
                            it.preventDefault()
                            setState {
                                subsection = s
                            }
                        }
                        +s.title
                    }
                }
            }
            styledDiv {
                css {
                    margin(20.px)
                }
                if (state.subsection != null) {
                    child(state.subsection!!.klass) { }
                }
            }
        }
    }
}