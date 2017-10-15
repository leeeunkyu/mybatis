package service;

import java.util.List;

import dao.CommentDao;
import dto.Comments;

public class CommentService {
	private CommentDao cdao = CommentDao.getInstance();
	
	public List<Comments> selectCommentList (int indexNum) {
		return cdao.selectCommentList(indexNum);
	}

	public int insertComment(Comments com) {
		return cdao.insertComment(com);
	}

	

}
