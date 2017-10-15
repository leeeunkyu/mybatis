package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.ibatis.session.SqlSession;

import Util.TransferExcel;
import dto.Borders;
import oracle.net.ns.SessionAtts;

/**
 * 게시판 db에 접근하기위한 게시판 dao클래스
 * @author kosta
 *
 */
public class BorderDao {
	private FactoryUser factory = FactoryUser.getInstance();
	private static BorderDao instance = new BorderDao();
	TransferExcel te = new TransferExcel();
	private ResourceBundle borderBundle;

	public static BorderDao getInstance() {
		return instance;
	}
	private BorderDao() {
		borderBundle = ResourceBundle.getBundle("conf/border_query");
	}
	
	public int addBorder(Borders bor) {
		SqlSession session = factory.factory.openSession(true);
		int insertNum;
		try {
			insertNum = session.insert("insertborder", bor);
		} finally {
			session.close();
		}
		return insertNum;
	
	}
	
	public int selectIndex() {
		SqlSession session = factory.factory.openSession();
		List<Borders> list = new ArrayList<Borders>();
		int maxNum = 0;
		try {
			list = session.selectList("selectindex");
			maxNum = list.size();
		} finally {
			session.close();
		}		
		return list.get(maxNum-1).getBord_index()+1;
	}
	
	public List<Borders> selectList() {
		List<Borders> a_bor = new ArrayList<>();
		SqlSession session = factory.factory.openSession();
		try {
			a_bor = session.selectList("selectborder");
			
		} finally {
			session.close();
		}
			return a_bor;
		}
	
	public Borders selctOne(int index_num) {
		Borders border = null;
		SqlSession session = factory.factory.openSession(true);

		try {
			border = session.selectOne("select_toindex", index_num);
			HashMap<String, String> map = new HashMap<String,String>();
			map.put("index",""+border.getBord_index());
			map.put("count",""+border.getBord_count());
			session.update("updateborder",map);		
		} finally {
			session.close();
		}
	
		return border;
		
	}
	
	public List<Borders> selectUserBord(String userid) {
		List<Borders> a_bor = new ArrayList<Borders>();
		SqlSession session = factory.factory.openSession(true);

		try {
			a_bor = session.selectList("select_toid", userid);
	
		} finally {
			session.close();
		}
		return a_bor;
	}
	public boolean deleteOne(int delete_num, String delete_pwd) {
		SqlSession session = factory.factory.openSession(true);
		String userPwd=null;
		delete_pwd = te.getEncrypt(delete_pwd);
		try {
			userPwd = session.selectOne("select_topwd",delete_num);
			if(delete_pwd.equals(userPwd)) {
				session.delete("deleteborder", delete_num);
			}
		} finally {
			session.close();
		}		
		
		return true;
	}

}
