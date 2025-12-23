/* (C)2025 */
package io.github.abhipdgupta.judge0.exceptions;

public class Judge0FailedHttpException extends RuntimeException {
    public Judge0FailedHttpException(String message) {
        super(message);
    }

    public Judge0FailedHttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public Judge0FailedHttpException(Throwable cause) {
        super(cause);
    }
}
