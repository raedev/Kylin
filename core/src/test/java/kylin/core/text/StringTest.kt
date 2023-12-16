package kylin.core.text

import androidx.kylin.core.text.isNotNull
import androidx.kylin.core.text.nullOrEmptyDefault
import org.junit.Test

class StringTest {

    @Test
    fun main() {
        var text: String? = null
        text = "Hello Word!"
        println("the text: $text")
        println("isNotNull: ${text.isNotNull()}")
        println("nullOrEmptyDefault: ${text.nullOrEmptyDefault("default value")}")
    }
}