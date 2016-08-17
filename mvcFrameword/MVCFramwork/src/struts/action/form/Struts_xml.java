package struts.action.form;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class Struts_xml {
	public static Map<String,XmlBean> struts_xml(String xmlPath) throws Exception{
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(new File(xmlPath));
		Element root = document.getRootElement();
		Map<String,XmlBean> rmap = new HashMap<String ,XmlBean>();
		Element actionroot = root.getChild("action-mapping");
		List<Element> actio = actionroot.getChildren();
		String path = "";
		for(Element e:actio){
			XmlBean action = new XmlBean();
			String name = e.getAttributeValue("name");
			action.setBeanName(name);
			Element actionform = root.getChild("formbeans");
			List<Element> form = actionform.getChildren();
			for(Element ex:form){
				if(name.equals(ex.getAttributeValue("name"))){
					String formclass=ex.getAttributeValue("class");
					action.setFormClass(formclass);
					break;
				}
			}
			path=e.getAttributeValue("path");
			action.setPath(path);
			String type = e.getAttributeValue("type");
			action.setActionClass(type);
			List<Element> forward = e.getChildren();
			Map<String,String> map = new HashMap<String,String>();
			for(Element x:forward){
				String fname = x.getAttributeValue("name");
				String fvalue = x.getAttributeValue("value");
				map.put(fname, fvalue);
			}
			action.setActionForward(map);
			rmap.put(path, action);
		}
		return rmap;
		
	}
}
