package kalevala.client.pages.cw

import kalevala.client.send
import kalevala.client.stucture.YamlListState
import kalevala.client.stucture.initYamlListState
import kalevala.client.stucture.updateYamlListState
import kalevala.common.Request
import kalevala.common.Symbol
import kalevala.common.interpretation.ImageDirs
import kalevala.common.interpretation.YamlRef
import kotlinx.css.*
import kotlinx.serialization.list
import react.RBuilder
import react.RComponent
import react.RProps
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledSpan


class CWSymbols : RComponent<RProps, YamlListState<Symbol>>() {

    init {
        initYamlListState()
        Request.GetYaml(YamlRef.CWSymbols).send(Symbol.serializer().list, ::updateYamlListState)
    }

    override fun RBuilder.render() {

        styledDiv {
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
                    styledImg(src = (ImageDirs.commonwealth file symbol.photo).path) {
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
}