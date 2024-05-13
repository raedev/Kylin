package androidx.kylin.core

/**
 * 运行环境
 *
 * @author RAE
 * @date 2024/01/12
 */
enum class RuntimeEnvironment {

    /** 本地开发环境 */
    DEV,

    /** 服务器测试环境 */
    TEST,

    /** 准生产环境，预发布环境  */
    SIM,

    /** 正式生产环境, Production */
    PROD
}