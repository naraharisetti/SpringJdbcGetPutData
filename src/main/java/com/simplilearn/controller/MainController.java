package com.simplilearn.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;

import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.simplilearn.dao.EProductDao;
import com.simplilearn.entity.EProductEntity;

@Controller
public class MainController {

	@Autowired
	private EProductDao dao;

	@GetMapping("/getdata")
	public String getData(ModelMap map) {
		List<EProductEntity> list= dao.getAllProducts();
		map.addAttribute("list",list);
		return "display";
	}
	@GetMapping("/details")
	public String getById(ModelMap map,HttpServletRequest request)
	{
		String id= request.getParameter("id");
		long uid= Long.parseLong(id);
		EProductEntity entity=dao.getProductById(uid);
		if(entity!=null)
		{
			map.addAttribute("product",entity);
			return "details";
		}
		else
		{
			return "error";
		}
	}

	@GetMapping("/putdata")
	public String putData(ModelMap map,HttpServletRequest request, HttpServletResponse response) throws IOException {

		System.out.println("I am runing put data");



		String id= request.getParameter("id");
		long uid= Long.parseLong(id);
		EProductEntity entity=dao.getProductById(uid);

		String name= request.getParameter("name");

		BigDecimal price = new BigDecimal(0);
		try {
			Long p1 = Long.parseLong(request.getParameter("price"));
			price = new BigDecimal(p1);
		} catch(Exception e) {

		}

		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());

		if (name==null || name.equals("")) {
			name = "Not Applicable";

		}
		try {
		entity.setName(name);
		entity.setPrice(price);
		entity.setDateAdded(timeStamp);
		}catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html");
			PrintWriter printWriter = response.getWriter();
			printWriter.print("<html>");
			printWriter.print("<body>");
			printWriter.print("<h2>Invalid Data</h2>");
			printWriter.print("</body>");
			printWriter.print("</html>");
			printWriter.close();

		}

		dao.updateProduct(entity);

		if(entity!=null)
		{
			map.addAttribute("product",entity);
			return "details";
		}
		else
		{
			return "error";
		}
	}
}
