package struts.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import struts.action.form.ActionForm;
import struts.action.form.FullForm;
import struts.action.form.XmlBean;

public class ActionServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		//读取请求头
		String path = this.getPath(request.getServletPath());
		Map<String,XmlBean> map =(Map<String,XmlBean>)this.getServletContext().getAttribute("struts");
		XmlBean xml = map.get(path);
		
		String formclass = xml.getFormClass();
		ActionForm form = FullForm.full(formclass,request);
		String actiontype = xml.getActionClass();
		Action action = null;
		String returnStr = "";
		String url = "";
		try{
			Class clazz = Class.forName(actiontype);
			action = (Action)clazz.newInstance();
			returnStr = action.execute(request, response, form, xml.getActionForward());
		}catch(Exception e){
			System.out.println("严重：控制器异常");
		}
		url = xml.getActionForward().get(returnStr);
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
		throws IOException,ServletException {
		this.doGet(request, response);
	}
	
	private String getPath(String servletpath){
		String path = servletpath.split("\\.")[0];
		return path;
	}
}
