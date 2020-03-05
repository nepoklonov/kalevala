package kalevala.client

import kotlinx.css.*
import kotlinx.html.DIV
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

interface FrameProps : RProps {
    var close: () -> Unit
    var width: LinearDimension
    var height: LinearDimension
    var content: StyledDOMBuilder<DIV>.() -> Unit
}

class FrameComponent : RComponent<FrameProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                display = Display.flex
                justifyContent = JustifyContent.center
                alignItems = Align.center
                position = Position.fixed
                top = 0.px
                left = 0.px
                width = 100.pct
                height = 100.pct
                zIndex = 10
                overflow = Overflow.auto
            }
            styledDiv {
                attrs.onClickFunction = {
                    props.close()
                }
                css {
                    position = Position.absolute
                    width = 100.pct
                    height = 100.pct
                    backgroundColor = Color.black
                    opacity = 0.7
                    cursor = Cursor.pointer
                }
            }
            styledDiv {
                css {
                    width = props.width
                    height = props.height
                    backgroundColor = Color.white
                    zIndex = 11
                    display = Display.flex
                    position = Position.relative
                    overflowY = Overflow.auto
                }
                styledDiv {
                    css {
                        position = Position.absolute
                        right = (-45).px
                        //right = 15.px
                        top = 15.px
                        cursor = Cursor.pointer
                        width = 30.px
                        height = 30.px
                        display = Display.flex
                        justifyContent = JustifyContent.center
                        alignItems = Align.center
                        backgroundColor = rgba(255, 255, 255, 0.5)
                        backgroundRepeat = BackgroundRepeat.noRepeat
                        backgroundSize = "60%"
                        backgroundPosition = "center center"
                        backgroundImage = Image("url('/images/design/exit.png')")
                        opacity = 0.6
                        hover {
                            opacity = 1
                        }
                    }
                    attrs.onClickFunction = {
                        props.close()
                    }
                }
                (props.content)()
            }
        }
    }
}
