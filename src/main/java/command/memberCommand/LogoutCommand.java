package command.memberCommand;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import control.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("isLogon", null);
		session.setAttribute("id", null);
		session.setAttribute("login.pwd", null);
	}

}
