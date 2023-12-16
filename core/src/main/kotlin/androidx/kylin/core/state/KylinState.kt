package androidx.kylin.core.state

/**
 *
 * @author rae
 * @copyright https://github.com/raedev
 */
sealed class KylinState(val message: String) {

    data object Idle : KylinState("IDLE")

    data object Loading : KylinState("LOADING")

    class Error(message: String, val code: Int = 0) : KylinState(message)

    class Success<T>(val data: T) : KylinState("SUCCESS")

    class Empty(message: String? = null) : KylinState(message ?: "EMPTY")

    data object Finish : KylinState("FINISH")

    data object Cancel : KylinState("CANCEL")

    data object Timeout : KylinState("TIMEOUT")

    data object UnKnow : KylinState("UNKNOW")

}