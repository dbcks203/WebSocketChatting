package chat;



public class MessageService{
	private static MessageService instance = null;

	public synchronized static MessageService getInstance() {
		if (instance == null) {
			instance = new MessageService();
		}
		return instance;
	}
}
