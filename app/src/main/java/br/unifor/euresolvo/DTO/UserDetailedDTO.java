package br.unifor.euresolvo.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.unifor.euresolvo.Models.Permission;
import br.unifor.euresolvo.Models.Post;
import br.unifor.euresolvo.Models.User;

public class UserDetailedDTO implements Serializable {

	private static final long serialVersionUID = -4334805450243911807L;

	private Long id;
	private String username;
	private String email;
	private Permission permission;
	private List<PostSimpleDTO> posts;

	public static UserDetailedDTO fromUser(User user) {
		UserDetailedDTO dto = new UserDetailedDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setPermission(user.getPermission());
		Set<PostSimpleDTO> posts = new HashSet<>();
		if (user.getPosts() != null) {
			for (Post p : user.getPosts()) {
				PostSimpleDTO pdto = PostSimpleDTO.fromPost(p);
				posts.add(pdto);
			}
		}
		dto.setPosts(new ArrayList<PostSimpleDTO>(posts));
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public List<PostSimpleDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostSimpleDTO> posts) {
		this.posts = posts;
	}

}
