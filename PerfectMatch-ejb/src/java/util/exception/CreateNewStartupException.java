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
public class CreateNewStartupException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewStartupException</code> without
     * detail message.
     */
    public CreateNewStartupException() {
    }

    /**
     * Constructs an instance of <code>CreateNewStartupException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewStartupException(String msg) {
        super(msg);
    }
}
