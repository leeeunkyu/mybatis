package Util;

import java.util.Random;

/**
 * ����� ��й�ȣ ã��� ���� �ڵ带 ���� Ŭ����
 * @author kosta
 *
 */
public class SecretString {
	public StringBuffer secret() {
		StringBuffer sb=new StringBuffer();
		Random r = new Random();

		for (int j = 0; j < 4; j++) {
			sb.append((char)(r.nextInt(26)+65));
		}
		return sb;
	}
}
