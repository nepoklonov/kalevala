package kalevala.client.pages

import kalevala.client.send
import kalevala.client.stucture.*
import kalevala.common.Request
import kotlinx.serialization.list
import kotlinx.serialization.serializer
import react.dom.br
import react.dom.h3
import styled.StyledDOMBuilder

class AntiGallery(props: PageProps) : StandardPageComponent<YamlListState<String>>(props) {

    init {
        initYamlListState()
    }

    override fun StyledDOMBuilder<*>.page() {
        if (state.yaml.isEmpty()) {
            Request.ParticipantsEthnoToutGetAll().send(String.serializer().list, ::updateYamlListState)
        }
        h3 {
            +"Список участников: "
        }
        state.yaml.forEachIndexed { index, s ->
            val i = index + 1
            +"$i. $s"
            br {}
        }
    }

}