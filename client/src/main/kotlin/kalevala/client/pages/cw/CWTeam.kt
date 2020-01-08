package kalevala.client.pages.cw

import kalevala.client.send
import kalevala.client.stucture.YamlState
import kalevala.client.stucture.updateYamlState
import kalevala.common.CWTeamKlass
import kalevala.common.Request
import kalevala.common.interpretation.ImageDirs
import kalevala.common.interpretation.YamlRef
import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.dom.h3
import react.dom.p
import styled.css
import styled.styledDiv
import styled.styledImg
import styled.styledP


class CWTeam : RComponent<RProps, YamlState<CWTeamKlass>>() {

    init {
        Request.GetYaml(YamlRef.CWTeam).send(CWTeamKlass.serializer(), ::updateYamlState)
    }

    override fun RBuilder.render() {
        if (state.yaml != undefined) {
            for (i in state.yaml.positions.indices) {
                styledDiv {
                    css {
                        margin(10.px, 30.px)
                    }
                    h3 {
                        +state.yaml.positions[i]
                    }
                    state.yaml.team.forEach {
                        if (it.position == i + 1) {
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
            h3 { +"Актив организации" }
            state.yaml.active.forEach {
                p {
                    +it
                }
            }
        }
    }
}
