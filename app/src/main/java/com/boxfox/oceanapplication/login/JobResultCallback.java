package com.boxfox.oceanapplication.login;

/**
 * Created by boxfox on 2018-01-20.
 */

public interface JobResultCallback<T> {
    public void end(boolean result, T data);
}
