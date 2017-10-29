package br.unifor.euresolvo.DTO;

import java.io.Serializable;

public class VoteDTO implements Serializable {

    private Long postId;
    private Long userId;

    public VoteDTO() {}

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
