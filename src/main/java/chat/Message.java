package chat;

public class Message {

	private String username;
	private String message;
	private String groupId;

	public Message() {

	}

	public Message(String username, String message, String groupId) {
		this.username = username;
		this.message = message;
		this.groupId = groupId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
