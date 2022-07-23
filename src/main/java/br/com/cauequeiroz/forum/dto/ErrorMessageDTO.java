package br.com.cauequeiroz.forum.dto;

public class ErrorMessageDTO {

    private String message;

    public ErrorMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
