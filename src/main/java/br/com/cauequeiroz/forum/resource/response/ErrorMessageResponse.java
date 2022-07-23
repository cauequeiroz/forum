package br.com.cauequeiroz.forum.resource.response;

public class ErrorMessageResponse {

    private String message;

    public ErrorMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
