package org.caja.idea.entity.dto.postDto;

public class AuthenticationPostRequest {
    private String username;

    public AuthenticationPostRequest() {
    }

    public AuthenticationPostRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
