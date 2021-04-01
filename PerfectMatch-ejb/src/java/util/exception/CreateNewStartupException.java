package util.exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yappeizhen
 */
public class CreateNewStartUpException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewStartupException</code> without
     * detail message.
     */
    public CreateNewStartUpException() {
    }

    /**
     * Constructs an instance of <code>CreateNewStartupException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewStartUpException(String msg) {
        super(msg);
    }
}
