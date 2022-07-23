package br.com.cauequeiroz.forum.dto;

public class ErrorFieldDTO {

    private String field;

    private String message;

    public ErrorFieldDTO(String field, String message) {
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
