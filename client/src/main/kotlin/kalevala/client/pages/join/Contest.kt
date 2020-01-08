package kalevala.client.pages.join

import kalevala.client.*
import kalevala.client.elements.input.*
import kalevala.common.Answer
import kalevala.common.DisplayType
import kalevala.common.Request
import kalevala.common.models.InputField
import kalevala.common.models.participants.FormType
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.css.*
import kotlinx.html.FormMethod
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onSubmitFunction
import kotlinx.serialization.list
import kotlinx.serialization.serializer
import org.w3c.dom.events.Event
import react.*
import react.dom.RDOMBuilder
import react.dom.a
import react.dom.br
import styled.css
import styled.styledDiv
import styled.styledForm
import styled.styledP
import kotlin.browser.window

fun getInputClass(displayType: DisplayType) = when (displayType) {
    DisplayType.Text -> TextInputComponent::class
    DisplayType.HTML -> HTMLInputComponent::class
    DisplayType.File -> FileInputComponent::class
    DisplayType.CheckBox -> CheckBoxInputComponent::class
    DisplayType.Submit -> SubmitInputComponent::class
    DisplayType.Select -> SelectInputComponent::class
    DisplayType.Label -> LabelInputHelpComponent::class
    DisplayType.Radio -> RadioInputComponent::class
    DisplayType.Hidden -> null
}

interface ContestProps : RProps {
    var formType: FormType
    var action: (Answer) -> Unit
}

interface ContestState : RState {
    var formType: FormType
    var allFields: Map<String, InputField>
    var formSent: Boolean
    var enable: Boolean
    var forceChecked: Boolean
}

class Contest(props: ContestProps) : RComponent<ContestProps, ContestState>(props) {

    private val InputField.isExpected: Boolean
        get() = ownType.live(ownParams, state.allFields)
    //    private val InputField.willBeCollected: Boolean
//        get() = isExpected
    private val InputField.isValid: Boolean
        get() = validation.validate(value)
    private val InputField.isValidIfExpected: Boolean
        get() = isValid || !isExpected

    init {
        state.allFields = mapOf()
    }

    private fun initActions() {
        setState {
            forceChecked = false
            allFields["time"]?.value = time
        }
    }

    override fun componentDidMount() {
        if (state.formType != props.formType) {
            Request.GetModel(props.formType).send(InputField.serializer().list) { list ->
                setState {
                    formType = props.formType
                    allFields = list.associateBy { it.name }
                }
                initActions()
            }
        }
    }

    override fun componentDidUpdate(prevProps: ContestProps, prevState: ContestState, snapshot: Any) {
        componentDidMount()
    }

    private var time: String = getISOSTime()

    private val valueChanged: (String, String) -> Unit = { key, value ->
        setState {
            allFields[key]?.value = value
            val e = allFields.all { it.value.isValidIfExpected }
            if (enable != e) {
                enable = e
            }
            allFields.forEach { if (!it.value.isValidIfExpected) console.log(it, it.value) }
        }
    }

    private val sendData: (Event) -> Unit = { e ->
        e.preventDefault()
        if (state.enable) {
            setState { enable = false }
            if (state.allFields.all { it.value.isValidIfExpected }) {
                GlobalScope.launch {
                    val answer = Request.FormSend(props.formType, state.allFields.values.toList()).send().parseAnswer()
                    if (props.action == undefined) {
                        if (answer.parseBody(String.serializer()) == "ok") {
                            setState {
                                allFields.forEach { it.value.value = if (it.value.type == DisplayType.CheckBox) "false" else "" }
                                formSent = true
                                window.scrollTo(0.0, 0.0)
                            }
                        }
                    } else {
                        props.action(answer)
                    }
                }
            } else {
                console.log("error is mistake")
            }
        } else {
            setState { forceChecked = true }
        }
    }


