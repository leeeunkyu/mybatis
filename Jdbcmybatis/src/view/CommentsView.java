package view;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import controller.CommentController;
import dto.Borders;
import dto.Comments;
import oracle.net.aso.b;

/**
 * ����� ����� �����ֱ����� view ������
 * @author kosta
 *
 */
public class CommentsView{
	public int indexNum;
	public String userId;
	Scanner scan;
	CommentController ccontrl;
	DateFormat df;

	/**
	 * ����� ��� ������ �ʱ⼳�� 
	 * @param index_num ��� �������� ������Ų �Խ��ǹ�ȣ
	 * @param userid �ش� �������� ���� ���� ���̵�
	 */
	public CommentsView(int index_num, String userid) {
		scan = new Scanner(System.in);
		ccontrl = new CommentController();
		this.indexNum = index_num;
		this.userId = userid;
	}
	
	/**
	 * ��� ������ �ۼ��ϱ����� �Լ�
	 * @param borders ����� �޸� �Խ���
	 */
	public void contentInsert(Borders borders) {
		df = DateFormat.getDateTimeInstance(0,1 ,Locale.KOREA);
		int result = 0;
		System.out.println("��� ������ �ۼ��ϼ���----------------------------------");
		String content;
		content = scan.next();
		result = ccontrl.insertComment(new Comments(userId, indexNum, content, df.format(new Date())));
		if (result == 1) {
			System.out.println("���������� ����� ��� �Ǿ����ϴ�.");
			ContentMenu(borders);
		}
	}

	/**
	 * ��� �Խ����� ���� �޴�
	 * @param borders
	 */
	public void ContentMenu(Borders borders) {
		String num;
		if (borders == null) {
		}else {
			List<Comments> acom = new ArrayList<Comments>();
			System.out.println(borders);
			System.out.println("�۳���- "+borders.getBord_body());
			System.out.println("��� ���---------------------------------------------");
			acom = ccontrl.selectCommentList(indexNum);
			for (int i = 0; i < acom.size(); i++) {
				System.out.println(acom.get(i).toString());	
			}
			System.out.println("---------------------------------------------------");
			System.out.println("1.�ڷ� ���� 2.����ۼ�");
			String pattern="^[1-2]+";
			while(true) {
				num = scan.next();
				if (Pattern.matches(pattern,num)) {
					switch (Integer.parseInt(num)) {
					case 1:
						System.out.println("�Խ��� �������� �̵�");
						return;
					case 2:
						contentInsert(borders);
						break;
					default:
						break;
					}
				}else{
					System.out.println("������  ��ȣ�� ������ �ּ���.");
				}	
			}
				
		}
	
	}
	
}
