package br.unifor.euresolvo.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.unifor.euresolvo.DTO.CommentDTO;
import br.unifor.euresolvo.DTO.PostDetailedDTO;
import br.unifor.euresolvo.DTO.PostSimpleDTO;
import br.unifor.euresolvo.DTO.ReportDTO;
import br.unifor.euresolvo.DTO.SolutionDTO;
import br.unifor.euresolvo.DTO.UserDetailedDTO;
import br.unifor.euresolvo.DTO.UserSimpleDTO;
import br.unifor.euresolvo.Models.Permission;

public class Conversor {

    public List<UserSimpleDTO> toListOfUserSimpleDTO(JSONArray array) {
        List<UserSimpleDTO> users = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject u = array.getJSONObject(i);
                users.add(toUserSimpleDTO(u));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public UserSimpleDTO toUserSimpleDTO(JSONObject object) {
        UserSimpleDTO dto = new UserSimpleDTO();
        try {
            dto.setId(object.getLong("id"));
            dto.setUsername(object.getString("username"));
            dto.setEmail(object.getString("email"));
            dto.setPermission(toPermission(object.getJSONObject("permission")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dto;
    }

    private Permission toPermission(JSONObject object) {
        Permission p = new Permission();
        try {
            p.setId(object.getLong("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return p;
    }

    public UserDetailedDTO toUserDetailedDTO(JSONObject object) {
        UserDetailedDTO dto = new UserDetailedDTO();
        try {
            dto.setId(object.getLong("id"));
            dto.setUsername(object.getString("username"));
            dto.setEmail(object.getString("email"));
            dto.setPermission(toPermission(object.getJSONObject("permission")));
            dto.setPosts(toListOfPostSimpleDTO(object.getJSONArray("posts")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public List<PostSimpleDTO> toListOfPostSimpleDTO(JSONArray array) {
        List<PostSimpleDTO> posts = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject p = array.getJSONObject(i);
                posts.add(toPostSimpleDTO(p));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return posts;
    }

    public PostSimpleDTO toPostSimpleDTO(JSONObject object) {
        PostSimpleDTO dto = new PostSimpleDTO();
        try {
            dto.setId(object.getLong("id"));
            dto.setAuthorUsername(object.getString("authorUsername"));
            dto.setContentPreview(object.getString("contentPreview"));
            dto.setDate(object.getString("date"));
            dto.setTitle(object.getString("title"));
            dto.setVoteCount(object.getInt("voteCount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public PostDetailedDTO toPostDetailedDTO(JSONObject object) {
        PostDetailedDTO dto = new PostDetailedDTO();
        try {
            dto.setId(object.getLong("id"));
            dto.setAuthorUsername(object.getString("authorUsername"));
            dto.setContent(object.getString("content"));
            dto.setDate(object.getString("date"));
            dto.setTitle(object.getString("title"));
            dto.setComments(toListOfCommentDTO(object.getJSONArray("comments")));
            dto.setSolutions(toListOfSolutionDTO(object.getJSONArray("solutions")));
            dto.setVoteIds(toSetOfVoteIds(object.getJSONArray("voteIds")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dto;
    }

    private List<CommentDTO> toListOfCommentDTO(JSONArray array) {
        List<CommentDTO> comments = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject c = array.getJSONObject(i);
                comments.add(toCommentDTO(c));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return comments;
    }

    private CommentDTO toCommentDTO(JSONObject object) {
        CommentDTO dto = new CommentDTO();
        try {
            dto.setId(object.getLong("id"));
            dto.setAuthorUsername(object.getString("authorUsername"));
            dto.setContent(object.getString("content"));
            dto.setDate(object.getString("date"));
            dto.setPostId(object.getLong("postId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dto;
    }

    private List<SolutionDTO> toListOfSolutionDTO(JSONArray array) {
        List<SolutionDTO> solutions = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject s = array.getJSONObject(i);
                solutions.add(toSolutionDTO(s));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return solutions;
    }

    private SolutionDTO toSolutionDTO(JSONObject object) {
        SolutionDTO dto = new SolutionDTO();
        try {
            dto.setId(object.getLong("id"));
            dto.setAuthorUsername(object.getString("authorUsername"));
            dto.setContent(object.getString("content"));
            dto.setDate(object.getString("date"));
            dto.setPostId(object.getLong("postId"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public List<ReportDTO> toListOfReportDTO(JSONArray array) {
        List<ReportDTO> reports = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject r = array.getJSONObject(i);
                reports.add(toReportDTO(r));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return reports;
    }

    private ReportDTO toReportDTO(JSONObject object) {
        ReportDTO dto = new ReportDTO();
        try {
            dto.setId(object.getLong("id"));
            dto.setAuthorUsername(object.getString("authorUsername"));
            dto.setDescription(object.getString("description"));
            dto.setPost(toPostSimpleDTO(object.getJSONObject("post")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dto;
    }

    private Set<Long> toSetOfVoteIds(JSONArray array) {
        Set<Long> votes = new HashSet<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                Long v = array.getLong(i);
                votes.add(v);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return votes;
    }

}
