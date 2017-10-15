package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.LogController;
import dto.Logs;
/**
 * ����� �α׸� �����ֱ����� view������
 * @author kosta
 *
 */
public class LogView {
	Scanner scan;
	LogController lcontrl;
	String userid;
	public LogView() {
	}

	public LogView(String userid) {
		lcontrl = new LogController();
		scan = new Scanner(System.in);
		this.userid = userid;
	}
	/**
	 * �α� ����� �������� �޴�
	 */
	public void LogMenu() {
		int num = 0;
		System.out.println("------------�α�ȭ��-----------");
		System.out.println("1. �αױ�Ϻ���");
		System.out.println("2. �ڷΰ���");
		System.out.println("-----------------------------");
		num = scan.nextInt();
		switch (num) {
		case 1:
			report();
			break;
		case 2:
			return;
		default:
			break;
		}
	}
	/**
	 * �������� �α� ��ϵ�
	 */
	public void report() {
		ArrayList<Logs> al = new ArrayList<Logs>();
		al=lcontrl.selectLog(userid);
		System.out.println("-----------------------------");
		System.out.println(al);
		for (int i = 0; i < al.size(); i++) {
			al.get(i).toString();
		}
		System.out.println("-----------------------------");
	}
}
