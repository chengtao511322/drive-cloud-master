package com.drive.oss.exception;

/**
 * @author DreamChan
 */
public class MinioFetchException extends RuntimeException {
    public MinioFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}