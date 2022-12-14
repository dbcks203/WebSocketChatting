package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ChatRoomDAO implements ChatRoomDAOInterface{

	Connection conn;
	PreparedStatement pstmt;
	DataSource dataFactory;
	private static ChatRoomDAO instance = null;
	
	public ChatRoomDAO() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/WebSocketChatting");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static ChatRoomDAO getInstance() {
		if (instance == null) {
			instance = new ChatRoomDAO();
		}
		return instance;
	}
	
	public List<ChatRoomDTO> listRooms() {
		List<ChatRoomDTO> list = new ArrayList<>();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from CHATROOM"; 
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ChatRoomDTO chatRoomDTO = new ChatRoomDTO(
						rs.getString("seq"), 
						rs.getString("title"),
						rs.getInt("clientCount"), 
						rs.getString("clients"),
						rs.getString("createDate"));
				list.add(chatRoomDTO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int CreateRoom(String chatName) {
		try {
			conn = dataFactory.getConnection();
			String query = "insert into chatroom (seq, title)"
					+ " values (NEXTVAL(seqChat), ?, default, default, default)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, chatName);

			return pstmt.executeUpdate(); // 성공시 1 실패시 0

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	
	public ChatRoomDTO getChatRoomDTO(String seq) throws SQLException {
		ChatRoomDTO dto = new ChatRoomDTO();
		
		try {
			conn = dataFactory.getConnection();
			String query = "select * from chatroom where seq= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(seq));
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setSeq(rs.getString("seq"));
				dto.setTitle(rs.getString("title"));
				dto.setClientCount(rs.getInt("clientCount"));
				dto.setClientList(rs.getString("clients"));
				dto.setCreateDate(rs.getString("createDate"));
			}
			System.out.println(dto.getSeq());
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	
	public void updateClient(String seq,List<String> clients) {
		try {
			conn = dataFactory.getConnection();
			String query = "update chatroom set clientCount = clientCount + 1,clients=? where seq=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, clients.toString());
			pstmt.setString(2, seq);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/*
	 * public int insert(ChatRoomDTO dto) throws SQLException { int cnt = 0;
	 * 
	 * String title = dto.getTitle(); String summary = dto.getSummary(); int price =
	 * dto.getPrice();
	 * 
	 * int uid; String[] generatedCols = { "bk_uid" };
	 * 
	 * try { pstmt = conn.prepareStatement(D.SQL_BOOK_INSERT, generatedCols);
	 * pstmt.setString(1, title); pstmt.setString(2, summary); pstmt.setInt(3,
	 * price); cnt = pstmt.executeUpdate();
	 * 
	 * if (cnt > 0) { rs = pstmt.getGeneratedKeys(); if (rs.next()) { uid =
	 * rs.getInt(1); dto.setUid(uid); } } } finally { close(); }
	 * 
	 * return cnt; }
	 * 
	 * 
	 * 
	 * public List<ChatRoomDTO> selectByUid(int uid) throws SQLException {
	 * List<ChatRoomDTO> list = null;
	 * 
	 * try { pstmt = conn.prepareStatement(D.SQL_BOOK_SELECT_BY_UID);
	 * pstmt.setInt(1, uid); rs = pstmt.executeQuery(); list = buildList(rs); }
	 * finally { close(); }
	 * 
	 * return list; }
	 * 
	 * public int update(int uid, String title, String summary, int price) throws
	 * SQLException { int cnt = 0;
	 * 
	 * try { pstmt = conn.prepareStatement(D.SQL_BOOK_UPDATE); pstmt.setString(1,
	 * title); pstmt.setString(2, summary); pstmt.setInt(3, price); pstmt.setInt(4,
	 * uid); cnt = pstmt.executeUpdate(); } finally { close(); }
	 * 
	 * return cnt; }
	 * 
	 * public int deleteByUid(int uid) throws SQLException { int cnt = 0;
	 * 
	 * try { pstmt = conn.prepareStatement(D.SQL_BOOK_DELETE); pstmt.setInt(1, uid);
	 * cnt = pstmt.executeUpdate(); } finally { close(); }
	 * 
	 * return cnt; }
	 */
}