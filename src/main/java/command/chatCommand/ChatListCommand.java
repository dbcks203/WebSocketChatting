package command.chatCommand;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import chat.ChatRoomDAO;
import control.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChatListCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		// TODO Auto-generated method stub
		ChatRoomDAO chatRoomDAO = new ChatRoomDAO();
		request.setAttribute("roomList", chatRoomDAO.listRooms());
	}

}
