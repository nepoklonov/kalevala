package kalevala.client.stucture

import kalevala.client.elements.image.scaledImage
import kotlinx.css.*
import react.RBuilder
import kalevala.common.interpretation.Images
import styled.css

fun RBuilder.setBackground() {
    scaledImage(Images.mainBackground) {
        css {
            width = 100.pct
            position = Position.absolute
            zIndex = -1
        }
    }
}