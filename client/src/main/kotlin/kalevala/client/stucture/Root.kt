package kalevala.client.stucture

import kalevala.client.PageClass
import kalevala.client.RoutedProps
import kalevala.client.stucture.header.HeaderComponent
import kotlinx.css.*
import kotlinx.html.id
import react.RBuilder
import react.RComponent
import react.RState
import styled.css
import styled.styledDiv

interface RootProps : RoutedProps, PageProps {
    var pageComponent: PageClass
}

class RootComponent(props: RootProps) : RComponent<RootProps, RState>(props) {

    override fun RBuilder.render() {
        styledDiv {
            attrs.id = "root"
            css {
                width = 100.pct
                height = 100.pct
                display = Display.flex
                flexWrap = FlexWrap.wrap
                alignContent = Align.flexStart
                justifyContent = JustifyContent.center
            }
            setBackground()
            child(HeaderComponent::class) {
                attrs.current = props.current
            }
//            child(LeftNavComponent::class) {}
//            child(ContactsPreviewComponent::class) {}
            child(props.pageComponent) {
//                console.log(props.pageComponent)
                attrs.current = props.current
                attrs.pageTitle = props.pageTitle
            }
            child(FooterComponent::class) {}
        }
    }
}
