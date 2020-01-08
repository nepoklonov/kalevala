package kalevala.client.pages

import kalevala.client.GalleryBox
import kalevala.client.getGalleryType
import kalevala.client.send
import kalevala.client.stucture.*
import kalevala.common.FileAnswer
import kalevala.common.Request
import kalevala.common.getPluralForm
import kalevala.common.interpretation.ScaleType
import kalevala.common.interpretation.put
import kalevala.common.interpretation.x
import kalevala.common.models.participants.FormType
import kotlinx.css.*
import kotlinx.serialization.list
import react.RComponent
import react.RProps
import react.dom.a
import react.dom.br
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledH2

private val updateYaml: RComponent<out RProps, out YamlListState<FileAnswer>>.(FormType, Int, Int) -> Unit
    get() = { type, width, height ->
        Request.ParticipantsImagesGetAll(type, width x height put ScaleType.OUTSIDE).send(FileAnswer.serializer().list, ::updateYamlListState)
    }

class GalleryComponent(props: PageProps) : StandardPageComponent<YamlListState<FileAnswer>>(props) {

    object BoxProps {
        const val horizontalAmount = 4
        const val proportion = 0.75
        const val zoom = 0.8
    }

    init {
        initYamlListState()
    }

    override fun StyledDOMBuilder<*>.page() {
        child(GalleryBox::class) {
            attrs {
                content = state.yaml
                horizontalAmount = BoxProps.horizontalAmount
                proportion = BoxProps.proportion
                zoom = BoxProps.zoom
                getImages = { w, h -> updateYaml(getGalleryType(props.current)!!, w, h) }
                current = props.current
                infoBlock = { it ->
                    styledDiv {
                        css {
                            padding(20.px)
                            fontSize = 16.pt
                        }
                        styledH2 {
                            css.margin(10.px)
                            +it.info!![0]
                        }
                        br { }
                        +"${it.info!![1]},"
                        br {}
                        if (it.info!![2] != "") {
                            +it.info!![2].let { "$it " + it.toInt().getPluralForm("год", "года", "лет") + "," }
                            br { }
                        }
                        +it.info!![3]
                        br {}
                        a(target = "blank", href = it.bigSrc) {
                            +"Открыть полную версию"
                        }
                    }
                }
            }
        }
    }
}