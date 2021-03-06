package kalevala.client.pages

import kotlinx.css.*
import kotlinx.serialization.list
import react.dom.a
import react.dom.p
import kalevala.client.gray50Color
import kalevala.client.send
import kalevala.client.stucture.*
import kalevala.common.Request
import kalevala.common.interpretation.YamlRef
import kalevala.common.Resource
import kalevala.common.interpretation.ImageDirs
import styled.*

class ResourcesComponent(props: PageProps) : StandardPageComponent<YamlListState<Resource>>(props) {
    init {
        initYamlListState()
        Request.GetYaml(YamlRef.ResourcesYaml).send(Resource.serializer().list, ::updateYamlListState)
    }

    override fun StyledDOMBuilder<*>.page() {
            state.yaml.forEach {
                styledDiv {
                    css {
                        display = Display.flex
                        margin(25.px, 30.px, 25.px, 20.px)
                    }
                    a(href = it.link, target = "_blank") {
                        styledImg(src = (ImageDirs.resources file it.logo).path) {
                            css {
                                maxWidth = 80.px
                                maxHeight = 80.px
                            }
                        }
                    }
                    styledDiv {
                        css {
                            display = Display.flex
                            flexDirection = FlexDirection.column
                            justifyContent = JustifyContent.flexStart
                            paddingLeft = 10.px
                        }
                        p {
                            +it.name
                        }
                        styledP {
                            css {
                                marginTop = 5.px
                                color = gray50Color
                                fontSize = 10.pt
                            }
                            +it.text
                        }
                    }
                }
            }
        }
}