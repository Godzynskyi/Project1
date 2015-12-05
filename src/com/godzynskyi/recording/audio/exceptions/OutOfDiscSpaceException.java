package com.godzynskyi.recording.audio.exceptions;

/**
 * Throws if customer tries to make album's duration more than 70 minutes
 */
public class OutOfDiscSpaceException extends Exception {
    public OutOfDiscSpaceException(Throwable cause) {
        super(cause);
    }
}
