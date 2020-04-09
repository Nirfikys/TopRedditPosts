package com.example.topredditposts.remote
import com.google.gson.annotations.SerializedName


data class PageRemoteEntity(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("kind")
    val kind: String
)

data class Data(
    @SerializedName("after")
    val after: String,
    @SerializedName("before")
    val before: Any,
    @SerializedName("children")
    val children: List<Children>,
    @SerializedName("dist")
    val dist: Int,
    @SerializedName("modhash")
    val modhash: String
)

data class Children(
    @SerializedName("data")
    val `data`: DataX,
    @SerializedName("kind")
    val kind: String
)

data class DataX(
    @SerializedName("all_awardings")
    val allAwardings: List<AllAwarding>,
    @SerializedName("allow_live_comments")
    val allowLiveComments: Boolean,
    @SerializedName("approved_at_utc")
    val approvedAtUtc: Any,
    @SerializedName("approved_by")
    val approvedBy: Any,
    @SerializedName("archived")
    val archived: Boolean,
    @SerializedName("author")
    val author: String,
    @SerializedName("author_flair_background_color")
    val authorFlairBackgroundColor: Any,
    @SerializedName("author_flair_css_class")
    val authorFlairCssClass: Any,
    @SerializedName("author_flair_richtext")
    val authorFlairRichtext: List<Any>,
    @SerializedName("author_flair_template_id")
    val authorFlairTemplateId: Any,
    @SerializedName("author_flair_text")
    val authorFlairText: Any,
    @SerializedName("author_flair_text_color")
    val authorFlairTextColor: Any,
    @SerializedName("author_flair_type")
    val authorFlairType: String,
    @SerializedName("author_fullname")
    val authorFullname: String,
    @SerializedName("author_patreon_flair")
    val authorPatreonFlair: Boolean,
    @SerializedName("author_premium")
    val authorPremium: Boolean,
    @SerializedName("awarders")
    val awarders: List<Any>,
    @SerializedName("banned_at_utc")
    val bannedAtUtc: Any,
    @SerializedName("banned_by")
    val bannedBy: Any,
    @SerializedName("can_gild")
    val canGild: Boolean,
    @SerializedName("can_mod_post")
    val canModPost: Boolean,
    @SerializedName("category")
    val category: Any,
    @SerializedName("clicked")
    val clicked: Boolean,
    @SerializedName("content_categories")
    val contentCategories: Any,
    @SerializedName("contest_mode")
    val contestMode: Boolean,
    @SerializedName("created")
    val created: Long,
    @SerializedName("created_utc")
    val createdUtc: Double,
    @SerializedName("discussion_type")
    val discussionType: Any,
    @SerializedName("distinguished")
    val distinguished: Any,
    @SerializedName("domain")
    val domain: String,
    @SerializedName("downs")
    val downs: Int,
    @SerializedName("edited")
    val edited: Boolean,
    @SerializedName("gilded")
    val gilded: Int,
    @SerializedName("gildings")
    val gildings: Gildings,
    @SerializedName("hidden")
    val hidden: Boolean,
    @SerializedName("hide_score")
    val hideScore: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_crosspostable")
    val isCrosspostable: Boolean,
    @SerializedName("is_meta")
    val isMeta: Boolean,
    @SerializedName("is_original_content")
    val isOriginalContent: Boolean,
    @SerializedName("is_reddit_media_domain")
    val isRedditMediaDomain: Boolean,
    @SerializedName("is_robot_indexable")
    val isRobotIndexable: Boolean,
    @SerializedName("is_self")
    val isSelf: Boolean,
    @SerializedName("is_video")
    val isVideo: Boolean,
    @SerializedName("likes")
    val likes: Any,
    @SerializedName("link_flair_background_color")
    val linkFlairBackgroundColor: String,
    @SerializedName("link_flair_css_class")
    val linkFlairCssClass: Any,
    @SerializedName("link_flair_richtext")
    val linkFlairRichtext: List<Any>,
    @SerializedName("link_flair_text")
    val linkFlairText: Any,
    @SerializedName("link_flair_text_color")
    val linkFlairTextColor: String,
    @SerializedName("link_flair_type")
    val linkFlairType: String,
    @SerializedName("locked")
    val locked: Boolean,
    @SerializedName("media")
    val media: Media,
    @SerializedName("media_embed")
    val mediaEmbed: MediaEmbed,
    @SerializedName("media_only")
    val mediaOnly: Boolean,
    @SerializedName("mod_note")
    val modNote: Any,
    @SerializedName("mod_reason_by")
    val modReasonBy: Any,
    @SerializedName("mod_reason_title")
    val modReasonTitle: Any,
    @SerializedName("mod_reports")
    val modReports: List<Any>,
    @SerializedName("name")
    val name: String,
    @SerializedName("no_follow")
    val noFollow: Boolean,
    @SerializedName("num_comments")
    val numComments: Int,
    @SerializedName("num_crossposts")
    val numCrossposts: Int,
    @SerializedName("num_reports")
    val numReports: Any,
    @SerializedName("over_18")
    val over18: Boolean,
    @SerializedName("parent_whitelist_status")
    val parentWhitelistStatus: String,
    @SerializedName("permalink")
    val permalink: String,
    @SerializedName("pinned")
    val pinned: Boolean,
    @SerializedName("post_hint")
    val postHint: String,
    @SerializedName("preview")
    val preview: Preview?,
    @SerializedName("pwls")
    val pwls: Int,
    @SerializedName("quarantine")
    val quarantine: Boolean,
    @SerializedName("removal_reason")
    val removalReason: Any,
    @SerializedName("removed_by")
    val removedBy: Any,
    @SerializedName("removed_by_category")
    val removedByCategory: Any,
    @SerializedName("report_reasons")
    val reportReasons: Any,
    @SerializedName("saved")
    val saved: Boolean,
    @SerializedName("score")
    val score: Int,
    @SerializedName("secure_media")
    val secureMedia: SecureMedia,
    @SerializedName("secure_media_embed")
    val secureMediaEmbed: SecureMediaEmbed,
    @SerializedName("selftext")
    val selftext: String,
    @SerializedName("selftext_html")
    val selftextHtml: Any,
    @SerializedName("send_replies")
    val sendReplies: Boolean,
    @SerializedName("spoiler")
    val spoiler: Boolean,
    @SerializedName("stickied")
    val stickied: Boolean,
    @SerializedName("subreddit")
    val subreddit: String,
    @SerializedName("subreddit_id")
    val subredditId: String,
    @SerializedName("subreddit_name_prefixed")
    val subredditNamePrefixed: String,
    @SerializedName("subreddit_subscribers")
    val subredditSubscribers: Int,
    @SerializedName("subreddit_type")
    val subredditType: String,
    @SerializedName("suggested_sort")
    val suggestedSort: Any,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("thumbnail_height")
    val thumbnailHeight: Int,
    @SerializedName("thumbnail_width")
    val thumbnailWidth: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("total_awards_received")
    val totalAwardsReceived: Int,
    @SerializedName("ups")
    val ups: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("user_reports")
    val userReports: List<Any>,
    @SerializedName("view_count")
    val viewCount: Any,
    @SerializedName("visited")
    val visited: Boolean,
    @SerializedName("whitelist_status")
    val whitelistStatus: String,
    @SerializedName("wls")
    val wls: Int
)

