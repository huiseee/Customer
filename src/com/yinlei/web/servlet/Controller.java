package com.yinlei.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.taglibs.standard.tag.el.sql.UpdateTag;

import com.yinlei.bean.Customer;
import com.yinlei.service.CustomerService;
import com.yinlei.service.CustomerServiceImpl;
import com.yinlei.utils.WebTools;
import com.yinlei.utils.WebUtils;
import com.yinlei.web.formbean.CustomerFormBean;

/**
 * Servlet implementation class Controller
 * <li>控制请求的转向(流程控制)
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

	CustomerService cs = new CustomerServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 拿到页面传递的数据
		String op = request.getParameter("op");

		if ("all".equals(op)) {
			listAll(request, response);
		} else if ("add".equals(op)) {
			addCustomer(request, response);
		} else if ("toupdate".equals(op)) {
			toUpdate(request, response);
		} else if ("update".equals(op)) {
			update(request, response);
		} else if ("delete".equals(op)) {
			delete(request, response);
		} else if ("deleteMore".equals(op)) {
			deleteMore(request, response);
		}

	}

	/**
	 * 删除多条数据
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void deleteMore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		// 拿到页面的数据
		String ids = request.getParameter("ids");

		// 由于ids后面多了一个逗号，记得要去掉
		ids = ids.substring(0, ids.length() - 1);
		// 拆分字符串
		if (ids.length() == 0) {
			return;
		}
		String[] strIds = ids.split(",");
		for (int i = 0; i < strIds.length; i++) {
			// 调用service层进行删除
			//String id = URLDecoder.decode(strIds[i], "UTF-8");
			System.out.println(strIds[i]);
			cs.delete(strIds[i]);
		}

		// 转向主页面
		listAll(request, response);

	}

	/**
	 * 删除用户的信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 拿到页面传过来的id
		String id = request.getParameter("id");
		//id = URLDecoder.decode(id, "UTF-8");
		// 调用service层完成业务逻辑
		System.out.println(id);
		boolean flag = cs.delete(id);
		if (!flag) {
			// 说明删除不成功
			request.getSession().setAttribute("error", "删除失败");
		}
		listAll(request, response);

	}

	// 修改客户信息
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 封装页面的数据
		CustomerFormBean cfb = WebUtils.fillFormBean(CustomerFormBean.class, request);

		// 检测数据(省略)
		// 拷贝数据到一个JavaBean中
		Customer c = new Customer();
		// 由于时间是date类型,所以首先注册一个时间转换器
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		// 拷贝数据
		try {
			BeanUtils.copyProperties(c, cfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 处理数据
		// 其次由于爱好类型不同,所以bean不会膀臂拷贝数据，需要手动拷贝
		// 拿到页面的爱好数组
		String[] hobby = cfb.getHobby();
		if (hobby != null && hobby.length > 0) {
			StringBuffer sb = new StringBuffer(hobby[0]);
			for (int i = 1; i < hobby.length; i++) {
				sb.append("," + hobby[i]);
			}
			c.setHobby(sb.toString());
		}

		// 调用service层完成业务逻辑
		boolean flag = cs.update(c);

		if (flag) {
			// 说明修改成功了，转向主页面
			// 先重新查询数据库，拿取数据后在转向
			listAll(request, response);
		} else {
			// 修改失败
			request.setAttribute("error", "修改失败");
			request.getRequestDispatcher("/update.jsp").forward(request, response);
		}
	}

	/**
	 * 转向修改页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 拿到页面传过来的id
		String id = request.getParameter("id");
		// 调用service层完成业务逻辑(查找用户)
		Customer c = cs.findCustomerById(id);

		// 将对象存入request对象后转发到修改页面
		request.setAttribute("customer", c);

		request.getRequestDispatcher("/update.jsp").forward(request, response);

	}

	/**
	 * 添加客户信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void addCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 封装页面的数据
		CustomerFormBean bean = WebUtils.fillFormBean(CustomerFormBean.class, request);

		// 检测数据

		// copy数据到javabean中
		Customer c = new Customer();
		// 时间是date类型(注意转换) 首先要注册一个事件转换器
		ConvertUtils.register(new DateLocaleConverter(), Date.class);

		// copy数据
		try {
			BeanUtils.copyProperties(c, bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 处理数据
		// customer中没有主键id 需要创建一个id
		c.setId(WebTools.createNewId());
		// 其次由于爱好类型不同，需要手动copy
		// 拿到页面的爱好数组
		String[] hobbys = bean.getHobby();

		if (hobbys != null && hobbys.length > 0) {
			StringBuffer sb = new StringBuffer(hobbys[0]);
			for (int i = 1; i < hobbys.length; i++) {
				sb.append("," + hobbys[i]);
			}
			c.setHobby(sb.toString());
		}

		// 调用service层完成业务逻辑
		boolean flag = cs.add(c);

		if (flag) {
			// 说明添加成功
			// 先重新查询数据库 拿取数据后在转向
			listAll(request, response);
		} else {
			// 添加失败
			request.setAttribute("error", "添加失败");
			request.getRequestDispatcher("/add.jsp").forward(request, response);
		}

	}

	/**
	 * 显示所有的数据
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void listAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 拿到所有的数据
		List<Customer> list = cs.getAllCustomer();

		// 将数据存储到session里面 采用请求重定向
		request.getSession().setAttribute("list", list);

		// 页面重定向到主页面
		response.sendRedirect(request.getContextPath() + "/list.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
