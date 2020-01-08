package kalevala.common.models

import kalevala.common.Display
import kalevala.common.DisplayType
import kalevala.common.SaveToTable
import kalevala.common.Validation
import kalevala.common.models.participants.AnyForm
import kotlinx.serialization.Serializable


@Serializable
data class CWJoin(
    @SaveToTable
    @Display(103, "Фамилия", validation = Validation.Text)
    val lastName: String,

    @SaveToTable
    @Display(104, "Имя", validation = Validation.Text)
    val firstName: String,

    @SaveToTable
    @Display(105, "Возраст", validation = Validation.Number)
    val age: Int,

    @SaveToTable
    @Display(106, "Населённый пункт", validation = Validation.Text)
    val city: String,

    @SaveToTable
    @Display(107, "E-mail", validation = Validation.Email)
    val email: String,

    @SaveToTable
    @Display(108, "Приложить заполненное заявление", validation = Validation.EssayFileName, displayType = DisplayType.File)
    val file1Id: Int,

    @SaveToTable
    @Display(109, "Приложить заполненную анкету", validation = Validation.EssayFileName, displayType = DisplayType.File)
    val file2Id: Int,

    override val id: Int, override val time: String, override val submit: Unit
): AnyForm