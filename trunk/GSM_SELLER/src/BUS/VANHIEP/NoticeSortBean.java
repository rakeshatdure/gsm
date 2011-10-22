package BUS.VANHIEP;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.richfaces.component.SortOrder;

@ManagedBean (name = "noticesort")
@ViewScoped
public class NoticeSortBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5068260866113659391L;

	private SortOrder idNoticeOrder = SortOrder.unsorted;
	private SortOrder nameTypeNoticeOrder = SortOrder.unsorted;
	private SortOrder titleNoticeOrder = SortOrder.unsorted;
	private SortOrder dateNoticeOrder = SortOrder.unsorted;
	
	public void sortByIdNotice() {
		nameTypeNoticeOrder = SortOrder.unsorted;
        titleNoticeOrder = SortOrder.unsorted;
        dateNoticeOrder = SortOrder.unsorted;
        if (idNoticeOrder.equals(SortOrder.ascending)) {
            setIdNoticeOrder(SortOrder.descending);
        } else {
        	setIdNoticeOrder(SortOrder.ascending);
        }
    }
	
	public void sortByNameTypeNotice() {
        idNoticeOrder = SortOrder.unsorted;
        titleNoticeOrder = SortOrder.unsorted;
        dateNoticeOrder = SortOrder.unsorted;
        if (nameTypeNoticeOrder.equals(SortOrder.ascending)) {
            setNameTypeNoticeOrder(SortOrder.descending);
        } else {
        	setNameTypeNoticeOrder(SortOrder.ascending);
        }
    }
	
	public void sortByTitleNotice() {
        idNoticeOrder = SortOrder.unsorted;
        nameTypeNoticeOrder = SortOrder.unsorted;
        dateNoticeOrder = SortOrder.unsorted;
        if (titleNoticeOrder.equals(SortOrder.ascending)) {
            setTitleNoticeOrder(SortOrder.descending);
        } else {
        	setTitleNoticeOrder(SortOrder.ascending);
        }
    }
	
	public void sortByDateNotice() {
		nameTypeNoticeOrder = SortOrder.unsorted;
        titleNoticeOrder = SortOrder.unsorted;
        idNoticeOrder = SortOrder.unsorted;
        if (dateNoticeOrder.equals(SortOrder.ascending)) {
            setDateNoticeOrder(SortOrder.descending);
        } else {
        	setDateNoticeOrder(SortOrder.ascending);
        }
    }
	
	public SortOrder getIdNoticeOrder() {
		return idNoticeOrder;
	}
	public void setIdNoticeOrder(SortOrder idNoticeOrder) {
		this.idNoticeOrder = idNoticeOrder;
	}
	
	public SortOrder getNameTypeNoticeOrder() {
		return nameTypeNoticeOrder;
	}

	public void setNameTypeNoticeOrder(SortOrder nameTypeNoticeOrder) {
		this.nameTypeNoticeOrder = nameTypeNoticeOrder;
	}

	public SortOrder getTitleNoticeOrder() {
		return titleNoticeOrder;
	}
	public void setTitleNoticeOrder(SortOrder titleNoticeOrder) {
		this.titleNoticeOrder = titleNoticeOrder;
	}
	public SortOrder getDateNoticeOrder() {
		return dateNoticeOrder;
	}
	public void setDateNoticeOrder(SortOrder dateNoticeOrder) {
		this.dateNoticeOrder = dateNoticeOrder;
	}
	
	
}
