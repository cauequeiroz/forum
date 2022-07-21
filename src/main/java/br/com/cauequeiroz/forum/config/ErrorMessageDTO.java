package br.com.cauequeiroz.forum.config;

public class ErrorMessageDTO {

    private String message;

    public ErrorMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
