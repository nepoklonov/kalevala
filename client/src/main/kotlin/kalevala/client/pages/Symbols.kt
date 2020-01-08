package kalevala.client.pages

import kalevala.client.send
import kalevala.client.stucture.*
import kalevala.common.Request
import kalevala.common.Symbol
import kalevala.common.interpretation.ImageDirs
import kalevala.common.interpretation.YamlRef
import kotlinx.css.*
import kotlinx.serialization.list
import styled.*

class SymbolsComponent(props: PageProps) : StandardPageComponent<YamlListState<Symbol>>(props) {
    init {
        initYamlListState()
        Request.GetYaml(YamlRef.SymbolsYaml).send(Symbol.serializer().list, ::updateYamlListState)
    }

    override fun StyledDOMBuilder<*>.page() {
        css {
            display = Display.flex
            justifyContent = JustifyContent.spaceAround
            alignContent = Align.flexStart
            flexWrap = FlexWrap.wrap
        }
        state.yaml.forEachIndexed { index, symbol ->
            styledDiv {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.column
                    alignItems = Align.center
                    textAlign = TextAlign.center
                    width = 300.px
                    padding(25.px, 0.px, 25.px, 0.px)
                }
                styledImg(src = (ImageDirs.symbols file symbol.photo).path) {
                    css {
                        maxHeight = 120.px
                        maxWidth = 270.px
                    }
                }
                styledSpan {
                    css {
                        paddingTop = 10.px
                    }
                    +symbol.text
                }
            }
        }
    }
}
