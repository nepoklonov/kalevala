package kalevala.common

import kotlinx.serialization.Serializable

@Serializable
data class Resource(val logo: String, val link: String, val name: String, val text: String)

@Serializable
data class TeamPerson(
    val position: Int,
    val fullName: String,
    val picture: String,
    val description: String,
    val email: String,
    val phone: String?,
    val vk: String?,
    val fb: String?,
    val insta: String?
)

@Serializable
data class Team(val positions: List<String>, val team: List<TeamPerson>)

@Serializable
data class Contact(val name: String, val logo: String, val link: String, val text: String)

@Serializable
data class Partner(val name: String, val logo: String, val link: String)

@Serializable
data class Partners(val partners: List<Partner>, val orgs: List<Partner>)

@Serializable
data class Symbol(val photo: String, val text: String)

@Serializable
data class KareliaSymbol(
    val header: String,
    val picture: String,
    val text: String,
    val song: String,
    val file: String,
    val source: String
)

@Serializable
data class KareliaTourism(val link: String, val name: String, val photo: String)

@Serializable
data class KareliaHistory(val header: String, val text: String, val source: String)

@Serializable
data class KareliaLiterature(val header: String, val texts: List<String>)

@Serializable
class KareliaCulture(val link: String, val name: String)

typealias KareliaInternetSources = KareliaCulture

@Serializable
data class CWTeamPerson(
    val position: Int,
    val fullName: String,
    val picture: String,
    val description: String,
    val email: String,
    val phone: String
)

@Serializable
class CWTeamKlass (val positions: List<String>, val team: List<CWTeamPerson>, val active: List<String>)