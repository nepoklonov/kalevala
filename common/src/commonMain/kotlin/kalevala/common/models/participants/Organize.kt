package kalevala.common.models.participants

import kalevala.common.DisplayType
import kalevala.common.Display
import kalevala.common.SaveToTable
import kalevala.common.Validation
import kotlinx.serialization.Serializable

@Serializable
data class OrganizeParticipant (
    @SaveToTable
    @Display(3,"Название мероприятия")
    val actionName: String,

    @SaveToTable(longText = true)
    @Display(4,"Краткое описание мероприятия", validation = Validation.LongText)
    val actionDescription: String,

    @SaveToTable
    @Display(5,"Страна", width = 15)
    val actionCountry: String,

    @SaveToTable
    @Display(6,"Населённый пункт", width = 25)
    val actionCity: String,

    @SaveToTable
    @Display(7,"Адрес", width = 30)
    val actionAddress: String,

    @SaveToTable
    @Display(8,"Дата", width = 10)
    val actionDate: String,

    @SaveToTable
    @Display(9,"Время", width = 10)
    val actionTime: String,

    @SaveToTable
    @Display(10,"Ссылка на мероприятие (по желанию)", validation = Validation.Any)
    val link: String,

    @Display(11, "Куратор мероприятия:",
        displayType = DisplayType.Label, validation = Validation.Any)
    val supervisor: Unit,

    @SaveToTable
    @Display(112,"Отчество", width = 30)
    var patronymic: String,

    @Display(214, "Изображение, отражающее тематику мероприятия (для размещения на сайте и в социальных сетях):",
        displayType = DisplayType.Label, validation = Validation.Any)
    val fileLabel: Unit,

    @SaveToTable
    @Display(215,"Приложить изображение",
        displayType = DisplayType.File, validation = Validation.EssayFileName,
        width = 45)
    val imageFileId: Int,

    override val id: Int, override val time: String, override val agree: Unit, override val submit: Unit, override val surname: String, override var name: String, override val email: String

) : Participant, OnlyName