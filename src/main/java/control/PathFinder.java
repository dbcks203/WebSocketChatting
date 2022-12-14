package control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.SendResult;

import java.io.IOException;
import java.sql.SQLException;

import command.memberCommand.*;
import command.boardCommand.*;
import command.chatCommand.*;

@WebServlet("*.zan")
public class PathFinder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PathFinder() {
        super();
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			actionDo(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			actionDo(request, response);
		} catch (ServletException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		request.setCharacterEncoding("UTF-8");
		
		Command command = null;	
		String viewPage = null;
		String dispatchPath = null;	
		
		String uri = request.getRequestURI();
		String []tokens = uri.split("/");
		String com = tokens[tokens.length-1];
		
		
		switch(com) {
		case "login.zan":
			command = new LoginCommand();
			command.execute(request, response);
			dispatchPath="/";
			viewPage = "index.jsp";
			break;
		case "join.zan":
			command = new JoinCommand();
			command.execute(request, response);
			dispatchPath="/";
			viewPage = "index.jsp";
			break;
		case "mypage.zan":
			command = new MemberInfoCommand();
			command.execute(request, response);
			dispatchPath="/jsp/member/";
			viewPage = "memberInfo.jsp";
			break;
		case "logout.zan":
			command = new LogoutCommand();
			command.execute(request, response);
			dispatchPath="/";
			viewPage = "index.jsp";
			break;
		case "memberdrop.zan":
			command = new MemberDropCommand();
			command.execute(request, response);
			dispatchPath="/";
			viewPage = "index.jsp";
			break;
		case "findinfo.zan":
			command = new MemberFindInfoCommand();
			command.execute(request, response);
			dispatchPath="/jsp/member/";
			viewPage = "findinfo_result.jsp";
			break;
			
			
			
		case "chatlist.zan":
			command = new ChatListCommand();
			command.execute(request, response);
			dispatchPath="/jsp/chat/";
			viewPage = "chatLobby.jsp";
			break;
		case "chatcreate.zan":
			command = new ChatCreateCommand();
			command.execute(request, response);
			dispatchPath="/jsp/chat/";
			viewPage = "chatLobby.jsp";
			break;
		case "chatinfo.zan":
			command = new ChatInfoCommand();
			command.execute(request, response);
			dispatchPath="/jsp/chat/";
			viewPage = "chatInfo.jsp";
			break;
		case "chatenter.zan":
			command = new ChatEnterCommand();
			command.execute(request, response);
			dispatchPath="/jsp/chat/";
			viewPage = "groupChat.jsp";
			break;	
			
			
			
			
		case "articlelist.zan":
			command = new ArticleListCommand();
			command.execute(request, response);
			dispatchPath="/jsp/board/";
			viewPage = "boardLobby.jsp";
			break;
		case "articleview.zan":
			command = new ArticleViewCommand();
			command.execute(request, response);
			dispatchPath="/jsp/board/";
			viewPage = "articleView.jsp";
			break;
			
		case "articlewrite.zan":
			command = new ArticleWriteCommand();
			command.execute(request, response);
			dispatchPath="/jsp/board/";
			viewPage = "articlelist.zan";
			break;
		case "articleedit.zan":
			command = new ArticleEditCommand();
			command.execute(request, response);
			dispatchPath="/jsp/board/";
			viewPage = "articleview.zan?seq="+request.getParameter("seq");
			break;
		case "articledelete.zan":
			command = new ArticleDeleteCommand();
			command.execute(request, response);
			dispatchPath="/jsp/board/";
			viewPage = "articlelist.zan";
			break;
		case "articlesetedit.zan":
			command = new ArticleSetEditCommand();
			command.execute(request, response);
			dispatchPath="/jsp/board/";
			viewPage = "articleSetEdit.jsp";
			break;
		}
		
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(dispatchPath + viewPage);
			dispatcher.forward(request, response);
		}
	}
}
