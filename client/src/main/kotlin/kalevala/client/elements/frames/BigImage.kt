package kalevala.client.elements.frames

import kalevala.client.FrameComponent
import kalevala.client.FrameProps
import kotlinx.css.*
import react.*
import styled.css
import styled.styledDiv

interface BigImageProps : RProps {
    var close: () -> Unit
    var src: String
    var infoBlock: RBuilder.() -> Unit
}

interface BigImageState : RState

class BigImage : RComponent<BigImageProps, BigImageState>() {
    private val imageContent = fun RBuilder.() {
        styledDiv {
            css {
                backgroundImage = Image("url('${props.src}')")
                backgroundRepeat = BackgroundRepeat.noRepeat
                backgroundSize = "cover"
                backgroundPosition = "center center"
                width = 500.px
                height = 500.px
            }
        }
        (props.infoBlock)()

    }

    override fun RBuilder.render() {
        child<FrameProps, FrameComponent> {
            attrs.close = props.close
            attrs.content = imageContent
            attrs.width = 800.px
            attrs.height = 500.px
        }
    }

}
