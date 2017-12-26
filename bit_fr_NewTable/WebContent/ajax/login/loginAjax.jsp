
<%@page import="com.bit_fr.vo.MemberVo"%>
<%@page import="com.bit_fr.dao.MemberDao"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String member_id = request.getParameter("member_id");
	String pwd = request.getParameter("pwd");
	String str = "";

	MemberDao dao = MemberDao.getInstance();

	if (member_id != null && !member_id.equals("")) {

		MemberVo v = dao.getDetail_MemberInfo(member_id);

		if (v.getMember_id() != null) // 입력받은 member_id가 DB에 존재하는지 검사. 
		{
			boolean compare = v.getPwd().equals(pwd); // member_id가 DB에 존재 할 때, member_id의 pwd갑과 입력받은 pwd값을 비교.
			if (!compare) { //비밀번호가 일치하지 않는 경우.
				str = "비밀번호가 일치하지 않습니다.";
			} else { //비밀번호가 일치하는 경우.

				String grade = v.getGrade();

				session.setAttribute("id", member_id);
				session.setAttribute("pwd", pwd);
				session.setAttribute("grade", grade);
			}
		} else { // 입력받은 member_id가 DB에 존재하지 않을 경우.					
			str = "아이디가 존재하지 않습니다.";
		}
	}
	str = "{\"str\":\"" + str + "\"}";
%>
<%=str%>