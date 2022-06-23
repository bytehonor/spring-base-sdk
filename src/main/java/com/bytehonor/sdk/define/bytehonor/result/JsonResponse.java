package com.bytehonor.sdk.define.bytehonor.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.define.bytehonor.code.StandardCode;
import com.bytehonor.sdk.define.bytehonor.exception.ResponseException;

/**
 * Standard Json Response
 * 
 * @author lijianqiang
 *
 * @param <T>
 */
public final class JsonResponse<T> {

    private static final Logger LOG = LoggerFactory.getLogger(JsonResponse.class);

    private Integer code;

    private String message;

    private T data;

    public static JsonResponse<StringData> ok(String data) {
        return success(StringData.of(data));
    }

    public static JsonResponse<IntegerData> ok(Integer data) {
        return success(IntegerData.of(data));
    }

    public static JsonResponse<LongData> ok(Long data) {
        return success(LongData.of(data));
    }

    public static JsonResponse<BooleanData> ok(Boolean data) {
        return success(BooleanData.of(data));
    }

    public static <R> JsonResponse<R> success(R data) {
        JsonResponse<R> result = new JsonResponse<R>();
        result.setCode(StandardCode.OK);
        result.setMessage(StandardCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static <R> JsonResponse<R> error(int code, String message, R data) {
        JsonResponse<R> result = new JsonResponse<R>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <R> JsonResponse<R> error(int code, String message) {
        return error(code, message, null);
    }

    public static <T> T safeGet(JsonResponse<T> response) {
        if (response == null) {
            throw new ResponseException("RESPONSE NULL");
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("JsonResponse ErrorCode:{}", response.getCode());
        }
        if (response.getCode() != StandardCode.OK) {
            throw new ResponseException(response.toString());
        }
        T data = response.getData();
        if (data == null) {
            throw new ResponseException("RESPONSE BODY NULL");
        }
        return data;
    }

    public static <S> JsonResponse<S> fallback() {
        return fallback(null);
    }

    public static <S> JsonResponse<S> fallback(S data) {
        JsonResponse<S> result = new JsonResponse<S>();
        result.setCode(StandardCode.FEIGN_FALLBACK);
        result.setMessage("远程服务不可用");
        result.setData(data);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[code:").append(this.code).append(", message:").append(this.message).append("]");
        return sb.toString();
    }

}
