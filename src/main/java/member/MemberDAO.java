package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAOInterface{
	Connection conn;
	PreparedStatement pstmt;
	DataSource dataFactory;
	private static MemberDAO instance = null;
	
	
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			Context envContext = (Context) context.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/WebSocketChatting");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized static MemberDAO getInstance() {
		if (instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	public List<MemberBean> listMembers() {
		List<MemberBean> list = new ArrayList<>();
		try {
			// connDB();
			conn = dataFactory.getConnection();
			String query = "select * from t_member ";
			System.out.println("prepareStatememt: " + query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberBean member = new MemberBean(

						rs.getString("userid"), rs.getString("pwd"), rs.getString("name"), rs.getString("sex"),
						rs.getString("email"), rs.getString("address"), rs.getString("phone"));

				System.out.println(member);
				list.add(member);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public int insertMember(MemberBean memberBean) throws SQLException {
		try {
			// connDB();
			conn = dataFactory.getConnection();
			String query = "insert into member (userid, pwd, name,sex,email,address,phone) values (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, memberBean.getId());
			pstmt.setString(2, memberBean.getPwd());
			pstmt.setString(3, memberBean.getName());
			pstmt.setString(4, memberBean.getSex());
			pstmt.setString(5, memberBean.getEmail());
			pstmt.setString(6, memberBean.getAddress());
			pstmt.setString(7, memberBean.getPhone());
			return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e; //해당 함수를 호출한 부분으로 예외를 던진다 
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	public int dropMember(String userid) throws SQLException {
		try {
			conn = dataFactory.getConnection();
			String query = "delete from member where userid=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);

			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public MemberBean findMember(String key, Boolean code) throws SQLException {
		MemberBean member = new MemberBean();

		try {
			conn = dataFactory.getConnection();
			if(code)
			{
				String query = "select * from member where name= ?";
				pstmt = conn.prepareStatement(query);
			}
			else {
				String query = "select * from member where userid= ?";
				pstmt = conn.prepareStatement(query);
			}
			pstmt.setString(1, key);
			
			
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				member.setId(rs.getString("userid"));
				member.setPwd(rs.getString("pwd"));
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	public MemberBean getMember(String userid) throws SQLException {
		MemberBean member = new MemberBean();

		try {
			conn = dataFactory.getConnection();
			String query = "select * from member where userid= ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				member.setId(rs.getString("userid"));
				member.setPwd(rs.getString("pwd"));
				member.setName(rs.getString("name"));
				member.setSex(rs.getString("sex"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
				member.setPhone(rs.getString("phone"));
				member.setJoinDate(rs.getString("createdate"));
			}

			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}

	public boolean isExisted(MemberBean member) {
		boolean result = false;
		String id = member.getId();
		String pwd = member.getPwd();
		try {
			conn = dataFactory.getConnection();
			String query = "select case count(*) when 1 then 'true' else 'false' end as result from member";
			query += " where userid=? and pwd=?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result = Boolean.parseBoolean(rs.getString("result"));
			System.out.println("result=" + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
