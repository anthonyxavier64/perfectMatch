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
public class CreateNewPostingException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewPostingException</code> without
     * detail message.
     */
    public CreateNewPostingException() {
    }

    /**
     * Constructs an instance of <code>CreateNewPostingException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewPostingException(String msg) {
        super(msg);
    }
}
