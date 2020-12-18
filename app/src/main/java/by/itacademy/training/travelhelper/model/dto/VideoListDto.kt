package by.itacademy.training.travelhelper.model.dto

data class VideoListDto(
    val etag: String,
    val items: List<ItemDto>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfoDto,
    val regionCode: String
)

data class ItemDto(
    val etag: String,
    val id: IdDto,
    val kind: String,
    val snippet: SnippetDto
)

data class IdDto(
    val kind: String,
    val videoId: String
)

data class PageInfoDto(
    val resultsPerPage: Int,
    val totalResults: Int
)

data class SnippetDto(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val liveBroadcastContent: String,
    val publishTime: String,
    val publishedAt: String,
    val thumbnails: ThumbnailsDto,
    val title: String
)

data class ThumbnailsDto(
    val default: DefaultDto,
    val high: HighDto,
    val medium: MediumDto
)

data class DefaultDto(
    val height: Int,
    val url: String,
    val width: Int
)

data class HighDto(
    val height: Int,
    val url: String,
    val width: Int
)

data class MediumDto(
    val height: Int,
    val url: String,
    val width: Int
)

