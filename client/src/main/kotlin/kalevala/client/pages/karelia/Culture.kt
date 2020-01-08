package kalevala.client.pages.karelia

import kalevala.client.indentedDiv
import kalevala.client.send
import kalevala.client.stucture.*
import kalevala.common.KareliaCulture
import kalevala.common.Request
import kalevala.common.interpretation.YamlRef
import kotlinx.serialization.list
import react.dom.a
import styled.StyledDOMBuilder

class Culture(props: PageProps) : StandardPageComponent<YamlListState<KareliaCulture>>(props) {
    init {
        initYamlListState()
        Request.GetYaml(YamlRef.KareliaCultureYaml).send(KareliaCulture.serializer().list, ::updateYamlListState)
    }

    override fun StyledDOMBuilder<*>.page() {
        state.yaml.forEach {
            indentedDiv {
                a(href = it.link) {
                    +it.name
                }
            }
        }
    }
}
