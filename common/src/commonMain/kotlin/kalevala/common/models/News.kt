package kalevala.common.models

import kalevala.common.Display
import kalevala.common.DisplayType
import kalevala.common.SaveToTable
import kalevala.common.Validation
import kalevala.common.models.participants.AnyForm
import kotlinx.serialization.Serializable

@Serializable
data class News(
    @SaveToTable
    @Display(103, "Заголовок")
    val header: String,

    @SaveToTable(longText = true)
    @Display(104, "Текст новости", displayType = DisplayType.HTML)
    val content: String,

    @SaveToTable
    @Display(212, "Картинка (по желанию)",
        displayType = DisplayType.File, validation = Validation.Any,
        width = 45)
    val imageFileId: Int,

    @SaveToTable
    @Display(213, "Дата (в формате DD.MM.YYYY)", validation = Validation.Date, width = 45)
    val date: String,

    override val id: Int, override val time: String, override val submit: Unit
) : AnyForm

@Serializable
data class NewsWithSrc(
    val news: News,
    val src: String
)