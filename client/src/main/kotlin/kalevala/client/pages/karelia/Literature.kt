package kalevala.client.pages.karelia

import kalevala.client.indentedDiv
import kalevala.client.redH3
import kalevala.client.send
import kalevala.client.stucture.*
import kalevala.common.KareliaLiterature
import kalevala.common.Request
import kalevala.common.interpretation.YamlRef
import kotlinx.serialization.list
import react.dom.a
import react.dom.p
import styled.*

class Literature(props: PageProps) : StandardPageComponent<YamlListState<KareliaLiterature>>(props) {
    init {
        initYamlListState()
        Request.GetYaml(YamlRef.KareliaLiteratureYaml).send(KareliaLiterature.serializer().list, ::updateYamlListState)
    }

    override fun StyledDOMBuilder<*>.page() {
        p {
            a(href = "http://library.karelia.ru/") {
                +"Фонд Национальной библиотеки республики Карелия"
            }
        }
        state.yaml.forEach { kareliaLiterature ->
            redH3 {
                +kareliaLiterature.header
            }
            kareliaLiterature.texts.forEach {
                indentedDiv {
                    +it
                }
            }
        }
    }
}