data class AllAwarding(
    @SerializedName("award_sub_type")
    val awardSubType: String,
    @SerializedName("award_type")
    val awardType: String,
    @SerializedName("coin_price")
    val coinPrice: Int,
    @SerializedName("coin_reward")
    val coinReward: Int,
    @SerializedName("count")
    val count: Int,
    @SerializedName("days_of_drip_extension")
    val daysOfDripExtension: Int,
    @SerializedName("days_of_premium")
    val daysOfPremium: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("end_date")
    val endDate: Any,
    @SerializedName("giver_coin_reward")
    val giverCoinReward: Int,
    @SerializedName("icon_format")
    val iconFormat: String,
    @SerializedName("icon_height")
    val iconHeight: Int,
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("icon_width")
    val iconWidth: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("is_enabled")
    val isEnabled: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("penny_donate")
    val pennyDonate: Int,
    @SerializedName("penny_price")
    val pennyPrice: Int,
    @SerializedName("resized_icons")
    val resizedIcons: List<ResizedIcon>,
    @SerializedName("start_date")
    val startDate: Any,
    @SerializedName("subreddit_coin_reward")
    val subredditCoinReward: Int,
    @SerializedName("subreddit_id")
    val subredditId: Any
)

data class ResizedIcon(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class Gildings(
    @SerializedName("gid_1")
    val gid1: Int,
    @SerializedName("gid_2")
    val gid2: Int,
    @SerializedName("gid_3")
    val gid3: Int
)

data class Media(
    @SerializedName("reddit_video")
    val redditVideo: RedditVideo
)

data class RedditVideo(
    @SerializedName("dash_url")
    val dashUrl: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("fallback_url")
    val fallbackUrl: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("hls_url")
    val hlsUrl: String,
    @SerializedName("is_gif")
    val isGif: Boolean,
    @SerializedName("scrubber_media_url")
    val scrubberMediaUrl: String,
    @SerializedName("transcoding_status")
    val transcodingStatus: String,
    @SerializedName("width")
    val width: Int
)

class MediaEmbed(
)

data class Preview(
    @SerializedName("enabled")
    val enabled: Boolean,
    @SerializedName("images")
    val images: List<Image>?
)

data class Image(
    @SerializedName("id")
    val id: String,
    @SerializedName("resolutions")
    val resolutions: List<Resolution>,
    @SerializedName("source")
    val source: Source,
    @SerializedName("variants")
    val variants: Variants
)

data class Resolution(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class Source(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

class Variants(
)

data class SecureMedia(
    @SerializedName("reddit_video")
    val redditVideo: RedditVideoX
)

data class RedditVideoX(
    @SerializedName("dash_url")
    val dashUrl: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("fallback_url")
    val fallbackUrl: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("hls_url")
    val hlsUrl: String,
    @SerializedName("is_gif")
    val isGif: Boolean,
    @SerializedName("scrubber_media_url")
    val scrubberMediaUrl: String,
    @SerializedName("transcoding_status")
    val transcodingStatus: String,
    @SerializedName("width")
    val width: Int
)

class SecureMediaEmbed(
)