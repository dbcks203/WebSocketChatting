package command.boardCommand;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import board.BoardDAO;
import board.BoardDTO;
import control.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArticleWriteCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		// TODO Auto-generated method stub
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		
		String userid = (String) request.getParameter("userid");
		String subject = (String) request.getParameter("subject");
		String content = (String) request.getParameter("content");
		String tag = (String) request.getParameter("tag");
		boardDTO.setUserid(userid);
		boardDTO.setContent(content);
		boardDTO.setSubject(subject);
		boardDTO.setTag(tag);
		boardDAO.articleInsert(boardDTO);
	}

}
