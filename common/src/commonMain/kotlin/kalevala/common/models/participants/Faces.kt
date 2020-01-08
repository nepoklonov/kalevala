package kalevala.common.models.participants

import kalevala.common.DisplayType
import kalevala.common.Display
import kalevala.common.SaveToTable
import kalevala.common.Validation
import kotlinx.serialization.Serializable

@Serializable
data class FacesParticipant(
    @SaveToTable
    @Display(106,"Отчество", width = 30)
    var patronymic: String,

    @SaveToTable
    @Display(210,"Название фотоработы", width = 45)
    val title: String,

    @SaveToTable
    @Display(211,"Загрузите работу",
        displayType = DisplayType.File, validation = Validation.EssayFileName,
        width = 45)
    val imageFileId: Int,

    override val id: Int, override val time: String, override val agree: Unit, override val know: Unit, override val submit: Unit, override val surname: String, override var name: String, override val email: String, override val city: String
) : Participant, NotOrganizer, OnlyName