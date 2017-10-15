package view;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import controller.BorderController;
import dto.Borders;

/**
 * ����ڰ� ���������� �α����� �Ǿ������ ���̴� view ������
 * @author kosta
 *
 */
public class LoginView {
	Scanner bscan;
	String userid;
	DateFormat df;
	BorderController bcontrol;
	List<Borders> ab;
	LogView lv;
	CommentsView cv;
	public LoginView() {
		
	}
	/**
	 * �α��� �������� ���� �ʱ� ����
	 * @param userid �α��ο� ������ ����� ���̵�
	 */
	public LoginView(String userid) {
		bscan = new Scanner(System.in);
		bcontrol = new BorderController();
		lv = new LogView(userid);
		this.userid = userid;
	}
	/**
	 * �α��� ���������� �Խ����� ����ϱ� ���� �޴�
	 */
	public void BorderMenu() {
		String num = null;
		String pattern="^[1-7]+";

		System.out.println("-------------------------------");
		System.out.println("1.�Խù� �ۼ�");
		System.out.println("2.�Խù� ����Ʈ ����");		
		System.out.println("3.������ �� ����");
		System.out.println("4.���� �ۼ��� �Խù� ����");
		System.out.println("5.���� �ۼ��� �Խù� ����");		
		System.out.println("6.�α� ����");
		System.out.println("7.�α׾ƿ� ");
		System.out.println("-------------------------------");
		num = bscan.next();
		if (Pattern.matches(pattern,num)) {
			choiceBorder(Integer.parseInt(num));
		}else{
			System.out.println("1~-7������ ���ڸ� �Է��� �ּ���.");
			BorderMenu();
		}
	}
	public int indexCount() {
		int index=bcontrol.selectIndex();
		return index;
	}
	
	/**
	 * �Խ��� �޴����� ������ ���� ���� �Խ��� ��� ���
	 * @param num �Խ��� �޴����� ������ ��
	 */
	public void choiceBorder(int num) {
		String head = null;
		String body = null;
		df = DateFormat.getDateTimeInstance(0, 1,Locale.KOREA);
		switch (num) {
		case 1:
			System.out.println("-------------------------------");
			System.out.println("1.�Խù� ������ �Է��ϼ���. ");
			head = bscan.next();
			System.out.println("2.�Խù� ������ �Է��ϼ���. ");
			body = bscan.next();
			System.out.println("-------------------------------");
			bcontrol.add(new Borders(userid, indexCount(), head, body, 0, df.format(new Date())));
			BorderMenu();
			break;
		case 2:
			System.out.println("-------------------------------");
			ab = bcontrol.selectList();
			for (int i = 0; i < ab.size(); i++) {
				System.out.println(ab.get(i).toString());
			}
			System.out.println("-------------------------------");
			BorderMenu();
			break;
		case 3:
			String pattern="^[0-9]*";
			String index_num = null;
			System.out.println("-------------------------------");
			System.out.print("�� ��ȣ�� ������ �ּ���. ");
			index_num = bscan.next();
			if (Pattern.matches(pattern,index_num)) {
				System.out.println(index_num+"�� �Խñ�");
				cv = new CommentsView(Integer.parseInt(index_num),userid);
				cv.ContentMenu(bcontrol.selectOne(Integer.parseInt(index_num)));
			}else{
				System.out.println("������ �� ��ȣ�� ������ �ּ���.");
			}			
			BorderMenu();
			break;
		case 4:
			System.out.println("-------------------------------");
			ab = bcontrol.selectUserBord(userid);
			for (int i = 0; i < ab.size(); i++) {
				System.out.println(ab.get(i).toString());
			}
			System.out.println("-------------------------------");
			BorderMenu();
			break;
		case 5:
			String delete_num = null;
			String delete_pwd = null;
			String pattern1="^[0-9]*";
		
			System.out.println("-------------------------------");
			System.out.print("�� ��ȣ�� ������ �ּ���. ");
			delete_num = bscan.next();
			if (Pattern.matches(pattern1,delete_num)) {
				System.out.println(delete_num+"�� �Խñ� ����");
				System.out.println("-------------------------------");
				System.out.println("ȸ���� ��й�ȣ�� �Է��� �ּ���.");
				delete_pwd = bscan.next();
				System.out.println("-------------------------------");
				if (bcontrol.deleteOne(Integer.parseInt(delete_num),delete_pwd)) {
					System.out.println("������ �Ϸ� �Ǿ����ϴ�.");
					BorderMenu();
				}
				System.out.println("��й�ȣ�� Ȯ���� �ּ���.");				
			}else{
				System.out.println("�ùٸ� �۹�ȣ�� �Է��� �ּ���.");
			}
			BorderMenu();
			break;
		case 6:
			lv.LogMenu();
			BorderMenu();
			break;
		case 7:
			System.out.println("���������� �α׾ƿ��� �Ǿ����ϴ�.");
			
			break;
		default:
			break;
		}
	}

}
