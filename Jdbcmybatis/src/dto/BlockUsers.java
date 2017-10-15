package dto;

/**
 * 블럭처리된 유저를 정의할 블락유저 테이블
 * @author kosta
 *
 */
public class BlockUsers {
	private String userId;
	private String userStatus;
	private String blockday;
	
	public BlockUsers() {

	}
	
	public BlockUsers(String userId, String userStatus, String blockday) {
		super();
		this.userId = userId;
		this.userStatus = userStatus;
		this.blockday = blockday;
	}
	
	public String getBlockday() {
		return blockday;
	}
	public void setBlockday(String blockday) {
		this.blockday = blockday;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
		
}
