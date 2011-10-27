package UTIL;


import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;

import org.apache.commons.lang.StringUtils;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.lexer.Lexer;

@ManagedBean
public class HtmlStringUtils {
	private String primeContent;

	public HtmlStringUtils(String content) {
		super();
		this.primeContent = content;
	}

	public String parseHtmlToText() {
		String str = primeContent.replaceAll("\\<.*?>", "");
		str = str.replaceAll("&nsbp;", " ");
		return StringUtils.trimToEmpty(str);
	}

	public String parseHtmlToTextNoEntity() {
		String str = primeContent.replaceAll("\\<.*?>", "");
		str = str.replaceAll("&.*?;", " ");
		return StringUtils.trimToEmpty(str);
	}

	public HtmlStringUtils subStringHtmlString(int lenght) {
		HtmlStringUtils utils = new HtmlStringUtils(this.getPrimeContent());

		if (parseHtmlToTextNoEntity().length() > lenght) {
			Parser parser = Parser.createParser(utils.getPrimeContent(),
					"UTF-8");
			try {
				utils.setPrimeContent(parseNode(parser.getLexer(), lenght, ""));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return utils;
	}

	public HtmlStringUtils toNoTag() {
		HtmlStringUtils utils = new HtmlStringUtils(this.getPrimeContent());

		utils.setPrimeContent(Pattern.compile("\\<.*?>",
				Pattern.CASE_INSENSITIVE).matcher(utils.getPrimeContent())
				.replaceAll(""));
		return utils;
	}

	public HtmlStringUtils toNoImageTag() {
		HtmlStringUtils utils = new HtmlStringUtils(this.getPrimeContent());

		utils.setPrimeContent(Pattern.compile("</?\\s?img.*?/?>",
				Pattern.CASE_INSENSITIVE).matcher(utils.getPrimeContent())
				.replaceAll(""));
		return utils;
	}

	public HtmlStringUtils toNoBrTag() {
		HtmlStringUtils utils = new HtmlStringUtils(this.getPrimeContent());

		utils.setPrimeContent(Pattern.compile("</?\\s?br.*?/?>",
				Pattern.CASE_INSENSITIVE).matcher(utils.getPrimeContent())
				.replaceAll(""));
		return utils;
	}

	public HtmlStringUtils toNoDivTag() {
		HtmlStringUtils utils = new HtmlStringUtils(this.getPrimeContent());

		utils.setPrimeContent(Pattern.compile("</?\\s?div.*?/?>",
				Pattern.CASE_INSENSITIVE).matcher(utils.getPrimeContent())
				.replaceAll(""));
		return utils;
	}

	private String parseNode(Lexer lexer, int lenght, String str)
			throws Exception {
		Node node = lexer.nextNode();
		if (lenght > 0 && node != null) {

			String text = node.toPlainTextString();
			if (StringUtils.isNotBlank(text)) {
				int realLenght = text.replaceAll("&.*?;", " ").length();
				lenght = lenght - text.length() + text.length() - realLenght;
			}

			if (lenght > 0) {
				str += node.toHtml();
			} else {
				int finalLenght = text.length() + lenght;
				for (int i = finalLenght; i > 0; i--) {
					if (text.charAt(i) == ' ') {
						break;
					}

					if (text.charAt(i) == ';') {
						finalLenght = i + 1;
						break;
					}

					if (text.charAt(i) == '&') {
						finalLenght = i;
						break;
					}
				}

				str += text.substring(0, finalLenght) + "...";
			}

			return parseNode(lexer, lenght, str);
		}

		if (node != null) {
			if (StringUtils
					.isBlank(node.toHtml().replaceAll("</\\s*\\w+>", ""))) {
				str += node.toHtml();
			}
		}

		return str;
	}

	public String getPrimeContent() {
		return primeContent;
	}

	public void setPrimeContent(String primeContent) {
		this.primeContent = primeContent;
	}

	public static HtmlStringUtils parse(String primeContent) {
		return new HtmlStringUtils(primeContent);
	}

}