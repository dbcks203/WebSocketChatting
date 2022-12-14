package command.boardCommand;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import board.BoardDAO;
import board.BoardDTO;
import control.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArticleEditCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		// TODO Auto-generated method stub
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO boardDTO = new BoardDTO();
		
		String seq = (String) request.getParameter("seq");
		String subject = (String) request.getParameter("subject");
		String content = (String) request.getParameter("content");
		
		boardDTO.setSeq(seq);
		boardDTO.setContent(content);
		boardDTO.setSubject(subject);
		boardDAO.articleEdit(boardDTO);
	}

}
