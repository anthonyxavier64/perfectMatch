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
public class JobNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>JobNotFoundException</code> without
     * detail message.
     */
    public JobNotFoundException() {
    }

    /**
     * Constructs an instance of <code>JobNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public JobNotFoundException(String msg) {
        super(msg);
    }
}
