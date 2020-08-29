package net.devpg.rpinder.batch.common.exception;

public class IllegalCollectedDateException extends RuntimeException {
    public IllegalCollectedDateException(String message) {
        super(message);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
