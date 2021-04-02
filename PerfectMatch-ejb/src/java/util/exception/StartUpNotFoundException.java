/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author Antho
 */
public class StartUpNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>StartUpNotFoundException</code> without
     * detail message.
     */
    public StartUpNotFoundException() {
    }

    /**
     * Constructs an instance of <code>StartUpNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public StartUpNotFoundException(String msg) {
        super(msg);
    }
}
