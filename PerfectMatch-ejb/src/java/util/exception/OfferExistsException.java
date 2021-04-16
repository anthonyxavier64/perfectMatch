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
public class OfferExistsException extends Exception {

    /**
     * Creates a new instance of <code>OfferExistsException</code> without
     * detail message.
     */
    public OfferExistsException() {
    }

    /**
     * Constructs an instance of <code>OfferExistsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public OfferExistsException(String msg) {
        super(msg);
    }
}
