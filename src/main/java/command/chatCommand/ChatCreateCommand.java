package command.chatCommand;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import chat.ChatRoomDAO;
import chat.ChatRoomDTO;
import control.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChatCreateCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		// TODO Auto-generated method stub
		ChatRoomDAO chatRoomDAO = new ChatRoomDAO();
		String chatName =request.getParameter("chatName");
		chatRoomDAO.CreateRoom(chatName);
	}
}
