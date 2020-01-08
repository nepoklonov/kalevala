package kalevala.common.models.participants

import kalevala.common.*
import kalevala.common.DisplayType.*
import kalevala.common.Validation.*
import kalevala.common.interpretation.DirRef
import kalevala.common.interpretation.Pages
import kalevala.common.models.AdminLogin
import kalevala.common.models.CWJoin
import kalevala.common.models.News
import kalevala.common.models.Review
import kotlinx.serialization.KSerializer
import kotlin.reflect.KClass


enum class FormType(
    val klass: KClass<out AnyForm>,
    val serializer: KSerializer<out AnyForm>,
    val folder: DirRef
) {
    EthnoTour(EthnoTourParticipant::class, EthnoTourParticipant.serializer(), Pages.Uploads.Other.ethnoTour),
    Faces(FacesParticipant::class, FacesParticipant.serializer(), Pages.Uploads.Images.faces),
    SunCountry(SunCountryParticipant::class, SunCountryParticipant.serializer(), Pages.Uploads.Images.sunCountry),
    EthnoMotive(EthnoMotiveParticipant::class, EthnoMotiveParticipant.serializer(), Pages.Uploads.Images.ethnoMotive),
    Scientific(ScientificParticipant::class, ScientificParticipant.serializer(), Pages.Uploads.Other.scientific),
    Organize(OrganizeParticipant::class, OrganizeParticipant.serializer(), Pages.Uploads.Other.organize),
    Article(ArticleParticipant::class, ArticleParticipant.serializer(), Pages.Uploads.Other.articles),
    ReviewForm(Review::class, Review.serializer(), DirRef.root),
    CWJoinForm(CWJoin::class, CWJoin.serializer(), Pages.Uploads.Images.cw),
    NewsForm(News::class, News.serializer(), Pages.Uploads.Images.news),
    Admin(AdminLogin::class, AdminLogin.serializer(), DirRef.root);

    val getSerializer: KSerializer<out AnyForm>
        get() = klass.getSerializer()

    val haveAnImage: Boolean
        get() = when (this) {
            Faces, SunCountry, EthnoMotive -> true
            else -> false
        }
}

interface AnyForm {
    @SaveToTable(autoIncremented = true, isPrimaryKey = true)
    val id: Int

    @SaveToTable(forceValidation = Validation.Text)
    val time: String

    @Display(50003, "", displayType = Submit, validation = Any, width = 40)
    val submit: Unit
}

interface Participant : AnyForm {
    @SaveToTable
    @Display(200, "E-mail", validation = Email, width = 45)
    val email: String

    @Display(10001, "$1", displayType = CheckBox, validation = FilledCheckBox)
    val agree: Unit
}

interface OnlyName {
    @SaveToTable
    @Display(101, "Фамилия", width = 30)
    val surname: String

    @SaveToTable
    @Display(102, "Имя", width = 30)
    var name: String
}

interface NameOrGroup {
    @SaveToTable
    @Display(100, "Коллективная работа", displayType = DisplayType.Radio, validation = Validation.Radio)
    val hasGroup: HowMuch

    @SaveToTable
    @Display(101, "Название коллектива/список участников через запятую")
    @Belongs(OwnType.BySelect, ["hasGroup", "1"])
    val group: String

    @SaveToTable
    @Display(101, "Фамилия", width = 30)
    @Belongs(OwnType.BySelect, ["hasGroup", "0"])
    val surname: String

    @SaveToTable
    @Display(102, "Имя", width = 30)
    @Belongs(OwnType.BySelect, ["hasGroup", "0"])
    var name: String
}

interface NotOrganizer {
    @Display(10002, "$2", displayType = CheckBox, validation = FilledCheckBox)
    val know: Unit

    @SaveToTable
    @Display(150, "Населённый пункт", width = 45)
    val city: String
}

enum class HowMuch(override val title: String) : Titled {
    Single("Один участник"),
    Plural("Коллектив")
}