package kalevala.client.stucture

import kalevala.client.*
import kalevala.client.elements.image.scaledImage
import kalevala.common.interpretation.getSectionIcon
import kotlinx.css.*
import kotlinx.css.properties.borderLeft
import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.DIV
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.asList
import react.*
import react.router.dom.navLink
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

interface HNavProps : RProps {
    var section: Section
    var current: String
    var content: StyledDOMBuilder<DIV>.() -> Unit
}

interface HNavState : RState {
    var selected: Boolean
    var lineLength: Pair<Double, Double>
}

class HNavElement : RComponent<HNavProps, HNavState>() {
    private val isMain
        get() = props.current == Section.Main.url
    private val shouldBeOpened
        get() = state.selected || isMain

    init {
        state.selected = false
        state.lineLength = 0.0 to 0.0
    }

    private fun StyledDOMBuilder<CommonAttributeGroupFacade>.addSelectListener() {
        attrs {
            onMouseEnterFunction = {
                setState { selected = true }
            }
            onMouseLeaveFunction = {
                setState { selected = false }
            }
        }
    }

    private fun RBuilder.divLink(color2: Color, to: String, title: String) = styledDiv {
        css {
            width = 100.pct
            fontSize = 16.px
            display = Display.flex
            alignItems = Align.center

        }
        styledDiv {
            css {
                color = color2
                margin(0.px, 5.px)
            }
            +"\u25C6"
        }
        styledDiv {
            css {
                margin(7.px, 0.px)
                children("a") {
                    +MainStyles.normalA
                    color = Color.black
                    hover {
                        color = Color.darkSlateBlue
                    }
                }
                if (to == props.current) +MainStyles.current
            }
            navLink(to) {
                +title
            }

        }
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                marginBottom = 5.px
                justifySelf = JustifySelf.center
            }
            scaledImage(getSectionIcon(props.section.self.ref.name)) {
                addSelectListener()
            }
        }
        styledDiv {
            ref { node ->
                if (node != undefined && props.section.pages.isNotEmpty()) {
                    val newLineLength = (node as Element).getBoundingClientRect().height / 2
                    if (state.lineLength.first != newLineLength) setState {
                        lineLength = lineLength.copy(first = newLineLength)
                    }
                }
            }
            addSelectListener()
            css {
                display = Display.flex
                position = Position.relative
                width = 100.pct
                display = Display.flex
                alignItems = Align.center
                justifyContent = JustifyContent.center
                textAlign = TextAlign.center
            }
            if (shouldBeOpened) {
                styledDiv {
                    css {
                        fontSize = 22.px
                        position = Position.absolute
                        left = 2.4.px
                        color = getPageColor(props.section.self.ref, true)
                    }
                    +"\u25C6"
                }
                styledDiv {
                    css {
                        position = Position.absolute
                        borderLeft(1.px, BorderStyle.solid, getPageColor(props.section.self.ref, true))
                        height = state.lineLength.run { first + second }.toInt().px
                        top = 16.px
                        left = 11.1.px
                    }
                }
            }
            styledDiv {
                css {
                    width = 70.pct
                    fontWeight = FontWeight.bold
                    child("a") {
                        fontWeight = FontWeight.bold
                    }
                    textAlign = TextAlign.center
                    if (props.current.startsWith(props.section.url)) +MainStyles.current
                }
                props.section.title.let {
                    if (props.section == Section.Kalevala) navLink(props.section.url) { +it } else +it
                }
            }
        }
        styledDiv {
            css {
                if (!isMain) height = 0.px
            }
            styledDiv {
                if (shouldBeOpened) {
                    addSelectListener()
                    css {
                        if (isMain) height = 100.pct
                        paddingRight = 5.px
                        backgroundColor = getPageColor(props.section.self.ref, false)
                    }
                    if (props.section.pages.isNotEmpty()) {
                        ref { node ->
                            if (node != undefined) {
                                val links = (node as Node).childNodes.asList()
                                val newLineLength = links.sumByDouble { e ->
                                    (e as Element).getBoundingClientRect().height.let {
                                        if (links.indexOf(e) == links.lastIndex) it / 2 else it
                                    }
                                }
                                if (state.lineLength.second != newLineLength) setState {
                                    lineLength = lineLength.copy(second = newLineLength)
                                }
                            }
                        }
                    }
                    props.run { content() }
                    props.section.pages.forEach {
                        divLink(getPageColor(props.section.self.ref, true), it.url, it.title)
                    }
                }
            }
        }
    }
}