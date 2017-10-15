package Util;


import java.util.ArrayList;




import dto.Users;

/**
 *사용자 데이터를 엑셀에 암호화해 저장시키기 위한 클래스 
 * @author kosta
 *
 */
public class TransferExcel {

	private static final int KEY_SIZE = 128;
    private static final int ITERATION_COUNT = 10000;
    private static final String IV = "F27D5C9927726BCEFE7510B1BDD3D137";
    private static final String SALT = "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55";
    private static final String PASSPHRASE = "passPhrase passPhrase aes encoding algorithm";

    public String getEncrypt(String plaintext) {
    	   AesUtil util = new AesUtil(KEY_SIZE, ITERATION_COUNT);
           String encrypt = util.encrypt(SALT, IV, PASSPHRASE, plaintext);
           String decrypt = util.decrypt(SALT, IV, PASSPHRASE, encrypt);
           return encrypt;
    }
    
	public Users transfer(Users user) {
	
			
			AesUtil util = new AesUtil(KEY_SIZE, ITERATION_COUNT);
			ArrayList<String> arr2 = new ArrayList<>();
			ArrayList<String> arrsha = new ArrayList<>();
			arr2.add(user.getUser_id());
			arr2.add(user.getUser_pwd());
			arr2.add(user.getUser_name());
			arr2.add(user.getGender());
			arr2.add(user.getUser_signup());			
			
			for (int i = 0; i < arr2.size(); i++) {
				String encrypt = util.encrypt(SALT, IV, PASSPHRASE, arr2.get(i));
				String decrypt = util.decrypt(SALT, IV, PASSPHRASE, encrypt);
				if(i==0) {
					arrsha.add(arr2.get(i));
				}
				else {
					arrsha.add(encrypt);
				}
			}
			user.setUser_pwd(arr2.get(0));
			user.setUser_pwd(arrsha.get(1));
			user.setUser_name(arrsha.get(2));
			user.setGender(arrsha.get(3));
			user.setUser_signup(arrsha.get(4));							
	
		return user;
	
}
}
