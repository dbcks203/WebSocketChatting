package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO implements BoardDAOInterface{

	Connection conn;
	PreparedStatement pstmt;
	DataSource dataFactory;
	private static BoardDAO instance = null;
	
	public BoardDAO() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/WebSocketChatting");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static BoardDAO getInstance() {
		if (instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	// AddOk 서블릿이 DTO를 줄테니, insert를 해주세요.
	public int articleInsert(BoardDTO dto) {
		try {
			conn = dataFactory.getConnection();
			String query = "insert into tblBoards (seq, userid, subject, content, regdate, readcount, tag)"
					+ " values (NEXTVAL(seqBoards), ?, ?, ?, default, default, ?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getTag());

			return pstmt.executeUpdate(); // 성공시 1 실패시 0

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public List<BoardDTO> listArticles() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from tblboards";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setSeq(rs.getString("seq"));
				dto.setUserid(rs.getString("userid"));
				dto.setSubject(rs.getString("subject"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setRegdate(rs.getString("regdate"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	// View 서블릿이 글번호를 줄테니 레코드 내용 전부를 DTO에 담아서 돌려주세요!
	public BoardDTO getBoardDTO(String seq) {
		BoardDTO dto = new BoardDTO();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from tblBoards where seq= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(seq));
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setUserid(rs.getString("userid"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setTag(rs.getString("tag"));
			}
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	// View 서블릿이 글번호를 줄테니 조회수를 +1 해주세요!
	public void updateReadCount(String seq) {
		try {
			conn = dataFactory.getConnection();
			String query = "update tblBoards set readcount = readcount + 1 where seq=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, seq);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// EditOk 서블릿이 수정할 DTO를 줄테니 update 해주세요!
	public int articleEdit(BoardDTO boardDTO) {
		try {
			conn = dataFactory.getConnection(); 
			String query = "update tblBoards set subject=?, content=? where seq=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, boardDTO.getSubject());
			pstmt.setString(2, boardDTO.getContent());
			pstmt.setString(3, boardDTO.getSeq());
			System.out.println(boardDTO.getSubject()+boardDTO.getContent()+boardDTO.getSeq());
			return pstmt.executeUpdate(); // 성공시 1 실패시 0
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// DelOk 서블릿이 글번호를 줄테니 글을 삭제해주세요!
	public int articleDelete(String seq){
		try {
			conn = dataFactory.getConnection(); 
			String query = "delete from tblBoards where seq=?";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, seq);

			return pstmt.executeUpdate(); // 성공시 1 실패시 0

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	/*
	 * // AddComment 서블릿이 댓글을 작성해달라고 요청 public int addComment(CommentDTO dto) {
	 * 
	 * try {
	 * 
	 * String sql = "insert into tblComment (seq, id, content, regdate, pseq)" +
	 * " values (seqBoards.nextVal, ?, ?, default, ?)";
	 * 
	 * pstmt = conn.prepareStatement(sql);
	 * 
	 * pstmt.setString(1, dto.getId()); // id? pstmt.setString(2, dto.getContent());
	 * pstmt.setString(3, dto.getPseq());
	 * 
	 * return pstmt.executeUpdate(); // 성공시 1 실패시 0
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return 0; }
	 */

	/*
	 * // View 서블릿이 댓글 목록 달라고 요청 public ArrayList<CommentDTO> listComment(String
	 * seq) {
	 * 
	 * try {
	 * 
	 * // 부모글 번호를 조건으로 받기 String sql =
	 * "select c.*, (select name from tblUsers where id = c.id) as name " +
	 * "from tblComment c where pseq = ? order by seq asc";
	 * 
	 * pstmt = conn.prepareStatement(sql); pstmt.setString(1, seq);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * ArrayList<CommentDTO> clist = new ArrayList<CommentDTO>();
	 * 
	 * while ( rs.next() ) {
	 * 
	 * CommentDTO dto = new CommentDTO();
	 * 
	 * dto.setSeq(rs.getString("seq")); dto.setContent(rs.getString("content"));
	 * dto.setId(rs.getString("id")); dto.setRegdate(rs.getString("regdate"));
	 * dto.setPseq(rs.getString("pseq")); dto.setName(rs.getString("name"));
	 * 
	 * clist.add(dto);
	 * 
	 * }
	 * 
	 * return clist;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return null; }
	 */

	// DelComment 서블릿이 글번호를 줄테니 댓글을 삭제해주세요.

	/*
	 * public int delComment(String seq) {
	 * 
	 * try {
	 * 
	 * String sql = "delete from tblComment where seq = ?";
	 * 
	 * pstmt = conn.prepareStatement(sql);
	 * 
	 * pstmt.setString(1, seq); // id?
	 * 
	 * return pstmt.executeUpdate(); // 성공시 1 실패시 0
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return 0; }
	 */
	// DelOk 서블릿이 글번호를 줄테니 글번호를 부모로 하는 모든 댓글을 삭제해주세요
	/*
	 * public void delAllComment(String seq) {
	 * 
	 * try {
	 * 
	 * String sql = "delete from tblComment where pseq = ?";
	 * 
	 * pstmt = conn.prepareStatement(sql); pstmt.setString(1, seq); // id?
	 * pstmt.executeUpdate(); // 성공시 1 실패시 0
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 
	// List 서블릿이 총 게시물 수 알려달라고 요청

	public int getTotalCount(HashMap<String, String> map) {

		try {

			String where = "";

			if (map.get("isSearch").equals("y")) {

				if (map.get("column").equals("all")) {
					where = String.format(" where subject like '%%%s%%' or content like '%%%s%%' ", map.get("search"),
							map.get("search"));
				} else {
					where = String.format(" where %s like '%%%s%%' ", map.get("column"), map.get("search"));
				}

			}

			String sql = String.format("select count(*) as cnt from tblBoards %s", where);

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("cnt");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
*/
	// AddOk 서블릿이 가장 큰 thread값을 알려달라고 요청
	/*
	 * public int getMaxThread() {
	 * 
	 * try {
	 * 
	 * // nullvalue = nvl 사용해서 쿼리작성 // -> 안하면 그냥 null // -> 하면 1000 String sql =
	 * "select nvl(max(thread), 0) + 1000 as thread from tblBoards";
	 * 
	 * stat = conn.createStatement(); rs = stat.executeQuery(sql);
	 * 
	 * if ( rs.next() ) { return rs.getInt("thread"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return 0; }
	 */

	// AddOk 서블릿이 답변 글쓰기에 필요한 업무를 위임

	/*
	 * public void updateThread(int parentThread, int previousThread) {
	 * 
	 * try {
	 * 
	 * // a. 현존 모든게시물의 thread값을 대상으로 현재 작성 중인 답변글인 부모글의 thread값보다 작고, 이전 새글의
	 * thread값보다 큰 thread를 찾아서 모두 -1 한다.
	 * 
	 * String sql =
	 * "update tblBoards set thread = thread - 1 where thread > ? and thread < ?";
	 * pstmt = conn.prepareStatement(sql);
	 * 
	 * pstmt.setInt(1, previousThread); pstmt.setInt(2, parentThread);
	 * 
	 * pstmt.executeUpdate();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */
}
