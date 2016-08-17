package struts.action.form;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

public class FullForm {
	public FullForm(){
		
	}
	
	public static ActionForm full(String formpath,HttpServletRequest request){
		ActionForm form = null;
		try{
			Class clazz = Class.forName(formpath);
			form = (ActionForm)clazz.newInstance();
			Field[] field_ar = clazz.getDeclaredFields();
			for(Field f:field_ar){
				f.setAccessible(true);
				f.set(form, request.getParameter(f.getName()));
				f.setAccessible(false);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return form;
	}
}