    init {
        state.formSent = false
        state.enable = false
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                padding(30.px)
                minHeight = 490.px
                width = 75.pct
            }
            if (state.formSent) {
                styledP {
                    css {
                        color = gray70Color
                    }
                    when (props.formType) {
                        FormType.Scientific -> {
                            +"Вы успешно зарегистрировались."
                        }
                        FormType.Article -> {
                            +"Вы успешно подали материал в сборник тезисов научно-деловой программы XIV Международного этнофестиваля «Земля Калевалы-2020»"
                        }
                        FormType.EthnoTour, FormType.Faces, FormType.SunCountry, FormType.EthnoMotive -> {
                            +"Спасибо за участие в конкурсе. Ваша работа успешно загружена! "
                            +"В ближайшее время на указанную почту будет отправлен подтверждающий участие сертификат."
                        }
                        FormType.Organize, FormType.CWJoinForm -> {
                            +"Вы успешно подали заявку."
                        }
                        FormType.ReviewForm -> {
                            +"Ваш отзыв успешно отправлен!"
                        }
                        FormType.NewsForm -> {
                            +"Новость добавлена!"
                        }
                        else -> {
                            +"Спасибо за участие в акции, работа успешно загружена! "
                            +"В ближайшее время на указанную почту будет отправлен сертификат."
                        }
                    }
                    br { }
                    if (props.formType != FormType.ReviewForm) a(href = "#") {
                        attrs.onClickFunction = { e ->
                            e.preventDefault()
                            setState {
                                formSent = false
                            }
                            initActions()
                        }
                        +"Загрузить ещё "
                        +when (props.formType) {
                            FormType.Scientific, FormType.Organize, FormType.CWJoinForm -> "заявку"
                            FormType.Article -> "материал"
                            FormType.NewsForm -> "новость"
                            else -> "работу"
                        }
                        +" ->"
                    }
                }
            } else {
                styledForm(action = "", method = FormMethod.post) {
                    styledP {
                        css {
                            color = gray70Color
                            width = 100.pct
                        }

                        when (props.formType) {
                            FormType.EthnoTour, FormType.Faces, FormType.SunCountry, FormType.EthnoMotive -> +"Чтобы принять участие в конкурсе, заполните, пожалуйста, небольшую анкету:"
                            FormType.Scientific -> +"Чтобы принять участие в научно-деловой программе этнофестиваля, заполните, пожалуйста, небольшую анкету:"
                            FormType.Organize -> +"Чтобы стать организатором площадки Этнофестиваля, заполните, пожалуйста, небольшую анкету:"
                            FormType.Article -> +"Чтобы подать статью в сборник, заполните, пожалуйста, небольшую анкету:"
                            FormType.ReviewForm -> +"Оставить отзыв:"
                            FormType.CWJoinForm -> +"Чтобы подать заявку на вступление, заполните, пожалуйста, небольшую форму:"
                            else -> +""
                        }
                    }
                    styledP {
                        css {
                            color = gray50Color
                            width = 100.pct
                        }

                        when (props.formType) {
                            FormType.EthnoTour, FormType.SunCountry, FormType.EthnoMotive ->
                                +"Если номинации не открываются, пожалуйста, воспользуйтесь другим браузером (Например, Google Chrome)"
                            else -> {
                            }
                        }
                    }
                    css {
                        margin(0.px, 30.px, 0.px, 30.px)
                        display = Display.flex
                        flexWrap = FlexWrap.wrap
                        justifyContent = JustifyContent.spaceBetween
                    }
                    attrs.onSubmitFunction = sendData
                    state.allFields.forEach { (_, value) ->
                        if (value.type != DisplayType.Hidden)
                            addInput(value)
                    }
                }
            }
        }
    }

    private fun RDOMBuilder<*>.addInput(inputField: InputField) = inputField.also { it ->
        getInputClass(it.type)?.let { inputClass ->
            child(inputClass) {
                attrs.also { a ->
                    a.formType = props.formType
                    a.type = it.type
                    a.time = time
                    a.name = it.name
                    a.title = it.title
                    a.isExpected = it.isExpected
                    a.validation = it.validation.validate
                    a.valueUpdate = valueChanged
                    a.width = it.width.pct
                    a.enable = state.enable
                    a.options = it.options
                    a.forceChecked = state.forceChecked
                }
            }
        }
    }
}
