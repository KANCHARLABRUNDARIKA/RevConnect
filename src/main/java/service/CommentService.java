package service;

import dao.CommentDAO;
import model.Comment;
import java.util.List;

public class CommentService {
    CommentDAO commentDAO = new CommentDAO();
    public boolean addComment(int userId,int postId,String comment){
        return commentDAO.addComment(userId,postId,comment);
    }

    public List<Comment> getComments(int postId){
        return commentDAO.getComments(postId);
    }

    public boolean deleteComment(int commentId){
        return commentDAO.deleteComment(commentId);
    }

}