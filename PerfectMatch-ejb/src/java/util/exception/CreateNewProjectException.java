/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.exception;

/**
 *
 * @author user
 */
public class CreateNewProjectException extends Exception {

    /**
     * Creates a new instance of <code>CreateNewProjectException</code> without
     * detail message.
     */
    public CreateNewProjectException() {
    }

    /**
     * Constructs an instance of <code>CreateNewProjectException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CreateNewProjectException(String msg) {
        super(msg);
    }
}
