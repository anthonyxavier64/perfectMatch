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
public class StartupNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>StartupNotFoundException</code> without
     * detail message.
     */
    public StartupNotFoundException() {
    }

    /**
     * Constructs an instance of <code>StartupNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public StartupNotFoundException(String msg) {
        super(msg);
    }
}
