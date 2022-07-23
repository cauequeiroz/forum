package br.com.cauequeiroz.forum.resource.response;

public class ErrorFieldResponse {

    private String field;

    private String message;

    public ErrorFieldResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
