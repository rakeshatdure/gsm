package UTIL;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ConvertCurrency {

	public static String getCurrentUSDRate() {
		String result = null;
		try {
			String uri = "http://www.vietcombank.com.vn/exchangerates/ExrateXML.aspx";

			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");

			InputStream xml = connection.getInputStream();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml);
			doc.getDocumentElement().normalize();

			NodeList nodeExrateList = doc.getElementsByTagName("ExrateList");
			Element nodeExrateListFirst = (Element) nodeExrateList.item(0);
			NodeList nodeExrate = nodeExrateListFirst
					.getElementsByTagName("Exrate");

			for (int index = 0; index < nodeExrate.getLength(); index++) {
				Element nodeExrateItem = (Element) nodeExrate.item(index);
				String currentCode = nodeExrateItem
						.getAttribute("CurrencyCode");
				// System.out.println(index);
				if ("USD".equals(currentCode)) {
					String transfer = nodeExrateItem.getAttribute("Transfer");
					result = transfer;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	public static String getCurrentKRWRatePerVND() {
		String result = null;
		try {
			String uri = "http://www.vietcombank.com.vn/exchangerates/ExrateXML.aspx";

			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");

			InputStream xml = connection.getInputStream();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml);
			doc.getDocumentElement().normalize();

			NodeList nodeExrateList = doc.getElementsByTagName("ExrateList");
			Element nodeExrateListFirst = (Element) nodeExrateList.item(0);
			NodeList nodeExrate = nodeExrateListFirst
					.getElementsByTagName("Exrate");

			for (int index = 0; index < nodeExrate.getLength(); index++) {
				Element nodeExrateItem = (Element) nodeExrate.item(index);
				String currentCode = nodeExrateItem
						.getAttribute("CurrencyCode");
				// System.out.println(index);
				if ("KRW".equals(currentCode)) {
					String transfer = nodeExrateItem.getAttribute("Transfer");
					result = transfer;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public static float convertVND_USD(float VNDAmount) {
		String currentUSDRate = getCurrentUSDRate();
		// System.out.println(currentUSDRate);
		if (currentUSDRate != null) {
			return VNDAmount / (Float.parseFloat(currentUSDRate));
		}
		return 0;
	}

	public static String[] getKRW_USD_Rate() {
		String[] result = { "0", "0" };
		try {
			String uri = "http://www.ecb.int/stats/eurofxref/eurofxref-daily.xml";

			URL url = new URL(uri);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/xml");

			InputStream xml = connection.getInputStream();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml);
			doc.getDocumentElement().normalize();

			NodeList nodeRoot = doc.getElementsByTagName("Cube");
			// System.out.println(nodeRoot.getLength());
			Element nodeCubeFirst = (Element) nodeRoot.item(1);
			// System.out.println(nodeCubeFirst.getAttribute("time"));
			NodeList nodeCube = nodeCubeFirst.getElementsByTagName("Cube");
			String usdRate = "", krwRate = "";
			for (int index = 0; index < nodeCube.getLength(); index++) {
				Element nodeCubeElement = (Element) nodeCube.item(index);
				String currentCode = nodeCubeElement.getAttribute("currency");
				// System.out.println(index);
				if ("USD".equals(currentCode)) {
					usdRate = nodeCubeElement.getAttribute("rate");
					result[0] = usdRate;
				}
				if ("KRW".equals(currentCode)) {
					krwRate = nodeCubeElement.getAttribute("rate");
					result[1] = krwRate;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static float convertKRWtoUSD(float won) {
		String[] array = getKRW_USD_Rate();
		float usdRate = Float.parseFloat(array[0]), kwrRate = Float
				.parseFloat(array[1]), result = 0;
		result = (usdRate * won) / kwrRate;
		//System.out.println(usdRate);
		//System.out.println(kwrRate);
		return result;

	}
	
	public static int convertUSDtoVND(float usd){
		float usdRate=Float.parseFloat(getCurrentUSDRate());
		float convertVND=usdRate*usd;
		//System.out.println("convert: "+convertVND);
		float result= ResourcesDefault.Round(convertVND, 0);
		//System.out.println("result: "+result);
		return Float.valueOf(result).intValue();
	}
	
	public static int convertKRWtoVND(float krw){
		float krwRate=Float.parseFloat(getCurrentKRWRatePerVND());
		float convertVND=krwRate*krw;
		//System.out.println("convert: "+convertVND);
		float result= ResourcesDefault.Round(convertVND, 0);
		//System.out.println("result: "+result);
		return Float.valueOf(result).intValue();
	}

	public static void main(String argv[]) {
		System.out.println(convertVND_USD(20));

	}
}
