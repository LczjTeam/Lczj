package jx.lczj.exception;

/**
 * 未登录
 * Created by 14260 on 2018/7/6.
 */
public class NoLoginException extends Exception {

    public NoLoginException() {
    }

    public NoLoginException(String message) {
        super(message);
    }
}
