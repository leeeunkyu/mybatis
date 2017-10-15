package view;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import Util.SecretString;
import Util.TransferExcel;
import controller.UserController;
import dto.Users;

/**
 * ����� �ʱ�ȭ���� ����ϴ� view 
 * @author kosta
 *
 */
public class InitView {
	Scanner scan;
	DateFormat df;
	UserController ucontrol;
	LoginView login;
	TransferExcel te;
	SecretString secret = new SecretString();
	public InitView() {
		te = new TransferExcel();
		ucontrol = new UserController();
		scan = new Scanner(System.in);
		initmenu();
	}
	public static void main(String[] args) {
		InitView iv = new InitView();
	}
	/**
	 * �ʱ� ���� �޴� 
	 * 1~6���� �޴��� ������ ������ ���� ���� ������ ó��
	 */
	private void initmenu() {
		String choiceNum = null;
		System.out.println("------------------------------");
		System.out.println("1. ȸ������ :");
		System.out.println("2. �α��� :");
		System.out.println("3. ���̵� ã�� :");
		System.out.println("4. ��й�ȣã�� :");	
		System.out.println("5. ȸ�� Ż��");
		System.out.println("6. �ý��� ���� :");			
		System.out.println("------------------------------");
		String pattern="^[1-6]+";
		choiceNum = scan.next();
		if (Pattern.matches(pattern,choiceNum)) {
			choice(Integer.parseInt(choiceNum));
		}else{
			System.out.println("1~-6������ ���ڸ� �Է��� �ּ���.");
			initmenu();
		}
	}
	/**
	 * init �޴����� ������ ���� �޾� �ش� ���� ���� ������ ó��
	 * @param num
	 */
	private void choice(int num) {
		String table_name = null;
		String y_or_n = null;
		String userid = null;
		String userpwd = null;
		String username = null;
		String gender = null;
		String signday = null;
		
		switch (num) {
		case 1:
			int result = 0;
			System.out.println("----------ȸ������ �޴�------------");
			System.out.print("���̵� �Է� �ϼ���.");
			while(true) {
				userid = scan.next();
				if(ucontrol.check(userid)) {
					System.err.println("�ߺ��Ǵ� ���̵� �ֽ��ϴ�.\n�ٸ� ���̵� �Է��ϼ���.");
				}else {
					System.out.println("�ߺ��Ǵ� ���̵� �����ϴ�. ����ϼŵ� �����ϴ�.");
					break;
				}
			}
			System.out.print("��й�ȣ�� �Է� �ϼ���.");
			userpwd = scan.next();
			System.out.print("�̸��� �Է� �ϼ���.");
			username = scan.next();
			System.out.print("����� ������ �Է� �ϼ���(��:m ��:f Ȥ��:o).");
			gender = scan.next();
			df = DateFormat.getDateTimeInstance(0, 1,Locale.KOREA);
			result = ucontrol.add(te.transfer(new Users(userid,userpwd,username,gender,df.format(new Date()))));
			if(result == 0) {
				System.err.println("ȸ������ �������� ������ �־����ϴ�. �ٽ� �õ��� �ֽñ� �ٶ��ϴ�.");
			}
			initmenu();
			break;
		case 2:
			int logincount = 0;
			while(true) {
				System.out.println("���̵� �Է��ϼ���.");
				userid = scan.next();
				System.out.println("��й�ȣ�� �Է��ϼ���.");
				userpwd = scan.next();
				if(ucontrol.login(userid,userpwd)) {
					System.out.println("�������� �α���");
					login = new LoginView(userid);
					login.BorderMenu();
					break;
				}else {
					System.out.println("���̵� ��й�ȣ�� Ȯ���ϼ���.");
					logincount++;
					if (logincount>5) {
						System.out.println("�α��� ���Ƚ���� 5ȸ�̻� �ʰ��ؼ� �ʱ�ȭ������ ���ư��ϴ�.");
						break;
					}
				}
			}
			initmenu();
			break;
		case 3:
			int namecount = 0;
			while(true) {
				System.out.println("�̸��� �Է��ϼ���.");
				String id;
				username = scan.next();
				id = ucontrol.selectID(username);
				if (id != null) {
					System.out.println("ȸ������ ���̵��:  "+id+ "�Դϴ�.");
					break;
				}else {
					System.out.println("�ùٸ� �̸��� �Է��ϼ���.");
					namecount++;
					if (namecount > 5) {
						System.out.println("���̵� ã�� ���Ƚ���� 5ȸ�̻� �ʰ��ؼ� �ʱ�ȭ������ ���ư��ϴ�.");
						break;
					}
				}
			}
			initmenu();
			break;	
		case 4:
			System.out.println("���̵� �Է��ϼ���");
			userid = scan.next();
			System.out.println("�̸��� �Է� �ϼ���");
			username = scan.next();
			if(ucontrol.selectPWD(userid,username)){
				System.out.println("���ȹ��ڸ� �Է����ּ���");
				String str = secret.secret().toString();
				String tstr = null;
				System.out.println(str);
				tstr = scan.next();
				if(tstr.equals(str)) {
					System.out.println("������ ��й�ȣ�� �Է��� �ּ���");
					String modipwd = null;
					modipwd = scan.next();
					System.out.println("����� ��й�ȣ:  "+modipwd);
					ucontrol.modifyPWD(userid,modipwd);
				}
				
			}else{
				System.out.println("���̵� �̸��� Ȯ���� �ּ���");
			}
			initmenu();
			break;
		case 5:
			int deletecount = 0;
			while(true) {
				System.out.print("���̵� �Է��ϼ���.");
				String deleteid;
				deleteid = scan.next();
				System.out.print("��й�ȣ�� �Է��ϼ���.");
				String deletepwd;
				deletepwd = scan.next();
				if(ucontrol.deleteUser(deleteid,deletepwd)) {	
					System.out.println("���������� ���� �Ǿ����ϴ�.");			
					break;
				}else {
					System.out.println("���̵� ��й�ȣ�� Ȯ���� �ּ���");
					deletecount++;
					if (deletecount > 5) {
						System.out.println("���� �õ� Ƚ�� 5ȸ�� �ʰ��ؼ� �ʱ�ȭ������ �̵��մϴ�.");
						break;
					}
				}
			}
			initmenu();
			break;
		case 6:
			System.exit(1);
			break;
		default:
			System.out.println("�ùٸ� �Է°��� �Է��ϼ���.");
			initmenu();

			break;
		}
	}
}
