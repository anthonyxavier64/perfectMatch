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
public class CreateNewReviewOfStartUpException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewReviewOfStartUpException</code>
     * without detail message.
     */
    public CreateNewReviewOfStartUpException() {
    }

    /**
     * Constructs an instance of <code>CreateNewReviewOfStartUpException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewReviewOfStartUpException(String msg) {
        super(msg);
    }
}
