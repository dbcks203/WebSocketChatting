package command.chatCommand;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chat.ChatRoomDAO;
import control.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChatEnterCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		
		HttpSession session = request.getSession();
		
		System.out.println(request.getParameter("seq"));
		System.out.println(request.getParameter("title"));
		boolean isRoom =true;
		session.setAttribute("isRoom", isRoom);
		
		request.setAttribute("roomNo", request.getParameter("seq"));
		request.setAttribute("title",request.getParameter("title"));
		
		
		System.out.println(request.getParameter("seq"));
		
		System.out.println(request.getParameter("title"));
		
		
	}
}
