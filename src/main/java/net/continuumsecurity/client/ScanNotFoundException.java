package net.continuumsecurity.client;

/**
 * 
 * 
 * @author Stephen de Vries
 * @author Adam Parsons
 * @version 0.0.2, 04/29/14
 * @since 1.0.0
 */
public class ScanNotFoundException extends RuntimeException {

    public ScanNotFoundException(String message) {
        super(message);
    }

    public ScanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
