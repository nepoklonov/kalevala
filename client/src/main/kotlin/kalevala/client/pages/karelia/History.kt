package kalevala.client.pages.karelia

import kalevala.client.send
import kalevala.client.stucture.*
import kalevala.common.KareliaHistory
import kalevala.common.Request
import kalevala.common.interpretation.YamlRef
import kotlinx.css.*
import kotlinx.serialization.list
import react.dom.p
import styled.*

class History(props: PageProps) : StandardPageComponent<YamlListState<KareliaHistory>>(props) {
    init {
        initYamlListState()
        Request.GetYaml(YamlRef.KareliaHistoryYaml).send(KareliaHistory.serializer().list, ::updateYamlListState)
    }

    override fun StyledDOMBuilder<*>.page() {
        state.yaml.forEach { kareliaHistory ->
//            h3 {
//                +kareliaHistory.header
//            }
            kareliaHistory.text.split('\n').forEach {
                p {
                    +it
                }
            }
            styledP {
                css {
                    textAlign = TextAlign.right
                }
                +kareliaHistory.source
            }
        }
    }
}
