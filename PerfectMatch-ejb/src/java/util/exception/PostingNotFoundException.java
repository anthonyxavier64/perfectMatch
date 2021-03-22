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
public class PostingNotFoundException extends Exception {

    /**
     * Creates a new instance of <code>PostingNotFoundException</code> without
     * detail message.
     */
    public PostingNotFoundException() {
    }

    /**
     * Constructs an instance of <code>PostingNotFoundException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PostingNotFoundException(String msg) {
        super(msg);
    }
}
