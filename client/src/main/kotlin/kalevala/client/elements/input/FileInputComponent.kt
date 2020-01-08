package kalevala.client.elements.input

import kalevala.client.JSFile
import kalevala.client.gray50Color
import kalevala.client.send
import kalevala.common.FileData
import kalevala.common.FileType
import kalevala.common.Request
import kalevala.common.interpretation.ScaleType
import kalevala.common.interpretation.put
import kalevala.common.interpretation.x
import kalevala.common.randomString
import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.deg
import kotlinx.css.properties.rotate
import kotlinx.css.properties.transform
import kotlinx.html.DIV
import kotlinx.html.InputType
import kotlinx.html.LABEL
import kotlinx.html.id
import kotlinx.html.js.*
import kotlinx.serialization.serializer
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.files.get
import react.setState
import styled.StyledDOMBuilder
import styled.css
import styled.styledDiv
import styled.styledInput

interface FileInputState : InputItemState {
    var moused: Boolean
    var miniFile: String
}

class FileInputComponent : InputComponent<FileInputState>() {
    init {
        state.moused = false
        state.miniFile = ""
    }

    private fun mouseIn(isMouseIn: Boolean): (Event) -> Unit = { _ -> setState { moused = isMouseIn } }

    override fun StyledDOMBuilder<DIV>.containerBody() {}
    override fun StyledDOMBuilder<DIV>.inputBody() {
        styledInput(type = InputType.file, name = props.name) {
            attrs {
                accept = when {
                    props.name.startsWith("imageFileId") -> "image/*"
                    props.name.startsWith("mediaFileId") -> "image/*,video/*,audio/*"
                    else -> "text/plain,application/pdf," +
                        "application/msword," +
                        "application/vnd.ms-powerpoint," +
                        "application/vnd.openxmlformats-officedocument.presentationml.presentation," +
                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
                }
                autoComplete = false
                id = "input-" + props.name
                onChangeFunction = {
                    commonOnChange(it)
                    (it.target as HTMLInputElement).files?.get(0)?.let { file ->
                        val jsFile = JSFile(props.name, file, randomString(10))
                        val fileType = if (props.name.startsWith("imageFileId")) FileType.Image else FileType.Other
                        val fileData = FileData(fileType, file.name, props.time)
                        Request.FileUpload(props.formType, fileData).send(Int.serializer(), listOf(jsFile)) { index ->

                            val fileName = index.toString()
                            props.valueUpdate(props.name, fileName)
                            setState {
                                value = fileName
                                isEmpty = fileName.isEmpty()
                                isCorrect = props.validation(value)
                                isIncorrect = !isCorrect && wasCorrect
                                moused = false
                            }
                            if (state.miniFile == "") {
                                Request.ParticipantsImagesGetVersion(index, 120 x 80 put ScaleType.OUTSIDE).send(String.serializer()) {
                                    setState {
                                        miniFile = it
                                    }
                                }
                            }
                        }
                    }
                }

                onBlurFunction = {
                    setState {
                        isIncorrect = !isCorrect
                    }
                }
            }
            css {
                display = Display.none
            }
        }
    }

    override fun StyledDOMBuilder<LABEL>.labelBody() {
        val type = state.isEmpty
        css {
            display = Display.flex
            alignItems = Align.center
            textAlign = TextAlign.center
            fontSize = 12.pt
            if (type) cursor = Cursor.pointer
            border(1.px, BorderStyle.solid, gray50Color)
            height = if (type) 50.px else 80.px
        }
        if (type) {
            attrs.onMouseOverFunction = mouseIn(true)
            attrs.onMouseOutFunction = mouseIn(false)
        }
        styledDiv {
            css {
                if (!type) {
                    attrs.onMouseOverFunction = mouseIn(true)
                    attrs.onMouseOutFunction = mouseIn(false)
                }
                cursor = Cursor.pointer
                width = 30.px
                height = 30.px
                margin(10.px, 15.px)
                display = Display.flex
                justifyContent = JustifyContent.center
                alignItems = Align.center
                backgroundRepeat = BackgroundRepeat.noRepeat
                backgroundSize = "60%"
                backgroundPosition = "center center"
                backgroundImage = Image("url('/images/design/exit.png')")
                opacity = if (state.moused) 1 else 0.4
                transform { if (type) rotate(45.deg) }
            }
            attrs.onClickFunction = {
                if (!type) {
                    it.preventDefault()
                    setState {
                        isEmpty = true
                        miniFile = ""
                    }
                    props.valueUpdate(props.name, "")
                    setState { value = "" }
                }
            }
        }
        styledDiv {
            css {
                fontSize = 10.pt
            }
            if (props.name.startsWith("imageFileId") && props.validation(state.value) && state.miniFile.isNotEmpty()) {
                css {
                    height = 100.pct
                    backgroundImage = Image("url('${state.miniFile}')")
                    backgroundSize = "cover"
                    backgroundPosition = "center center"
                    flexGrow = 1.0
                }
            } else {
                css {
                    if (!state.isEmpty && state.isCorrect) {
                        borderColor = Color.limeGreen
                        backgroundColor = rgb(212, 235, 193)
                        color = Color.darkSeaGreen
                    }
                    if (state.isIncorrect || (props.forceChecked && !state.isCorrect)) {
                        borderColor = Color.darkRed
                        backgroundColor = Color.pink
                        color = Color.darkRed
                    }
                }
                if (state.isEmpty) {
                    +props.title
                } else {
                    +"Файл загружен"
                }
            }
        }

    }
}
