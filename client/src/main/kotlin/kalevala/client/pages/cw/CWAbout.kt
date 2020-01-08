package kalevala.client.pages.cw

import kalevala.common.interpretation.Documents
import kalevala.common.interpretation.ImageDirs
import kalevala.common.interpretation.dot
import kotlinx.css.*
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import react.dom.p
import styled.css
import styled.styledDiv
import styled.styledImg


class CWAbout : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                display = Display.flex
                justifyContent = JustifyContent.center
                child("p") {
                    margin(20.px)
                }
            }
            listOf("first", "second", "third", "fourth").forEach {
                styledImg(src = (ImageDirs.commonwealth file it dot "png").path) {
                    css {
                        margin(20.px)
                        height = 80.px
                    }
                }
            }
        }
        p {
            +"МОО «Карельское Содружество» является добровольным самоуправляемым общественным объединением, созданным на основе свободного волеизъявления физических лиц, объединившихся на основе общности интересов и убеждений по развитию культурных отношений, указанных в Уставе и действует на территории Москвы, Московской области, Твери, Санкт-Петербурга, Ленинградской области и Республики Карелия."
        }
        p {
            +"По своей организационно-правовой форме Организация является общественной организацией и действует в соответствии с Федеральным законом «Об общественным объединениях», и иными правовыми актами законодательства Российской Федерации и на основании настоящего Устава и руководствуется в своей деятельности общепризнанными международными принципами. Организация является юридическим лицом."
        }
        a(href = Documents.ustav.path) {
            +"Скачать устав"
        }
    }
}
