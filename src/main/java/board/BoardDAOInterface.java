package board;

import java.util.List;

public interface BoardDAOInterface {
	
	public int articleInsert(BoardDTO dto);

	public List<BoardDTO> listArticles(); 
	
	public BoardDTO getBoardDTO(String seq); 

	public void updateReadCount(String seq);

	public int articleEdit(BoardDTO boardDTO) ;
	
	public int articleDelete(String seq);
}