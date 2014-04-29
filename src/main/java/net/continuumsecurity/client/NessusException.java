package net.continuumsecurity.client;

/**
 * 
 * 
 * @author Stephen de Vries
 * @author Adam Parsons
 * @version 0.0.2, 04/29/14
 * @since 1.0.0
 */
public class NessusException extends RuntimeException {

    public NessusException(String message) {
        super(message);
    }

    public NessusException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
