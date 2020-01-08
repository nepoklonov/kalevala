package kalevala.server

import kalevala.common.interpretation.DirRef
import kalevala.common.interpretation.FileRef
import java.io.File

fun File.pave() = this.also { d -> if (!d.exists()) d.mkdirs() }

val DirRef.file
    get() = File(path.trim('/'))

val FileRef.file
    get() = File(path.trim('/'))

val DirRef.liveFile
    get() = file.pave()

val FileRef.liveFile
    get() = file.also { it.parentFile.pave() }
