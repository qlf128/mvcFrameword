package business;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import struts.action.Action;
import struts.action.form.ActionForm;

public class PanDuanAction implements Action{
	@Override
		public String execute(HttpServletRequest request,HttpServletResponse response,ActionForm form,Map<String,String> actinForward){
		return "success";
	}
}
