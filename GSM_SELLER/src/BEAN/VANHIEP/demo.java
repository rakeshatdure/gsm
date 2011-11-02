package BEAN.VANHIEP;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;

@ManagedBean (name = "demo")
public class demo {

	private String demo;
	private String out;

	public void setDemo(String demo) {
		this.demo = demo;
	}

	public String getDemo() {
		return demo;
	}
	
	public void getText(ValueChangeEvent e) {
		demo = e.getNewValue().toString();
	}

	public void setOut(String out) {
		this.out = out;
	}

	public String getOut() {
		return out;
	}
}
