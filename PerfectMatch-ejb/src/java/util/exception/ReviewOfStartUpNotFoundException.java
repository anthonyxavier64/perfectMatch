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
public class ReviewOfStartUpNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>ReviewOfStartUpNotFoundException</code>
     * without detail message.
     */
    public ReviewOfStartUpNotFoundException() {
    }

    /**
     * Constructs an instance of <code>ReviewOfStartUpNotFoundException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ReviewOfStartUpNotFoundException(String msg) {
        super(msg);
    }
}
