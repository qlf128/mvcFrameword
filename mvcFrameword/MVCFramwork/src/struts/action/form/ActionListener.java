package struts.action.form;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ActionListener implements ServletContextListener{
	@Override
	public void contextDestroyed(ServletContextEvent arg0){
		
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0){
		ServletContext context = arg0.getServletContext();
		String xmlpath = arg0.getServletContext().getInitParameter("struts-config");
		String tomcatpath = arg0.getServletContext().getRealPath("\\");
		try{
			Map<String,XmlBean> map = Struts_xml.struts_xml(tomcatpath+xmlpath);
			context.setAttribute("struts", map);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("信息：系统已经加载完成!");
	}
}
