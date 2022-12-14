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

public class MemberDropCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
	
		MemberDAO memberDAO = new MemberDAO();
		
		
		String userid=(String) session.getAttribute("id");
		if(memberDAO.dropMember(userid)==1) {
			session.setAttribute("isLogon", null);
			session.setAttribute("id", null);
			session.setAttribute("login.pwd", null);
			System.out.println("Drop"+userid);
		}
		else
			System.out.println("fail Drop"+userid);
	}

}
