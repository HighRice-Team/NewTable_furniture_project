package com.bit_fr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	}
}
