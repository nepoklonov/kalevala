package kalevala.client.pages

import kalevala.client.send
import kalevala.client.stucture.PageComponent
import kalevala.client.stucture.PageProps
import kalevala.client.stucture.YamlState
import kalevala.client.stucture.updateYamlState
import kalevala.common.Partner
import kalevala.common.Partners
import kalevala.common.Request
import kalevala.common.interpretation.ImageDirs
import kalevala.common.interpretation.YamlRef
import kotlinx.css.*
import react.RBuilder
import react.dom.a
import react.dom.h2
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledImg

fun RBuilder.logo(partner: Partner, logoHeight: LinearDimension, topMargin: LinearDimension) {
    a(href = partner.link, target = "_blank") {
        styledImg(src = (ImageDirs.partners file partner.logo).path) {
            css {
                height = logoHeight
                margin(topMargin, 0.px, 0.px, 20.px)
            }
        }
    }
}

fun RBuilder.logoWithHeight(logoHeight: LinearDimension, topMargin: LinearDimension) =
    { partner: Partner -> logo(partner, logoHeight, topMargin) }

fun RBuilder.logos(title: String?, partners: List<Partner>, logosHeight: LinearDimension, topMargin: LinearDimension) {
    if (title != null) h2 {
        +title
    }
    styledDiv {
        css {
            margin(topMargin, 0.px)
            display = Display.flex
            flexWrap = FlexWrap.wrap
        }
        partners.forEach(logoWithHeight(logosHeight, topMargin))
    }
}

class PartnersComponent(props: PageProps) : PageComponent<YamlState<Partners>>(props) {
    init {
        Request.GetYaml(YamlRef.PartnersYaml).send(Partners.serializer(), ::updateYamlState)
    }

    override fun StyledDOMBuilder<*>.page() {
        if (state.yaml != undefined) {
            logos("Организаторы", state.yaml.orgs, 80.px, 20.px)
            logos("Партнёры", state.yaml.partners, 80.px, 20.px)
        }
    }
}