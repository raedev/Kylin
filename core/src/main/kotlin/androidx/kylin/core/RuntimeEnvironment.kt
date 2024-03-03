package androidx.kylin.core

/**
 * 运行环境，一般项目取：开发环境DEV、测试环境SIT、准生产环境SIM、生产环境PROD。
 *
 * @author RAE
 * @date 2024/01/12
 */
enum class RuntimeEnvironment {
    /** 开发环境，Development，本地环境 */
    DEV,

    /** 测试环境，System Integrate Test 系统集成测试环境（内测） */
    SIT,

    /** 用户验收测试环境，User Acceptance Test */
    UAT,

    /** 性能评估测试环境，Performance Evaluation Test */
    PET,

    /** 高仿真环境，准生产环境，Simulation */
    SIM,

    /** 正式生产环境, Production */
    PROD,

    /** 其他环境，Other System */
    OTHER,
}