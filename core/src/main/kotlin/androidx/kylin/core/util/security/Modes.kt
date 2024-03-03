package androidx.kylin.core.util.security

/**
 * 模式，常用模式是CBC、ECB
 * @author RAE
 * @date 2024/01/14
 */
enum class Modes {
    CBC,
    ECB,
    GCM,
    CFB,
    CTR,
    CTS,
    OFB,
    NONE,
    Poly1305
}