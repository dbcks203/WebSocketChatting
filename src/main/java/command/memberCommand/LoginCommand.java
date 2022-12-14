package command.memberCommand;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberBean;
import member.MemberDAO;
import control.Command;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String user_id = request.getParameter("userid");
		String user_pwd = request.getParameter("password");

		MemberBean memberbean = new MemberBean();
		memberbean.setId(user_id);
		memberbean.setPwd(user_pwd);
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(memberbean);
		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", "loginned");
			session.setAttribute("id", user_id);
			session.setAttribute("login.pwd", user_pwd);
		}

	}

}
