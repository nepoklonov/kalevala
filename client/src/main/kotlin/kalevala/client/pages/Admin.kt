package kalevala.client.pages

import kalevala.client.elements.image.imageInDiv
import kalevala.client.gray70Color
import kalevala.client.pages.join.Contest
import kalevala.client.parseAnswer
import kalevala.client.parseAnswerBody
import kalevala.client.send
import kalevala.client.stucture.PageProps
import kalevala.client.stucture.PageState
import kalevala.client.stucture.StandardPageComponent
import kalevala.common.AnswerType
import kalevala.common.FileAnswer
import kalevala.common.Request
import kalevala.common.interpretation.ScaleType.OUTSIDE
import kalevala.common.interpretation.put
import kalevala.common.interpretation.x
import kalevala.common.models.AdminLogin
import kalevala.common.models.InputField
import kalevala.common.models.participants.FormType
import kalevala.common.quote
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.html.AreaTarget
import kotlinx.html.js.onClickFunction
import kotlinx.serialization.list
import react.RBuilder
import react.RClass
import react.RProps
import react.dom.a
import react.dom.b
import react.dom.br
import react.setState
import styled.*

interface AdminState : PageState {
    var isAdmin: Boolean
    var section: Int
    var participants: List<List<InputField>>?
    var files: MutableMap<Int, FileAnswer>
}

@Suppress("UNCHECKED_CAST")
class AdminComponent(pageProps: PageProps) : StandardPageComponent<AdminState>(pageProps) {
    init {
        state.section = 0
        state.files = mutableMapOf()
    }

    var sectionsAmount = 0

    private inline fun RBuilder.section(title: String, isSelected: RBuilder.() -> Unit) {
        val id = sectionsAmount + 1
        styledDiv {
            styledSpan {
                css {
                    width = 100.pct
                    fontSize = 18.px
                }
                styledA(href = "#") {
                    attrs.onClickFunction = {
                        it.preventDefault()
                        setState {
                            participants = undefined
                            section = if (section == 0) id else 0
                        }
                    }
                    +title
                }
            }
            if (state.section == id) {
                isSelected()
            }
        }
        sectionsAmount++
    }


    private fun RBuilder.forms(title: String, formType: FormType) {
        section("Все анкеты в разделе ${title.quote()}:") {
            val participants = state.participants
            if (participants != undefined) {
                participants.forEach { p ->
                    styledDiv {
                        css {
                            border(1.px, BorderStyle.solid, gray70Color)
                            padding(5.px)
                            display = Display.flex
                        }
                        if (state.files != undefined) {
                            p.find { it.name == "imageFileId" }?.let {
                                imageInDiv(state.files[it.value.toInt()]?.src ?: "", "contain", 300.px, 200.px) {
                                    css.minWidth = 300.px
                                    css.marginRight = 20.px
                                }
                            }
                        }
                        styledDiv {
                            css {
                                child("b") {
                                    fontWeight = FontWeight.bold
                                }
                            }
                            p.forEach { field ->
                                if (field.name !in listOf("agree", "know", "submit", "imageFileId")) {
                                    b {
                                        +field.title.let { if (it != "") it else field.name }
                                    }
                                    if (field.value != Unit.toString()) {
                                        b { +": " }
                                        +field.value
                                    }
                                    br { }
                                }
                            }
                            p.find { it.name == "fileId" || it.name == "mediaFileId" }?.also { field ->
                                if (state.files.containsKey(field.value.toInt())) {
                                    b { +"Ссылка на файл: " }
                                    state.files.getValue(field.value.toInt()).src.let {
                                        a(href = it, target = AreaTarget.blank) {
                                            +it
                                        }
                                    }
                                } else {
                                    GlobalScope.launch {
                                        val file = Request.AdminGetFileById(field.value.toInt()).send().parseAnswerBody(FileAnswer.serializer())
                                        setState { files[field.value.toInt()] = file }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                GlobalScope.launch {
                    val p = Request.AdminParticipantGetAll(formType)
                        .send().parseAnswerBody(InputField.serializer().list.list)
                    setState { this.participants = p }
                    if (formType == FormType.SunCountry || formType == FormType.Faces || formType == FormType.EthnoMotive) {
                        val images = Request.ParticipantsImagesGetAll(formType, 300 x 200 put OUTSIDE).send().parseAnswerBody(FileAnswer.serializer().list)
                        setState { this.files = images.associateBy { it.id }.toMutableMap() }
                    }
                }
            }
        }
    }

    override fun StyledDOMBuilder<*>.page() {
        if (state.isAdmin != undefined) {
            if (state.isAdmin) {
                sectionsAmount = 0
                section("Добавить новость:") {
                    styledDiv {
                        css {
                            backgroundColor = Color.white
                        }
                        child(Contest::class) {
                            attrs {
                                formType = FormType.NewsForm
                            }
                        }
                    }
                }

                forms("ЭтноТур", FormType.EthnoTour)
                forms("Лики земли Карельской", FormType.Faces)
                forms("Калевала — страна солнца", FormType.SunCountry)
                forms("ЭтноМотив", FormType.EthnoMotive)
                forms("Научно-деловая программа", FormType.Scientific)
                forms("Научно-деловая программа: Статьи", FormType.Article)
                forms("Организовать площадку этнофестиваля", FormType.Organize)
            } else {
                child(Contest::class) {
                    attrs.formType = FormType.Admin
                    attrs.action = {
                        setState {
                            isAdmin = it.answerType == AnswerType.OK
                        }
                    }
                }
            }
        } else {
            GlobalScope.launch {
                val r = Request.AdminCheck().send().parseAnswer().answerType == AnswerType.OK
                setState {
                    isAdmin = r
                }
            }
        }
    }
}