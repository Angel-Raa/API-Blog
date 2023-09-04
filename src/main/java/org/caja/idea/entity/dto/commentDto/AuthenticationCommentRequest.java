package org.caja.idea.entity.dto.commentDto;

public class AuthenticationCommentRequest {
    private String username;

    public AuthenticationCommentRequest(String username) {
        this.username = username;
    }

    public AuthenticationCommentRequest() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
