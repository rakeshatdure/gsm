package DAO;

import java.util.List;

import POJO.Template;

public class TemplateDAO extends HibernateDAO {

	public static boolean insert(Template template, String lang) {
		return HibernateDAO.insert(template, lang);
	}

	public static boolean update(Template template, String lang) {
		return HibernateDAO.update(template, lang);
	}

	public static boolean delete(Template template, String lang) {
		return HibernateDAO.delete(template, lang);
	}
	
	@SuppressWarnings("unchecked")
	public static Template getTemplate(Template template, String lang) {
		List<Template> list = HibernateDAO.getList("from Template", lang);
		if (list != null && list.size() > 0) {
			list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<Template> getListTemplate(Template template, String lang) {
		List<Template> list = HibernateDAO.getList("from Template", lang);

		return list;
	}
}
