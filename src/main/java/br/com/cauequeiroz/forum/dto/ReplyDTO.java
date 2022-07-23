package br.com.cauequeiroz.forum.dto;

import br.com.cauequeiroz.forum.model.Reply;

public class ReplyDTO {

    private Long id;

    private String message;

    private String authorName;

    public ReplyDTO(Reply reply) {
        this.id = reply.getId();
        this.message = reply.getMessage();
        this.authorName = reply.getAuthor().getName();
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthorName() {
        return authorName;
    }
}
