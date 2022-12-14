package control;

import board.BoardDAO;
import board.BoardDAOInterface;
import chat.ChatRoomDAO;
import chat.ChatRoomDAOInterface;
import member.MemberDAO;
import member.MemberDAOInterface;

public abstract class SetDAO {
	
	protected static ChatRoomDAOInterface chatRoomDAOInstance = ChatRoomDAO.getInstance();
	protected static BoardDAOInterface boardDAOInstance = BoardDAO.getInstance();
	protected static MemberDAOInterface memberDAOInstance = MemberDAO.getInstance();
	
}