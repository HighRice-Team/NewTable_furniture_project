package com.bit_fr.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit_fr.dao.ProductDao;
import com.bit_fr.vo.ProductVo;

public class IndexAction implements ShopAction {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int productMax = 16;
		int pageNum = 1;
		int min = 0;
		int max = 0;
		if (request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("")) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		int endNum = pageNum * productMax;
		int startNum = endNum - (productMax - 1);

		String category = request.getParameter("category");
		String quality = request.getParameter("quality");
		String sql = "select * from (select product_id, condition, product_name, category, quality, price, main_img, rownum e from (select product_id, product_name, condition, main_img, category, quality, price from product where condition='물품게시'";

		if (request.getParameter("min") != null && Integer.parseInt(request.getParameter("min")) != 0) {
			min = Integer.parseInt(request.getParameter("min"));
			sql += " and price>=" + min;
		}
		if (request.getParameter("max") != null && Integer.parseInt(request.getParameter("max")) != 0) {
			max = Integer.parseInt(request.getParameter("max"));
			sql += " and price<=" + max;
		}
		if (category != null && !category.equals(""))
			sql += " and category='" + category + "'";

		if (quality != null && !quality.equals(""))
			sql += " and quality='" + quality + "'";

		sql += " order by product_id desc))";
		ProductDao dao = ProductDao.getInstance();
		ArrayList<ProductVo> list = dao.customizeProduct(sql);
		request.setAttribute("len", list.size());
		int pageMax = list.size() / productMax;
		if (list.size() % productMax != 0)
			pageMax++;

		sql += "where e>=" + startNum + " and e<=" + endNum;
		list = dao.customizeProduct(sql);

		request.setAttribute("list", list);
		request.setAttribute("category", category);
		request.setAttribute("min", min);
		request.setAttribute("max", max);
		request.setAttribute("quality", quality);

		request.setAttribute("pageMax", pageMax);

		return "index.jsp";
	}
}
