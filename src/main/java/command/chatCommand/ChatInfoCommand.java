package command.chatCommand;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import chat.ChatRoomDAO;
import chat.ChatRoomDTO;
import control.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChatInfoCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		// TODO Auto-generated method stub
	
		ChatRoomDAO chatRoomDAO = new ChatRoomDAO();
		String target = request.getParameter("seq");
		ChatRoomDTO chatRoomDTO = chatRoomDAO.getChatRoomDTO(target);
		request.setAttribute("chatRoomDTO", chatRoomDTO);
		
		
		String clients = chatRoomDTO.getClientList();
		StringTokenizer stk=new StringTokenizer(clients,",{}");		
		ArrayList<String> clientsArray = new ArrayList<String>();
		
		while (stk.hasMoreTokens()){
		clientsArray.add(stk.nextToken());
		}
		request.setAttribute("clients", clientsArray);
	}

}
