/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import java.util.List;

/**
 *
 * @author Antho
 */
public class FavouritesWrapper {
    private PostingWrapper post;
    private Long postingId;
    private Long studentId;

    public FavouritesWrapper() {
    }

    public PostingWrapper getPost() {
        return post;
    }

    public void setPost(PostingWrapper post) {
        this.post = post;
    }

    public Long getPostingId() {
        return postingId;
    }

    public void setPostingId(Long postingId) {
        this.postingId = postingId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
    
    
}
