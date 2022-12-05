package com.qis.springframework.beans;

/**
 * bean的异常信息
 *
 * @author: qishuo
 * @date: 2022/12/5 18:31
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
