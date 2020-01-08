package kalevala.client.pages.karelia

import kalevala.client.indentedDiv
import kalevala.client.send
import kalevala.client.stucture.*
import kalevala.common.KareliaTourism
import kalevala.common.Request
import kalevala.common.interpretation.ImageDirs
import kalevala.common.interpretation.YamlRef
import kotlinx.css.*
import kotlinx.serialization.list
import react.dom.a
import styled.*

class Tourism(props: PageProps) : StandardPageComponent<YamlListState<KareliaTourism>>(props) {
    init {
        initYamlListState()
        Request.GetYaml(YamlRef.KareliaTourismYaml).send(KareliaTourism.serializer().list, ::updateYamlListState)
    }

    override fun StyledDOMBuilder<*>.page() {
        state.yaml.forEach {
            indentedDiv {
                styledImg (src = (ImageDirs.tourism / it.photo).path) {
                    css {
                        width = 120.px
                        marginRight = 30.px
                    }
                }
                a(href = it.link) {
                    +it.name
                }
            }
        }
    }
}
