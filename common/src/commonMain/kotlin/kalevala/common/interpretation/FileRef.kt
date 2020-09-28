package kalevala.common.interpretation

import kalevala.common.div
import kalevala.common.interpretation.DirRef.Companion.documents
import kalevala.common.interpretation.DirRef.Companion.images
import kalevala.common.interpretation.DirRef.Companion.root
import kalevala.common.interpretation.ScaleType.INSIDE
import kalevala.common.interpretation.ScaleType.OUTSIDE
import kalevala.common.models.participants.FormType
import kotlinx.serialization.Serializable

@Serializable
data class DirRef constructor(val parent: DirRef? = null, val name: String) {
    val path: String
        get() = parent?.let { it.path / name } ?: "/$name"
    val tree: List<String>
        get() = if (parent == null) listOf(name) else parent.tree + name

    companion object {
        val root = DirRef(null, "")
        val yaml = root / "yaml"
        val images = root / "images"
        val documents = root / "documents"
        operator fun invoke(refString: String): DirRef {
            var dirRef = root
            refString.split('/').forEach { if (it != "") dirRef /= it }
            return dirRef
        }
    }

    operator fun div(other: String) = DirRef(this.takeIf { it != root }, other)
    infix fun file(other: String) = FileRef(this, other)
}

infix fun DirRef.from(other: DirRef): String {
    var i = 0
    val oneTree = this.tree
    val otherTree = other.tree
    while (i < oneTree.size && i < otherTree.size && oneTree[i] == otherTree[i]) i++
    var result: String = ""
    for (j in i until otherTree.size) {
        result += "../"
    }
    for (j in i until oneTree.size) {
        result += oneTree[j]
    }
    return result.trim('/')
}

@Serializable
data class FileRef(val dir: DirRef, val fileName: String) {
    val path: String
        get() = dir.path / fileName
    companion object {
        operator fun invoke(refString: String): FileRef {
            var dirRef = root
            val strTree = refString.split('/').filter { it != "" }
            strTree.forEachIndexed { index, s ->
                if (index != strTree.size - 1) dirRef /= s
            }
            return dirRef file strTree.last()
        }
    }
}

infix fun FileRef.dot(ext: String) = copy(fileName = "$fileName.$ext")

object Documents {

    fun getOfficialPDF(current: String): String {
        return (documents file (DirRef(current) from Pages.join) dot "pdf").path
    }
    fun getOfficialPDF(formType: FormType): String {
        return (documents file formType.folder.name dot "pdf").path
    }

    val ustav = documents file "ustav.pdf"
    val conceptionPDF = documents file "conception.pdf"

    val cwForm = documents file "cw-form.pdf"
    val cwApplication = documents file "cw-application.jpg"
}


object ImageDirs {
    val design = images / "design"
    val contacts = images / "contacts"
    val partners = images / "partners"
    val resources = images / "resources"
    val greetings = images / "greetings"
    val smi = images / "smi"
    val symbols = images / "symbols"
    val team = images / "team"
    val history = images / "history"
    val old = images / "old"
    val karelia = images / "karelia"
    val tourism = images / "tourism"
    val join = images / "join"
    val commonwealth = images / "commonwealth"

    fun getPoster(current: String): String? {
        return (join file (DirRef(current) from Pages.join) dot "jpg").path//if (current == Pages.Join.organize.path) null
        /*else*/
    }

    fun getImageFromDirectory(dir: DirRef, fileRef: FileRef): Image<*> {
        require(fileRef.dir.path.startsWith(dir.path)) { "$fileRef is not in $dir" }
        return when(dir) {
            old -> Image(fileRef, getDirectoryScales(dir))
            else -> TODO()
        }
    }

    fun getDirectoryScales(dir: DirRef) = when (dir) {
        old -> 250 x 125 put OUTSIDE
        else -> TODO()
    }
}

//enum class Category(title: String) {
//    Images("images"),
//    Sections("sections"),
//    Old("old"),
//}

object Images {
    val mainBackground: Image<SingleScale> =
        Image(ImageDirs.design file "back.png", scaleOutsideByHeight(918))
    val bear: Image<SingleScale> =
        Image(ImageDirs.design file "bear.png", 30 x 40 put INSIDE)
    val oldKarel: Image<SingleScale> =
        Image(ImageDirs.design file "old-karel.png", 70 x 120 put INSIDE)
}

object SVGImages {
    val mainLogo = SVGImage(ImageDirs.design file "main-logo.svg")
    val mainTitle = SVGImage(ImageDirs.design file "title.svg")
}

object Pages {
    val main = root
    val commonwealth = root / "commonwealth"
    val about = root / "about"

    object About {
        val conception = about / "conception"
        val news = about / "news"
        val partners = about / "partners"
        val team = about / "team"
        val history = about / "history"
        val photos = about / "photos"
        val videos = about / "videos"
        val greetings = about / "greetings"
        val symbols = about / "symbols"
    }

    val join = root / "join"

    object Join {
        val ethnoTour = join / "ethno-tour"
        val faces = join / "faces"
        val sunCountry = join / "sun-country"
        val ethnoMotive = join / "ethno-motive"
        val scientific = join / "scientific"
        val organize = join / "organize"
    }

    val gallery = root / "gallery"

    object Gallery {
        val ethnoTour = gallery / "ethno-tour"
        val faces = gallery / "faces"
        val sunCountry = gallery / "sun-country"
        val ethnoMotive = gallery / "ethno-motive"
//        val scientific = gallery / "scientific"
//        val organize = gallery / "organize"
    }

    val karelia = root / "karelia"

    object Karelia {
        val common = karelia / "common"
        val symbols = karelia / "symbols"
        val history = karelia / "history"
        val tourism = karelia / "tourism"
        val literature = karelia / "literature"
        val culturalInstitutions = karelia / "cultural-institutions"
        val onlineResources = karelia / "online-resources"
    }

    val kalevala = root / "kalevala"

    val uploads = root / "uploads"

    object Uploads {
        val temp = uploads / "temp"
        val images = uploads / "images"

        object Images {
            val cw = images / "cw"
            val faces = images / "faces"
            val sunCountry = images / "sun-country"
            val ethnoMotive = images / "ethno-motive"
            val news = images / "news"
        }

        val other = uploads / "other"

        object Other {
            val ethnoTour = other / "ethno-tour"
            val scientific = other / "scientific"
            val organize = other / "organize"
            val articles = other / "articles"
        }
    }

    val admin = root / "admin"

    val sections = listOf(about, join, gallery, karelia, kalevala, commonwealth)
}