package Util;

import java.util.Random;

/**
 * 사용자 비밀번호 찾기시 보안 코드를 위한 클래스
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
