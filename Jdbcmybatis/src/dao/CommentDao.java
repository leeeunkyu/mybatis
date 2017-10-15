package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.ibatis.session.SqlSession;

import dto.Borders;
import dto.Comments;
import dto.Logs;

/**
 * �ڸ�Ʈ db������ ���� �ڸ�Ʈ daoŬ����
 * @author kosta
 *
 */
public class CommentDao {
	private static CommentDao instance = new CommentDao();
	private FactoryUser factory = FactoryUser.getInstance();
	private ResourceBundle commentBundle;

	private CommentDao() {
		commentBundle = ResourceBundle.getBundle("conf/comment_query");
	}
	
	public static CommentDao getInstance() {
		return instance;
	}

	public List<Comments> selectCommentList(int indexNum) {
		List<Comments> commentlist = new ArrayList<Comments>();
		SqlSession session = factory.factory.openSession(true);

		try {
			commentlist = session.selectList("selectcomment", indexNum);
	
		} finally {
			session.close();
		}
		return commentlist;
	}

	public int insertComment(Comments com) {
		SqlSession session = factory.factory.openSession(true);
		int insertNum;
		try {
			insertNum = session.insert("insertcomment", com);
		} finally {
			session.close();
		}
		return insertNum;
	
	}
	
}
