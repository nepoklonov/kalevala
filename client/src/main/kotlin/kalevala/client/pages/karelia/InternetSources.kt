package kalevala.client.pages.karelia

import kalevala.client.indentedDiv
import kalevala.client.send
import kalevala.client.stucture.*
import kalevala.common.KareliaInternetSources
import kalevala.common.Request
import kalevala.common.interpretation.YamlRef
import kotlinx.serialization.list
import react.dom.a
import styled.StyledDOMBuilder

class InternetSources(props: PageProps) : StandardPageComponent<YamlListState<KareliaInternetSources>>(props) {
    init {
        initYamlListState()
        Request.GetYaml(YamlRef.KareliaInternetSourcesYaml)
            .send(KareliaInternetSources.serializer().list, ::updateYamlListState)
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
