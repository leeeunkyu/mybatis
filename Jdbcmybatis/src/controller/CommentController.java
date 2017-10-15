package controller;

import java.util.ArrayList;
import java.util.List;

import dto.Comments;
import service.CommentService;

public class CommentController {
	private CommentService service = new CommentService();
	public CommentController() {
		// TODO Auto-generated constructor stub
	}
	public int insertComment (Comments com){
		
		return service.insertComment(com);
		
	}
	public List<Comments> selectCommentList(int indexNum){
		return service.selectCommentList(indexNum);
		
	}
	
}
