package hun.portfolio.first.data

import hun.portfolio.first.data.EMOJIS.EMOJI_CLOUDS
import hun.portfolio.first.data.EMOJIS.EMOJI_FLAMINGO
import hun.portfolio.first.data.EMOJIS.EMOJI_MELTING
import hun.portfolio.first.data.EMOJIS.EMOJI_PINK_HEART
import hun.portfolio.first.data.EMOJIS.EMOJI_POINTS
import hun.portfolio.first.ui.chat.MessageUiState

val messages = listOf(
    MessageUiState(
        authorName = "me",
        content = "Check it out!",
        timestamp = "8:07 PM",
    ),
    MessageUiState(
        authorName = "me",
        content = "Thank you!$EMOJI_PINK_HEART",
        timestamp = "8:06 PM"
    ),
    MessageUiState(
        authorName = "Taylor Brooks",
        content = "You can use all the same stuff",
        timestamp = "8:05 PM"
    ),
    MessageUiState(
        authorName = "Taylor Brooks",
        content = "@aliconors Take a look at the `Flow.collectAsStateWithLifecycle()` APIs",
        timestamp = "8:05 PM"
    ),
    MessageUiState(
        authorName = "John Glenn",
        content = "Compose newbie as well $EMOJI_FLAMINGO, have you looked at the JetNews sample? " +
                "Most blog posts end up out of date pretty fast but this sample is always up to " +
                "date and deals with async data loading (it's faked but the same idea " +
                "applies) $EMOJI_POINTS https://goo.gle/jetnews",
        timestamp = "8:04 PM"
    ),
    MessageUiState(
        authorName = "me",
        content = "Compose newbie: I’ve scourged the internet for tutorials about async data " +
                "loading but haven’t found any good ones $EMOJI_MELTING $EMOJI_CLOUDS. " +
                "What’s the recommended way to load async data and emit composable widgets?",
        timestamp = "8:03 PM"
    ),
    MessageUiState(
        authorName = "Shangeeth Sivan",
        content = "Does anyone know about Glance Widgets its the new way to build widgets in Android!",
        timestamp = "8:08 PM"
    ),
    MessageUiState(
        authorName = "Taylor Brooks",
        content = "Wow! I never knew about Glance Widgets when was this added to the android ecosystem",
        timestamp = "8:10 PM"
    ),
    MessageUiState(
        authorName = "John Glenn",
        content = "Yeah its seems to be pretty new!",
        timestamp = "8:12 PM"
    ),
)

val messageByMe = MessageUiState(
    authorName = "me",
    content = "Hello",
    timestamp = "8:07 PM"
)

val messageByOther = MessageUiState(
    authorName = "Taylor Brooks",
    content = "World!",
    timestamp = "8:05 PM"
)

object EMOJIS {
    // EMOJI 15
    const val EMOJI_PINK_HEART = "\uD83E\uDE77"

    // EMOJI 14 🫠
    const val EMOJI_MELTING = "\uD83E\uDEE0"

    // ANDROID 13.1 😶‍🌫️
    const val EMOJI_CLOUDS = "\uD83D\uDE36\u200D\uD83C\uDF2B️"

    // ANDROID 12.0 🦩
    const val EMOJI_FLAMINGO = "\uD83E\uDDA9"

    // ANDROID 12.0  👉
    const val EMOJI_POINTS = " \uD83D\uDC49"
}
