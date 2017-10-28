package br.unifor.euresolvo.DTO;

import java.io.Serializable;

import br.unifor.euresolvo.Models.Comment;

public class CommentDTO implements Serializable {

	private static final long serialVersionUID = 3069238842078018772L;

	private Long id;
	private String content;
	private String date;
	private String authorUsername;
	private Long postId;

	public static CommentDTO fromComment(Comment comment) {
		CommentDTO dto = new CommentDTO();
		dto.setId(comment.getId());
		dto.setContent(comment.getContent());
		dto.setDate(comment.getDate());
		if (comment.getAuthor() != null)
			dto.setAuthorUsername(comment.getAuthor().getUsername());
		if (comment.getPost() != null)
			dto.setPostId(comment.getPost().getId());
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAuthorUsername() {
		return authorUsername;
	}

	public void setAuthorUsername(String authorUsername) {
		this.authorUsername = authorUsername;
	}

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}
}
