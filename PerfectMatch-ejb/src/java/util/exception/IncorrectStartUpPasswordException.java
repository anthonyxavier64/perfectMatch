/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author Phire
 */
public class IncorrectStartUpPasswordException extends Exception {

    /**
     * Creates a new instance of <code>IncorrectStartUpPasswordException</code>
     * without detail message.
     */
    public IncorrectStartUpPasswordException() {
    }

    /**
     * Constructs an instance of <code>IncorrectStartUpPasswordException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public IncorrectStartUpPasswordException(String msg) {
        super(msg);
    }
}
