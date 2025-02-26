package hun.portfolio.first.data

import hun.portfolio.first.R
import hun.portfolio.first.ui.chat.MessageData
import hun.portfolio.first.data.EMOJIS.EMOJI_CLOUDS
import hun.portfolio.first.data.EMOJIS.EMOJI_FLAMINGO
import hun.portfolio.first.data.EMOJIS.EMOJI_MELTING
import hun.portfolio.first.data.EMOJIS.EMOJI_POINTS
import hun.portfolio.first.data.EMOJIS.EMOJI_PINK_HEART

val messages = listOf(
    MessageData(
        "me",
        "Check it out!",
        "8:07 PM"
    ),
    MessageData(
        "me",
        "Thank you!$EMOJI_PINK_HEART",
        "8:06 PM"
    ),
    MessageData(
        "Taylor Brooks",
        "You can use all the same stuff",
        "8:05 PM"
    ),
    MessageData(
        "Taylor Brooks",
        "@aliconors Take a look at the `Flow.collectAsStateWithLifecycle()` APIs",
        "8:05 PM"
    ),
    MessageData(
        "John Glenn",
        "Compose newbie as well $EMOJI_FLAMINGO, have you looked at the JetNews sample? " +
                "Most blog posts end up out of date pretty fast but this sample is always up to " +
                "date and deals with async data loading (it's faked but the same idea " +
                "applies) $EMOJI_POINTS https://goo.gle/jetnews",
        "8:04 PM"
    ),
    MessageData(
        "me",
        "Compose newbie: I’ve scourged the internet for tutorials about async data " +
                "loading but haven’t found any good ones $EMOJI_MELTING $EMOJI_CLOUDS. " +
                "What’s the recommended way to load async data and emit composable widgets?",
        "8:03 PM"
    ),
    MessageData(
        "Shangeeth Sivan",
        "Does anyone know about Glance Widgets its the new way to build widgets in Android!",
        "8:08 PM"
    ),
    MessageData(
        "Taylor Brooks",
        "Wow! I never knew about Glance Widgets when was this added to the android ecosystem",
        "8:10 PM"
    ),
    MessageData(
        "John Glenn",
        "Yeah its seems to be pretty new!",
        "8:12 PM"
    ),
)

val messageByMe = MessageData(
    author = "me",
    content = "Hello",
    timestamp = "8:07 PM"
)

val messageByOther = MessageData(
    author = "Taylor Brooks",
    content ="World!",
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
