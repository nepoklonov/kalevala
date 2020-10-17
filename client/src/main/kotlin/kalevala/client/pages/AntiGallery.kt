package kalevala.client.pages

import kalevala.client.send
import kalevala.client.stucture.*
import kotlinx.serialization.list
import kotlinx.serialization.serializer
import react.dom.br
import react.dom.h3
import styled.StyledDOMBuilder
import kalevala.common.*
import kalevala.common.interpretation.Pages
import kalevala.common.models.participants.FormType
import react.setState

interface AntiGalleryState : YamlListState<String> {
    var current: String
}

class AntiGallery(props: PageProps) : StandardPageComponent<AntiGalleryState>(props) {

    init {
        initYamlListState()
    }

    override fun StyledDOMBuilder<*>.page() {
        if (state.yaml.isEmpty() or (state.current != props.current)) {
            val formType = when(props.current) {
                Pages.Gallery.ethnoTour.path -> FormType.EthnoTour
                Pages.Gallery.scientific.path -> FormType.Scientific
                Pages.Gallery.organize.path -> FormType.Organize
                else -> TODO()
            }
            Request.ParticipantsOpenDataGetAll(formType)
                .send(String.serializer().list) {
                    setState {
                        yaml = it.toMutableList()
                        current = props.current
                    }
                }
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