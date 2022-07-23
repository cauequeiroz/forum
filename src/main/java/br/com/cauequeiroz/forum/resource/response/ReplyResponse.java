package br.com.cauequeiroz.forum.resource.response;

import br.com.cauequeiroz.forum.model.Reply;

public class ReplyResponse {

    private Long id;

    private String message;

    private String authorName;

    public ReplyResponse(Reply reply) {
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
