package kalevala.client.pages.about

import kalevala.client.GalleryBox
import kalevala.client.send
import kalevala.client.stucture.*
import kalevala.common.Request
import kalevala.common.SubGallery
import kalevala.common.getPluralForm
import kotlinx.css.*
import kotlinx.serialization.list
import react.RComponent
import react.RProps
import react.dom.a
import react.dom.br
import react.dom.h2
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledH2

private val updateYaml: RComponent<out RProps, out YamlListState<SubGallery>>.(Int, Int) -> Unit
    get() = { width, height ->
        Request.AboutGetPhotos(width, height).send(SubGallery.serializer().list, ::updateYamlListState)
    }

class PhotosComponent(props: PageProps) : StandardPageComponent<YamlListState<SubGallery>>(props) {

    object BoxProps {
        const val horizontalAmount = 7
        const val proportion = 0.75
        const val zoom = 0.8
    }

    init {
        initYamlListState()
        updateYaml(0, 0)
    }

    override fun StyledDOMBuilder<*>.page() {
        state.yaml.forEach {
            styledH2 {
                css {
                    margin(10.px, 0.px, 5.px, 0.px)
                }
                +"Земля Калевалы — ${it.title}"
            }
            child(GalleryBox::class) {
                attrs {
                    content = it.list
                    horizontalAmount = BoxProps.horizontalAmount
                    proportion = BoxProps.proportion
                    zoom = BoxProps.zoom
                    getImages = { _, _ -> }
                    current = props.current
                    infoBlock = { }
                }
            }
        }
    }
}