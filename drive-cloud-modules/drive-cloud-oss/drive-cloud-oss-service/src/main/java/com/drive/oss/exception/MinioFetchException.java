package com.drive.oss.exception;

/**
 * @author xiaoguo
 */
public class MinioFetchException extends RuntimeException {
    public MinioFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}