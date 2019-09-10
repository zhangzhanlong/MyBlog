package zhang.myblog.zhang.util;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created with IDEA
 *
 * @author xp
 * @date 2019/2/26 22:24
 */
public class Rest<T> {
    @ApiModelProperty(value = "是否成功")
    private boolean success=true;
    @ApiModelProperty(value = "错误编号")
    private Integer code;
    @ApiModelProperty(value = "错误信息")
    private String message;
    @ApiModelProperty(value = "返回对象")
    private T data;

    public Rest(boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public Rest() {
    }

    public boolean isSuccess() {
        return success;
    }

    public Rest<T> setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public Rest<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Rest<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Rest<T> setData(T data) {
        this.data = data;
        return this;
    }
}
