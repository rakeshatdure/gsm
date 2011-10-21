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

	private SortOrder idNoticeOrder;
	private SortOrder nameTypeNotice;
	private SortOrder titleNoticeOrder;
	private SortOrder dateNoticeOrder;
	
	public void sortByIdNotice() {
		nameTypeNotice = SortOrder.unsorted;
        titleNoticeOrder = SortOrder.unsorted;
        dateNoticeOrder = SortOrder.unsorted;
        if (idNoticeOrder.equals(SortOrder.ascending)) {
            setIdNoticeOrder(SortOrder.descending);
        } else {
        	setIdNoticeOrder(SortOrder.ascending);
        }
    }
	
	public void sortByTypeNotice() {
        idNoticeOrder = SortOrder.unsorted;
        titleNoticeOrder = SortOrder.unsorted;
        dateNoticeOrder = SortOrder.unsorted;
        if (nameTypeNotice.equals(SortOrder.ascending)) {
            setTypeNoticeOrder(SortOrder.descending);
        } else {
        	setTypeNoticeOrder(SortOrder.ascending);
        }
    }
	
	public void sortByTitleNotice() {
        idNoticeOrder = SortOrder.unsorted;
        nameTypeNotice = SortOrder.unsorted;
        dateNoticeOrder = SortOrder.unsorted;
        if (titleNoticeOrder.equals(SortOrder.ascending)) {
            setTitleNoticeOrder(SortOrder.descending);
        } else {
        	setTitleNoticeOrder(SortOrder.ascending);
        }
    }
	
	public void sortByDateNotice() {
		nameTypeNotice = SortOrder.unsorted;
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
	public SortOrder getTypeNoticeOrder() {
		return nameTypeNotice;
	}
	public void setTypeNoticeOrder(SortOrder typeNoticeOrder) {
		this.nameTypeNotice = typeNoticeOrder;
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
