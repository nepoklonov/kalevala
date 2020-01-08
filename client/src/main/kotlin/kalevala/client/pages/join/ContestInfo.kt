package kalevala.client.pages.join

import kalevala.client.pages.ContestA
import kalevala.client.pages.getFormType
import kalevala.common.models.participants.FormType.*
import react.RBuilder

fun RBuilder.getContestInfo(aOfficial: ContestA, aJoin: ContestA, current: String) {
    when (getFormType(current)) {
        EthnoTour -> getEthnoTourContestInfo(aOfficial, aJoin)
        Faces -> getFacesContestInfo(aOfficial, aJoin)
        SunCountry -> getSunCountryContestInfo(aOfficial, aJoin)
        EthnoMotive -> getEthnoMotiveContestInfo(aOfficial, aJoin)
        Scientific -> getScientificContestInfo()
        Organize -> getOrganizeContestInfo()
        else -> TODO()
    }
}