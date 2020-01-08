package kalevala.server.api.join

import io.ktor.routing.Route
import kalevala.common.*
import kalevala.common.interpretation.Pages
import kalevala.common.models.ParticipantFile
import kalevala.server.*
import kalevala.server.api.listenAndAutoRespond
import kalevala.server.database.addImageVersions
import kalevala.server.database.addParticipantFile
import kotlinx.serialization.serializer
import java.io.File


fun Route.startFileUploadAPI() = listenAndAutoRespond<Request.FileUpload>(Method.FileUpload) { request, files ->
    if (files.isNotEmpty()) {
        val dir = Pages.Uploads.temp
        try {
            val fileData = request.filesData.first()
            val resultDir = request.formType.folder
            val ext = File(fileData.originalFileName).extension
            val newName = fileData.namePrefix usc randomString(7) dot ext
            val newFileRef = resultDir file newName
            (dir file files[0]).liveFile.renameTo(newFileRef.liveFile)
            val id = addParticipantFile(ParticipantFile(-1, request.formType, newName, fileData.originalFileName))
            if (fileData.isImage) addImageVersions(newFileRef, id)
            Answer.ok(Int.serializer(), id)
        } catch (e: NoSuchElementException) {
            Answer.wrong("Necessary file info is missing")
        }
    } else {
        Answer.wrong("File is missing")
    }
}