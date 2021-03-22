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
public class CreateNewApplicationException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewApplicationException</code>
     * without detail message.
     */
    public CreateNewApplicationException() {
    }

    /**
     * Constructs an instance of <code>CreateNewApplicationException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewApplicationException(String msg) {
        super(msg);
    }
}
