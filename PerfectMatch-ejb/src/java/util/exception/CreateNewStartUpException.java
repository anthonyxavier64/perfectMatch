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
public class CreateNewStartUpException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewStartUpException</code> without
     * detail message.
     */
    public CreateNewStartUpException() {
    }

    /**
     * Constructs an instance of <code>CreateNewStartUpException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewStartUpException(String msg) {
        super(msg);
    }
}
