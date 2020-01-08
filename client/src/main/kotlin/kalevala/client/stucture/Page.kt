package kalevala.client.stucture

import kotlinx.css.*
import kotlinx.html.DIV
import kotlinx.html.id
import react.*
import react.dom.h2
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv

typealias StaticPage = StandardPageComponent<PageState>

interface PageProps : RProps {
    var current: String
    var pageTitle: String
    var keyWords: List<String>
}

interface PageState : RState

interface YamlState<T> : PageState {
    var yaml: T
}

interface YamlListState<T> : YamlState<MutableList<T>>

fun <T> RComponent<out RProps, out YamlListState<T>>.initYamlListState() {
    state.yaml = mutableListOf()
}

fun <T> RComponent<out RProps, out YamlListState<T>>.updateYamlListState(newYamlState: List<T>) {
    setState {
        yaml = newYamlState.toMutableList()
    }
}

fun <T> RComponent<out RProps, out YamlState<T>>.updateYamlState(newYamlState: T) {
    setState {
        yaml = newYamlState
    }
}

abstract class PageComponent<S : PageState>(props: PageProps) : RComponent<PageProps, S>(props) {
    inline fun RBuilder.pageDiv(content: StyledDOMBuilder<DIV>.() -> Unit) {
        styledDiv {
            attrs.id = "page"
            css {
//                backgroundColor = rgba(255, 255, 255, 0.9)
                padding(5.px)
                //TODO remove this stupid code
                width = 94.pct
                minHeight = 500.px
            }
            content()
        }
    }

    override fun RBuilder.render() {
        pageDiv {
            page()
        }
    }

    abstract fun StyledDOMBuilder<*>.page()
}

abstract class StandardPageComponent<S : PageState>(props: PageProps) : PageComponent<S>(props) {
    override fun RBuilder.render() {
        pageDiv {
            css {
                flexDirection = FlexDirection.column
                alignItems = Align.flexStart
                justifyContent = JustifyContent.flexStart
            }
            h2 {
                +props.pageTitle
            }
            styledDiv {
                attrs.id = "page-body"
                css {
                    marginTop = 10.px
                    width = 100.pct
                    height = 100.pct
                }
                page()
            }
        }
    }
}