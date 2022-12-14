package control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public interface Command {

	void execute(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException, SQLException, ServletException;

}