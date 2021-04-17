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
public class DuplicateReviewException extends Exception {

    /**
     * Creates a new instance of <code>DuplicateReviewException</code> without
     * detail message.
     */
    public DuplicateReviewException() {
    }

    /**
     * Constructs an instance of <code>DuplicateReviewException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DuplicateReviewException(String msg) {
        super(msg);
    }
}
