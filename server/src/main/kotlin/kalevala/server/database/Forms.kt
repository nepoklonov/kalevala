package kalevala.server.database

import kalevala.common.interpretation.ScaleType
import kalevala.common.interpretation.put
import kalevala.common.interpretation.x
import kalevala.common.models.News
import kalevala.common.models.NewsWithSrc
import kalevala.common.models.Review
import org.jetbrains.exposed.sql.SortOrder

fun getAllNews(width: Int, height: Int): List<NewsWithSrc> {
    return loggedTransaction {
        News::class.getModelTable().let { table ->
            table.selectAllModelsOrderBy(table[News::date], SortOrder.DESC).map { news ->
                val src = if (news.imageFileId != -1 && news.imageFileId != Int.MIN_VALUE) {
                    getImageVersion(news.imageFileId, width x height put ScaleType.OUTSIDE)
                } else ""
                NewsWithSrc(news, src)
            }
        }
    }
}


fun getAllReview(): List<Review> {
    return loggedTransaction {
        Review::class.getModelTable().selectAllModels()
    }
}