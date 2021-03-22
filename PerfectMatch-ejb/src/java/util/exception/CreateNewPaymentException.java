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
public class CreateNewPaymentException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewPaymentException</code> without
     * detail message.
     */
    public CreateNewPaymentException() {
    }

    /**
     * Constructs an instance of <code>CreateNewPaymentException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewPaymentException(String msg) {
        super(msg);
    }
}
