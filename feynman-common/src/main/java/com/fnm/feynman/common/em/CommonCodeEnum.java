
package com.fnm.feynman.common.em;

/**
 * @author 刘彦军
 */
public enum CommonCodeEnum implements FastEnum {

    /**
     * 成功响应码 [200000～300000)
     */
    SUCCESS("20000", "消息处理成功"),

    ERROR("-1", "服务器异常"),

    /**
     * 客户端错误响应码 [400000～500000)
     */
    VALIDATE_ERROR("400000", "数据校验不合法"),

    ACCESS_PERM_DENIED("400001", "对不起,您没有访问权限"),

    CAPTCHA_VALIDATE_FAIL("400002", "图片验证码验证失败"),

    CAPTCHA_EXPIRE("400003", "图片验证码已失效"),

    /**
     * 服务端异常响应码 [500000～600000)
     */
    EXCEPTION("500000", "系统繁忙,请稍后重试"),

    COMMON_EXCEPTION("500001", "消息处理异常"),

    DB_INSERT_EXCEPTION("500002", "数据插入异常"),

    DB_UPDATE_EXCEPTION("500003", "数据更新异常"),

    DB_SELECT_EXCEPTION("500004", "数据查询异常"),

    DATA_IS_EXIST("50005", "该数据已存在"),

    DATA_IS_NOT_EXIST("50007", "该数据不存在"),

    PARAMETERS_MISS("50006", "数据参数异常"),

    DB_KEY_DUPLICATE("500011", "主键或唯一性约束冲突"),


    PASSWORD_ERROR("401", "密码错误"),
    GET_TOKEN_ERROR("402", "获取token错误"),
    AUTHORITY_ERROR("403", "权限不足"),
    TOKEN_ERROR("405", "token错误或已失效"),
    OUD_PASSWORD_ERROR("407", "旧密码错误"),
    ACCOUNT_ERROR("408", "账号错误"),
    LOGIN_TYPE_ERROR("409", "loginType错误"),



    NOT_USER("70003", "没有此用户"),
    PHONE_ERROR("70004", "手机号不正确"),
    ACCOUNT_MORE("70007","该账号请联系管理员重置"),
    ACCOUNT_USER("70008", "无此账号，或账号和手机号不匹配"),;




    private String code;
    private String message;

    private CommonCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    @Override
    public String getCode() {
        return code;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public static CommonCodeEnum getEnumByCode(String code) {
        for (CommonCodeEnum commonCodeEnum : CommonCodeEnum.values()) {
            if (commonCodeEnum.getCode().equals(code)) {
                return commonCodeEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("CommonCodeEnum name = %s code = %s message = %s", this.name(), code, message);
    }

}
