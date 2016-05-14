package com.yinlei.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
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
		}

	}

	/**
	 * ��ӿͻ���Ϣ
	 * 
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			StringBuffer 	sb = new StringBuffer(hobbys[0]);
			for (int i = 1; i < hobbys.length; i++) {
				sb.append("," + hobbys[i]);
			}
			c.setHobby(sb.toString());
		}
		
		//����service�����ҵ���߼�
		boolean flag = cs.add(c);
		if (flag) {
			//˵����ӳɹ�
			//�����²�ѯ���ݿ�  ��ȡ���ݺ���ת��
			try {
				listAll(request, response);
			} catch (IOException e) {
				e.printStackTrace();
				request.setAttribute("error", "���ʧ��");
				request.getRequestDispatcher("/add.jsp").forward(request, response);
				
			}
			
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
		request.setAttribute("list", list);

		// ҳ���ض�����ҳ��
		response.sendRedirect(request.getContextPath() + "/list.jsp");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
