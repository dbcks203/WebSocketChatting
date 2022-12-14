package command.memberCommand;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import control.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.MemberBean;
import member.MemberDAO;

public class MemberFindInfoCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException, ServletException {
		// TODO Auto-generated method stub
		MemberBean memberBean = new MemberBean();
		MemberDAO memberDAO = new MemberDAO();
		boolean code;
		if(request.getParameterMap().containsKey("name")) {
			String key =request.getParameter("name");
			code = true;
			memberBean = memberDAO.findMember(key,code);
			request.setAttribute("id",memberBean.getId());
		}
		else if(request.getParameterMap().containsKey("id")){
			String key =request.getParameter("id");
			code = false;
			memberBean = memberDAO.findMember(key,code);
			request.setAttribute("pwd",memberBean.getPwd());
		}
	}
}
