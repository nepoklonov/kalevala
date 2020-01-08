package kalevala.client.pages

import kotlinx.css.*
import react.dom.h3
import react.dom.p
import kalevala.common.Request
import kalevala.common.interpretation.YamlRef
import kalevala.client.send
import kalevala.client.stucture.PageProps
import kalevala.client.stucture.StandardPageComponent
import kalevala.client.stucture.YamlState
import kalevala.client.stucture.updateYamlState
import kalevala.common.Team
import kalevala.common.interpretation.ImageDirs
import styled.*

class TeamComponent(props: PageProps) : StandardPageComponent<YamlState<Team>>(props) {
    init {
        state.yaml = Team(emptyList(), emptyList())
        Request.GetYaml(YamlRef.TeamYaml).send(Team.serializer(), ::updateYamlState)
    }

    override fun StyledDOMBuilder<*>.page() {
        css {
            width = 100.pct
            height = 100.pct
        }
        for (i in 0 until state.yaml.positions.size) {
            styledDiv {
                css {
                    margin(10.px, 30.px)
                }
                h3 {
                    +state.yaml.positions[i]
                }
                state.yaml.team.forEach {
                    if (it.position == i) {
                        styledDiv {
                            css {
                                margin(10.px, 0.px)
                                display = Display.flex
                            }
                            styledImg(src = (ImageDirs.team file it.picture).path) {
                                css {
                                    marginRight = 10.px
                                    maxWidth = 120.px
                                    maxHeight = 300.px
                                }
                            }
                            styledDiv {
                                p {
                                    +it.fullName
                                }
                                styledP {
                                    css {
                                        fontSize = 10.pt
                                        color = Color.darkGray
                                    }
                                    p {
                                        +it.description
                                    }
                                    p {
                                        +it.email
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}