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
public class RepeatedApplicationException extends Exception {

    /**
     * Creates a new instance of <code>RepeatedApplicationException</code>
     * without detail message.
     */
    public RepeatedApplicationException() {
    }

    /**
     * Constructs an instance of <code>RepeatedApplicationException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public RepeatedApplicationException(String msg) {
        super(msg);
    }
}
