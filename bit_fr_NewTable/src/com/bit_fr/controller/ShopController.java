package com.bit_fr.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit_fr.action.ShopAction;

/**
 * Servlet implementation class ShopController
 */
@WebServlet("*.do")
public class ShopController extends HttpServlet {

	HashMap<String, ShopAction> map = new HashMap<String, ShopAction>();

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShopController() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		try {
			// properties의 실 경로를 찾아, 파일을 읽어오고, 내용을 key value로 나누어 HashMap에 담고, 요청이 들어올 때마다
			// 이를 참조한다.

			String path = config.getServletContext().getRealPath("/WEB-INF");
			FileInputStream fis = new FileInputStream(path + "/bit_fr.properties");
			Properties prop = new Properties();
			prop.load(fis);
			Iterator iter = prop.keySet().iterator();

			// properties의 객체 수 만큼 루프를 돌아가며 HashMap에 추가한다.

			while (iter.hasNext()) {
				String key = (String) iter.next();
				String className = prop.getProperty(key);
				map.put(key, (ShopAction) Class.forName(className).newInstance());
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		proRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		proRequest(request, response);
	}

	private void proRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/") + 1);
		ShopAction action = map.get(cmd);
		String view = action.proRequest(request, response);

		request.setAttribute("viewPage", view);

		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");

		dispatcher.forward(request, response);
	}
}