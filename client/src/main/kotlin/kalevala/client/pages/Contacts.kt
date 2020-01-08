package kalevala.client.pages

import kotlinx.css.*
import kotlinx.serialization.list
import react.dom.a
import kalevala.client.gray70Color
import kalevala.client.send
import kalevala.client.stucture.*
import kalevala.common.Request
import kalevala.common.interpretation.YamlRef
import kalevala.common.Contact
import kalevala.common.interpretation.ImageDirs
import styled.*

class ContactsComponent(props: PageProps) : StandardPageComponent<YamlListState<Contact>>(props) {
    init {
        initYamlListState()
        Request.GetYaml(YamlRef.ContactsYaml).send(Contact.serializer().list, ::updateYamlListState)
    }

    override fun StyledDOMBuilder<*>.page() {
        state.yaml.forEach {
            styledDiv {
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                    alignItems = Align.center
                    margin(7.px, 30.px)
                }
                a(href = it.link, target = "_blank") {
                    styledImg(src = (ImageDirs.contacts file it.logo).path) {
                        css {
                            width = 20.px
                            height = 20.px
                        }
                    }
                }
                styledSpan {
                    css {
                        position = Position.relative
                        top = (-3).px
                        paddingLeft = 5.px
                        color = gray70Color
                    }
                    +it.text
                }
            }
        }
    }
}