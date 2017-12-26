package com.bit_fr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bit_fr.db.ConnectionProvider;
import com.bit_fr.vo.MemberVo;

public class MemberDao {
	private static MemberDao dao;

	static {
		dao = new MemberDao();
	}

	private MemberDao() {
	};

	public static MemberDao getInstance() {
		return dao;
	}
	// 모든 회원들의 정보를 가져옴.
	public ArrayList<MemberVo> select_AllMemberInfo(){
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		
		try {
			
			String sql = "";
			
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery(sql);
			
			while(rs.next()) {
				MemberVo m = new MemberVo();
				
				m.setMember_id(rs.getString(1));
				m.setPwd(rs.getString(2));
				m.setName(rs.getString(3));
				m.setTel(rs.getString(4));
				m.setJumin(rs.getString(5));
				m.setPwd_q(rs.getString(6));
				m.setPwd_a(rs.getString(7));
				m.setAddress(rs.getString(8));
				m.setAddress_detail(rs.getString(9));
				m.setPayback(rs.getInt(10));
				m.setAccount_no(rs.getString(11));
				m.setBank(rs.getString(12));
				m.setBalance(rs.getInt(13));
				m.setGrade(rs.getString(14));
				
				list.add(m);
			}
			
			ConnectionProvider.close(conn, pstmt, rs);
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		return list;
	}

	// Member_id를 매개변수로 받아, 해당 member의 정보를 Vo로 반환한다.
	public MemberVo getDetail_MemberInfo(String member_id) {
		MemberVo v = new MemberVo();

		try {

			String sql = "SELECT * FROM member WHERE member_id=?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				v.setMember_id(rs.getString(1));
				v.setPwd(rs.getString(2));
				v.setName(rs.getString(3));
				v.setTel(rs.getString(4));
				v.setJumin(rs.getString(5));
				v.setPwd_q(rs.getString(6));
				v.setPwd_a(rs.getString(7));
				v.setAddress(rs.getString(8));
				v.setAddress_detail(rs.getString(9));
				v.setPayback(rs.getInt(10));
				v.setAccount_no(rs.getString(11));
				v.setBank(rs.getString(12));
				v.setBalance(rs.getInt(13));
				v.setGrade(rs.getString(14));
			}

			ConnectionProvider.close(conn, pstmt, rs);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return v;
	}// getDetail_MemberInfo end

	/*
	 * 회원가입 일반회원과 관리자 구분없이 아이디 생성이 이루어짐. 구분은 속성 값 grade 로 구별한다.
	 */
	public int insert_JoinMember(MemberVo m) {
		int re = -1;
		try {

			String sql = "insert into member values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getMember_id());
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getTel());
			pstmt.setString(5, m.getJumin());
			pstmt.setString(6, m.getPwd_q());
			pstmt.setString(7, m.getPwd_a());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getAddress_detail());
			// 회원가입시 payback 의 잔액은 0으로 최기화합니다.
			pstmt.setInt(10, 0);
			pstmt.setString(11, m.getAccount_no());
			pstmt.setString(12, m.getBank());
			// 회원의 계좌의 잔액이 들어갈곳
			pstmt.setInt(13, m.getBalance());
			pstmt.setString(14, m.getGrade());

			re = pstmt.executeUpdate();

			ConnectionProvider.close(conn, pstmt, null);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return re;
	}// insert_JoinMember end

	// 회원가입시 아이디 중복체크
	public boolean check_DuplicateMemberID(String member_id) {
		boolean check = false;

		try {

			String sql = "select member_id from member where member_id = ?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member_id);

			ResultSet rs = pstmt.executeQuery();
			check = rs.next();

			ConnectionProvider.close(conn, pstmt, rs);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return check;
	}// check_DuplicateMemberID end

	// 회원아이디 찾기
	public MemberVo search_Member_ID(String name, String jumin) {
		MemberVo m = new MemberVo();

		try {

			String sql = "select member_id from member where name=? and jumin=?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, jumin);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				m.setMember_id(rs.getString("member_id"));
			}

			ConnectionProvider.close(conn, pstmt, rs);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return m;

	}// search_Member_ID end

	// 회원계정의 비밀번호 초기화.
	public int update_ResetMemberPWD(String member_id) {
		int re = -1;

		try {

			String sql = "update member set pwd='0000' where member_id=?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, member_id);

			re = pstmt.executeUpdate();

			ConnectionProvider.close(conn, pstmt, null);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return re;
	}// update_ResetMemberPWD end

	// 회원 계정의 비밀번호 업데이트.
	public int update_UpdateMemberPWD(String member_id, String pwd) {
		int re = -1;

		try {

			String sql = "update member set pwd=? where member_id=?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, pwd);
			pstmt.setString(2, member_id);

			re = pstmt.executeUpdate();

			ConnectionProvider.close(conn, pstmt, null);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return re;
	}// update_UpdateMemberPWD end

	// 모든 회원의 수.( 관리자 포함)
	public int getNumber_AllMember() {
		int member_NUM = -1;

		try {

			String sql = "select count(member_id) from member";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery(sql);

			if (rs.next()) {
				member_NUM = rs.getInt(1);
			}

			ConnectionProvider.close(conn, pstmt, rs);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return member_NUM;
	}// getNumber_AllMember end

	// 회원정보수정
	public int update_UpdateMemberInfo(MemberVo m) {
		int re = -1;

		try {

			String sql = "update member set pwd=?,pwd_q=?,pwd_a=?,tel=? where member_id=?";

			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, m.getPwd());
			pstmt.setString(2, m.getPwd_q());
			pstmt.setString(3, m.getPwd_a());
			pstmt.setString(4, m.getTel());
			pstmt.setString(5, m.getMember_id());

			re = pstmt.executeUpdate();

			ConnectionProvider.close(conn, pstmt, null);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return re;
	}//update_UpdateMemberInfo end
	
	

}
