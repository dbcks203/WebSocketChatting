package command.memberCommand;

import java.io.IOException;
import java.sql.SQLException;

import control.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.MemberBean;
import member.MemberDAO;

public class JoinCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		MemberDAO memberDAO = new MemberDAO();
		MemberBean memberBean = new MemberBean(
				request.getParameter("userid"), 
				request.getParameter("password"),
				request.getParameter("name"),
				request.getParameter("sex"),
				request.getParameter("email"),
				request.getParameter("address"), 
				request.getParameter("phone")
				);
		memberDAO.insertMember(memberBean);
		// response.setContentType("text/html; charset=utf-8");
	}

}
