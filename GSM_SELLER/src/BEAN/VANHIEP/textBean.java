package BEAN.VANHIEP;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean 
@SessionScoped
public class textBean{

	private String text;
	private int size;
	private String text1;
	private int size1;
	private String text2;
	private int size2;

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		if(text == null){
			size = 0;
		} else {
			size = text.length();
		}
		return size;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}

	public int getSize1() {
		if(text1 == null){
			size1 = 0;
		} else {
			size1 = text1.length();
		}
		return size1;
	}

	public void setSize1(int size1) {
		this.size1 = size1;
	}

	public String getText2() {
		return text2;
	}

	public void setText2(String text2) {
		this.text2 = text2;
	}

	public int getSize2() {
		if(text2 == null){
			size2 = 0;
		} else {
			size2 = text2.length();
		}
		return size2;
	}

	public void setSize2(int size2) {
		this.size2 = size2;
	}
	
	
}
