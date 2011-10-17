package BUS;

import java.util.List;

import DAO.TemplateDAO;
import POJO.Template;

public class TemplateBUS {
	public static boolean insert(Template template, String lang) {
		return TemplateDAO.insert(template, lang);
	}

	public static boolean update(Template template, String lang) {
		return TemplateDAO.update(template, lang);
	}

	public static boolean delete(Template template, String lang) {
		return TemplateDAO.delete(template, lang);
	}
	
	public static Template getTemplate(Template template, String lang) {
		
		return TemplateDAO.getTemplate(template, lang);
	}

	public static List<Template> getListTemplate(Template template, String lang) {
		
		return TemplateDAO.getListTemplate(template, lang);
	}
}
