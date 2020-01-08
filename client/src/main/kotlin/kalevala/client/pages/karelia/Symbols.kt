package kalevala.client.pages.karelia

import kalevala.client.redH3
import kalevala.client.send
import kalevala.client.stucture.*
import kalevala.common.KareliaSymbol
import kalevala.common.Request
import kalevala.common.interpretation.ImageDirs
import kalevala.common.interpretation.YamlRef
import kotlinx.css.*
import kotlinx.serialization.list
import react.dom.a
import react.dom.p
import styled.StyledDOMBuilder
import styled.css
import styled.styledImg
import styled.styledP

class Symbols(pageProps: PageProps) : StandardPageComponent<YamlListState<KareliaSymbol>>(pageProps) {
    init {
        initYamlListState()
        Request.GetYaml(YamlRef.KareliaSymbolsYaml).send(KareliaSymbol.serializer().list, ::updateYamlListState)
    }

    override fun StyledDOMBuilder<*>.page() {
        if (state.yaml != undefined) {
            state.yaml.forEach { kareliaSymbol ->
                redH3 {
                    +kareliaSymbol.header
                }
                if (kareliaSymbol.picture != "") styledImg (src = (ImageDirs.karelia file kareliaSymbol.picture).path) {
                    css {
                        maxHeight = 120.px
                        margin(5.px)
                    }
                }
                kareliaSymbol.text.split('\n').forEach {
                    p {
                        +it
                    }
                }
                kareliaSymbol.song.split('\n').forEach {
                    styledP {
                        css {
                            textAlign = TextAlign.center
                        }
                        +it
                    }
                }
                if (kareliaSymbol.file != "") a (href = kareliaSymbol.file) {
                    +"Прослушать композицию"
                }
                if (kareliaSymbol.source != "") styledP {
                    css {
                        textAlign = TextAlign.right
                    }
                    +kareliaSymbol.source
                }
            }
        }
    }
}