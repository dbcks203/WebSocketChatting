package command.memberCommand;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import control.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberBean;
import member.MemberDAO;

public class MemberInfoCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException, ServletException {
		HttpSession session = request.getSession();
		MemberBean memberBean = new MemberBean();
		MemberDAO memberDAO = new MemberDAO();
		
		
		String userid=(String) session.getAttribute("id");
		memberBean = memberDAO.getMember(userid);
		request.setAttribute("memberBean",memberBean);
	}
	
	
}
