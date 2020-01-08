package kalevala.common.interpretation

import kalevala.common.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.list
import kotlinx.serialization.serializer


enum class YamlRef(private val yamlName: String, val serializer: KSerializer<out Any>) {
    ResourcesYaml("resources", Resource.serializer().list),
    SymbolsYaml("symbols", Symbol.serializer().list),
    TeamYaml("team", Team.serializer()),
    ContactsYaml("contacts", Contact.serializer().list),
    PartnersYaml("partners", Partners.serializer()),
    GreetingsYaml("greetings", String.serializer().list),
    KareliaSymbolsYaml("karelia/symbols", KareliaSymbol.serializer().list),
    KareliaHistoryYaml("karelia/history", KareliaHistory.serializer().list),
    KareliaTourismYaml("karelia/tourism", KareliaTourism.serializer().list),
    KareliaLiteratureYaml("karelia/literature", KareliaLiterature.serializer().list),
    KareliaCultureYaml("karelia/culture", KareliaCulture.serializer().list),
    KareliaInternetSourcesYaml("karelia/internet-sources", KareliaInternetSources.serializer().list),
    CWTeam("cw/team", CWTeamKlass.serializer()),
    CWSymbols("cw/symbols", Symbol.serializer().list);

    fun getFileRefByName() = DirRef.yaml file yamlName dot "yaml"
}
