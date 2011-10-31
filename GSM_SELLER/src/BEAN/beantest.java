package BEAN;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "beantest")
@SessionScoped
public class beantest {
	private boolean on=false;

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public void eventClick() {
		if (on) {
			System.out.println("on");
			setOn(false);
		} else {
			System.out.println("off");
			setOn(true);
		}
	}
}
