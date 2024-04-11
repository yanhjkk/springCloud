package com.example.ykcloud.Common;

import com.example.ykcloud.emums.ErrorCodeEnum;
import com.example.ykcloud.emums.NotifyIconEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(
        value = "接口返回对象",
        description = "接口返回对象"
)
public class JsonResult<T> implements Serializable {
    public static final Object SUCCESS_CODE = 200;
    public static final String SUCCESS_MESSAGE = "操作成功";
    @ApiModelProperty("成功标志")
    private boolean success;
    @ApiModelProperty("返回代码")
    private Object code;
    @ApiModelProperty("返回处理消息")
    private String message;
    @ApiModelProperty("返回数据对象")
    private T result;
    @ApiModelProperty("访问凭证")
    private String token;
    @ApiModelProperty("提示标识图样")
    private String notifyIcon;

    private JsonResult<T> code(Object code) {
        this.setCode(code);
        return this;
    }

    private JsonResult<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    private JsonResult<T> success(boolean success) {
        this.setSuccess(success);
        return this;
    }

    private JsonResult<T> result(T result) {
        this.setResult(result);
        return this;
    }

    private JsonResult<T> token(String token) {
        this.setToken(token);
        return this;
    }

    private JsonResult<T> notifyIcon(String notifyIcon) {
        this.setNotifyIcon(notifyIcon);
        return this;
    }

    public JsonResult() {
        this(SUCCESS_CODE, "操作成功");
    }

    public JsonResult(Object code, String message) {
        this.success = true;
        this.code(code).message(message).success(true);
    }

    public JsonResult(Object code, String message, String notifyIcon) {
        this.success = true;
        this.code(code).message(message).success(true).setNotifyIcon(notifyIcon);
    }

    public JsonResult(Object code, String message, String notifyIcon, boolean success) {
        this.success = true;
        this.code(code).message(message).success(success).setNotifyIcon(notifyIcon);
    }

    public JsonResult(Object code, String message, boolean success) {
        this.success = true;
        this.code(code).message(message).success(success);
    }

    public JsonResult(Object code, String message, boolean success, T result) {
        this.success = true;
        this.code(code).message(message).success(success).result(result);
    }

    public JsonResult(Object code, String message, boolean success, String notifyIcon) {
        this.success = true;
        this.code(code).message(message).success(success).notifyIcon(notifyIcon);
    }

    public JsonResult(Object code, String message, boolean success, T result, String token) {
        this.success = true;
        this.code(code).message(message).success(success).result(result).token(token);
    }

    public JsonResult(Object code, String message, boolean success, T result, String token, String notifyIcon) {
        this.success = true;
        this.code(code).message(message).success(success).result(result).token(token).setNotifyIcon(notifyIcon);
    }

    public static JsonResult success() {
        return new JsonResult(SUCCESS_CODE, "操作成功");
    }

    public static JsonResult success(Object code, String message) {
        return new JsonResult(code, message, true);
    }

    public static JsonResult success(Object code, String message, Object result) {
        return new JsonResult(code, message, true, result);
    }

    public static JsonResult success(Object code, String message, Object result, String token) {
        return new JsonResult(SUCCESS_CODE, "操作成功", true, result, token);
    }

    public static JsonResult success(Object result) {
        return new JsonResult(SUCCESS_CODE, "操作成功", true, result);
    }

    public static JsonResult success(Object result, NotifyIconEnum notifyIconEnum) {
        return new JsonResult(SUCCESS_CODE, "操作成功", true, result, "", notifyIconEnum.name());
    }

    public static JsonResult success(Object result, String token, boolean success) {
        return new JsonResult(SUCCESS_CODE, "操作成功", success, result, token);
    }

    public static JsonResult error() {
        return new JsonResult(ErrorCodeEnum.BASE99990500.code(), ErrorCodeEnum.BASE99990500.msg(), false);
    }

    public static JsonResult error(String message) {
        return new JsonResult(ErrorCodeEnum.BASE99990500.code(), message, false);
    }

    public static JsonResult error(String message, NotifyIconEnum notifyIconEnum) {
        return new JsonResult(ErrorCodeEnum.BASE99990500.code(), message, notifyIconEnum.name(), false);
    }

    public static JsonResult error(Object code, String message) {
        return new JsonResult(code, message, false);
    }

    public static JsonResult error(Object code, String message, Object result) {
        return new JsonResult(code, message, false, result);
    }

    public static JsonResult error(Object code, String message, NotifyIconEnum notifyIconEnum) {
        return new JsonResult(code, message, false, notifyIconEnum.name());
    }

    public static JsonResult error(ErrorCodeEnum errorCodeEnum) {
        return new JsonResult(errorCodeEnum.code(), errorCodeEnum.msg(), false);
    }

    public static JsonResult error(ErrorCodeEnum errorCodeEnum, Object... args) {
        return new JsonResult(errorCodeEnum.code(), String.format(errorCodeEnum.msg(), args), false);
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Object getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getResult() {
        return this.result;
    }

    public String getToken() {
        return this.token;
    }

    public String getNotifyIcon() {
        return this.notifyIcon;
    }

    public void setSuccess(final boolean success) {
        this.success = success;
    }

    public void setCode(final Object code) {
        this.code = code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setResult(final T result) {
        this.result = result;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public void setNotifyIcon(final String notifyIcon) {
        this.notifyIcon = notifyIcon;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof JsonResult)) {
            return false;
        } else {
            JsonResult<?> other = (JsonResult) o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.isSuccess() != other.isSuccess()) {
                return false;
            } else {
                label73:
                {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label73;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label73;
                    }

                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if (this$message == null) {
                    if (other$message != null) {
                        return false;
                    }
                } else if (!this$message.equals(other$message)) {
                    return false;
                }

                label59:
                {
                    Object this$result = this.getResult();
                    Object other$result = other.getResult();
                    if (this$result == null) {
                        if (other$result == null) {
                            break label59;
                        }
                    } else if (this$result.equals(other$result)) {
                        break label59;
                    }

                    return false;
                }

                Object this$token = this.getToken();
                Object other$token = other.getToken();
                if (this$token == null) {
                    if (other$token != null) {
                        return false;
                    }
                } else if (!this$token.equals(other$token)) {
                    return false;
                }

                Object this$notifyIcon = this.getNotifyIcon();
                Object other$notifyIcon = other.getNotifyIcon();
                if (this$notifyIcon == null) {
                    if (other$notifyIcon != null) {
                        return false;
                    }
                } else if (!this$notifyIcon.equals(other$notifyIcon)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof JsonResult;
    }

//    public int hashCode() {
//        int PRIME = true;
//        int result = 1;
//        int result = result * 59 + (this.isSuccess() ? 79 : 97);
//        Object $code = this.getCode();
//        result = result * 59 + ($code == null ? 43 : $code.hashCode());
//        Object $message = this.getMessage();
//        result = result * 59 + ($message == null ? 43 : $message.hashCode());
//        Object $result = this.getResult();
//        result = result * 59 + ($result == null ? 43 : $result.hashCode());
//        Object $token = this.getToken();
//        result = result * 59 + ($token == null ? 43 : $token.hashCode());
//        Object $notifyIcon = this.getNotifyIcon();
//        result = result * 59 + ($notifyIcon == null ? 43 : $notifyIcon.hashCode());
//        return result;
//    }

    public String toString() {
        return "JsonResult(success=" + this.isSuccess() + ", code=" + this.getCode() + ", message=" + this.getMessage() + ", result=" + this.getResult() + ", token=" + this.getToken() + ", notifyIcon=" + this.getNotifyIcon() + ")";
    }
}