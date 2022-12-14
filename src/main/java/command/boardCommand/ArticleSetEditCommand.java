package command.boardCommand;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import board.BoardDAO;
import control.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArticleSetEditCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		
		BoardDAO boardDAO = new BoardDAO();
		String target = request.getParameter("seq");
		request.setAttribute("boardDTO", boardDAO.getBoardDTO(target));
	}
}
