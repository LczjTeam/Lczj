package jx.lczj.exception;

/**
 * 没权限
 * Created by 14260 on 2018/7/6.
 */
public class NoAuthorityException extends Exception{
    public NoAuthorityException() {
    }

    public NoAuthorityException(String message) {
        super(message);
    }
}
