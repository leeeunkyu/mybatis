package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.ibatis.session.SqlSession;

import Util.TransferExcel;
import dto.BlockUsers;
import dto.Logs;
import dto.Users;

/**
 * userdb에 접속하기위한 userdao클래스
 * @author kosta
 *
 */
public class UserDao {
	private static UserDao instance = new UserDao();
	private FactoryUser factory = FactoryUser.getInstance();
	TransferExcel te = new TransferExcel();
	BlockUsers bu;
	DateFormat df;

	private UserDao() {	
	}
	
	public static UserDao getInstance() {
		return instance;
	}
	
	public boolean isUsers(String user_id) {
		SqlSession session = factory.factory.openSession(); // boolean : auto commit
		String isUserid = null;
		try {
			isUserid = session.selectOne("selectid", user_id);
			if(isUserid != null) {
				return true;
			}
		} finally {
			session.close();
		}
		return false;
	}
	public int addUsers(Users usr) {
		SqlSession session = factory.factory.openSession(true);
		int insertNum;
		try {
			insertNum = session.insert("insertuser",usr);
			System.out.println(insertNum);
		} finally {
			session.close();
		}
		return insertNum;
	}
	public boolean selectUser(String userid, String userpwd) {
		SqlSession session = factory.factory.openSession();
		SqlSession logsession = factory.factory.openSession(true);
		df = DateFormat.getDateTimeInstance(0,1,Locale.KOREA);
		userpwd = te.getEncrypt(userpwd);
		try {
			if(userpwd.equals(session.selectOne("selectpwd_toid", userid))){
				logsession.insert("insertlog", new Logs(userid,df.format(new Date()), "p"));
				return true;
			}else {
				logsession.insert("insertlog", new Logs(userid, df.format(new Date()), "f"));
				return false;
			}
		} finally {
			session.close();
			logsession.close();
		}
	
			
	}
	public String selectID(String username) {
		SqlSession session = factory.factory.openSession();
		username = te.getEncrypt(username);
		String id;
		try {
			id = session.selectOne("selectid_toname", username);
		} finally {
			session.close();
		}
		return id;
	}

	public boolean deleteUser(String deleteid, String deletepwd) {
		SqlSession session = factory.factory.openSession(true);
		deletepwd = te.getEncrypt(deletepwd);
		try {
			if(deletepwd.equals(session.selectOne("selectpwd_toid", deleteid))){
				session.delete("deletelog_toid", deleteid);
				session.delete("deleteid", deleteid);
				return true;
			}else {
				return false;
			}
		} finally {
			session.close();
		}

	}
	public boolean selectUserPWD(String userid, String username) {
		SqlSession session = factory.factory.openSession();
		username = te.getEncrypt(username);
		try {
			if(username.equals(session.selectOne("selectname_toid", userid))){
				return true;
			}else {
				return false;
			}
		} finally {
			session.close();
		}
	}
	public boolean modifyUserPWD(String userid, String modipwd) {
		SqlSession session = factory.factory.openSession(true);
		modipwd = te.getEncrypt(modipwd);
		HashMap<String, String> updateMap = new HashMap<String, String>();
		updateMap.put("id", userid);
		updateMap.put("pwd", modipwd);
		try {
			session.update("updateidpwd", updateMap);
			return true;
		} finally {
			session.close();
		}
		
	}
}
