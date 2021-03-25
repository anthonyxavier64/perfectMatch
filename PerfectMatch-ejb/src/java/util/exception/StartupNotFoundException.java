/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author yappeizhen
 */
public class StartUpNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>StartupNotFoundException</code> without
     * detail message.
     */
    public StartUpNotFoundException() {
    }

    /**
     * Constructs an instance of <code>StartupNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public StartUpNotFoundException(String msg) {
        super(msg);
    }
}
