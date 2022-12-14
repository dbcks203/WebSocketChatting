package command.boardCommand;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import board.BoardDAO;
import control.Command;
import control.SetDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ArticleDeleteCommand extends SetDAO implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException, SQLException {
		// TODO Auto-generated method stub
		BoardDAO boardDAO = new BoardDAO();
		String target = request.getParameter("seq");
		boardDAO.articleDelete(target);
	}

}
