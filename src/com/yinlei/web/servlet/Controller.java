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
 * <li>���������ת��(���̿���)
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {

	CustomerService cs = new CustomerServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// �õ�ҳ�洫�ݵ�����
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
	 * ɾ����������
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void deleteMore(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		// �õ�ҳ�������
		String ids = request.getParameter("ids");

		// ����ids�������һ�����ţ��ǵ�Ҫȥ��
		ids = ids.substring(0, ids.length() - 1);
		// ����ַ���
		if (ids.length() == 0) {
			return;
		}
		String[] strIds = ids.split(",");
		for (int i = 0; i < strIds.length; i++) {
			// ����service�����ɾ��
			//String id = URLDecoder.decode(strIds[i], "UTF-8");
			System.out.println(strIds[i]);
			cs.delete(strIds[i]);
		}

		// ת����ҳ��
		listAll(request, response);

	}

	/**
	 * ɾ���û�����Ϣ
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// �õ�ҳ�洫������id
		String id = request.getParameter("id");
		//id = URLDecoder.decode(id, "UTF-8");
		// ����service�����ҵ���߼�
		System.out.println(id);
		boolean flag = cs.delete(id);
		if (!flag) {
			// ˵��ɾ�����ɹ�
			request.getSession().setAttribute("error", "ɾ��ʧ��");
		}
		listAll(request, response);

	}

	// �޸Ŀͻ���Ϣ
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��װҳ�������
		CustomerFormBean cfb = WebUtils.fillFormBean(CustomerFormBean.class, request);

		// �������(ʡ��)
		// �������ݵ�һ��JavaBean��
		Customer c = new Customer();
		// ����ʱ����date����,��������ע��һ��ʱ��ת����
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		// ��������
		try {
			BeanUtils.copyProperties(c, cfb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ��������
		// ������ڰ������Ͳ�ͬ,����bean�����ۿ������ݣ���Ҫ�ֶ�����
		// �õ�ҳ��İ�������
		String[] hobby = cfb.getHobby();
		if (hobby != null && hobby.length > 0) {
			StringBuffer sb = new StringBuffer(hobby[0]);
			for (int i = 1; i < hobby.length; i++) {
				sb.append("," + hobby[i]);
			}
			c.setHobby(sb.toString());
		}

		// ����service�����ҵ���߼�
		boolean flag = cs.update(c);

		if (flag) {
			// ˵���޸ĳɹ��ˣ�ת����ҳ��
			// �����²�ѯ���ݿ⣬��ȡ���ݺ���ת��
			listAll(request, response);
		} else {
			// �޸�ʧ��
			request.setAttribute("error", "�޸�ʧ��");
			request.getRequestDispatcher("/update.jsp").forward(request, response);
		}
	}

	/**
	 * ת���޸�ҳ��
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �õ�ҳ�洫������id
		String id = request.getParameter("id");
		// ����service�����ҵ���߼�(�����û�)
		Customer c = cs.findCustomerById(id);

		// ���������request�����ת�����޸�ҳ��
		request.setAttribute("customer", c);

		request.getRequestDispatcher("/update.jsp").forward(request, response);

	}

	/**
	 * ��ӿͻ���Ϣ
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void addCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��װҳ�������
		CustomerFormBean bean = WebUtils.fillFormBean(CustomerFormBean.class, request);

		// �������

		// copy���ݵ�javabean��
		Customer c = new Customer();
		// ʱ����date����(ע��ת��) ����Ҫע��һ���¼�ת����
		ConvertUtils.register(new DateLocaleConverter(), Date.class);

		// copy����
		try {
			BeanUtils.copyProperties(c, bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ��������
		// customer��û������id ��Ҫ����һ��id
		c.setId(WebTools.createNewId());
		// ������ڰ������Ͳ�ͬ����Ҫ�ֶ�copy
		// �õ�ҳ��İ�������
		String[] hobbys = bean.getHobby();

		if (hobbys != null && hobbys.length > 0) {
			StringBuffer sb = new StringBuffer(hobbys[0]);
			for (int i = 1; i < hobbys.length; i++) {
				sb.append("," + hobbys[i]);
			}
			c.setHobby(sb.toString());
		}

		// ����service�����ҵ���߼�
		boolean flag = cs.add(c);

		if (flag) {
			// ˵����ӳɹ�
			// �����²�ѯ���ݿ� ��ȡ���ݺ���ת��
			listAll(request, response);
		} else {
			// ���ʧ��
			request.setAttribute("error", "���ʧ��");
			request.getRequestDispatcher("/add.jsp").forward(request, response);
		}

	}

	/**
	 * ��ʾ���е�����
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void listAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// �õ����е�����
		List<Customer> list = cs.getAllCustomer();

		// �����ݴ洢��session���� ���������ض���
		request.getSession().setAttribute("list", list);

		// ҳ���ض�����ҳ��
		response.sendRedirect(request.getContextPath() + "/list.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
