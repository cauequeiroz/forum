package br.com.cauequeiroz.forum.resource.response;

public class LoginResponse {

    private String token;

    private String authType;

    public LoginResponse(String token, String authType) {
         this.token = token;
         this.authType = authType;
    }

    public String getToken() {
        return token;
    }

    public String getAuthType() {
        return authType;
    }
}
