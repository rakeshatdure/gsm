package BUS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.richfaces.component.UIExtendedDataTable;

import POJO.NoticeView;

@ManagedBean (name = "NoticeSelection")
@ViewScoped
public class NoticeTableSelectionBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -619881878045300680L;

	private Collection<Object> selection;
    @ManagedProperty(value = "#{NoticeBean.listNotice}")
    private List<NoticeView> noticeItems;
    private List<NoticeView> selectionItems = new ArrayList<NoticeView>();
    
    public void selectionListener(AjaxBehaviorEvent event) {
        UIExtendedDataTable dataTable = (UIExtendedDataTable) event.getComponent();
        Object originalKey = dataTable.getRowKey();
        selectionItems.clear();
        for (Object selectionKey : selection) {
            dataTable.setRowKey(selectionKey);
            if (dataTable.isRowAvailable()) {
                selectionItems.add((NoticeView) dataTable.getRowData());
            }
        }
        dataTable.setRowKey(originalKey);
    }
    
	public Collection<Object> getSelection() {
		return selection;
	}
	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}
	public List<NoticeView> getNoticeItems() {
		return noticeItems;
	}
	public void setNoticeItems(List<NoticeView> noticeItems) {
		this.noticeItems = noticeItems;
	}
	public List<NoticeView> getSelectionItems() {
		return selectionItems;
	}
	public void setSelectionItems(List<NoticeView> selectionItems) {
		this.selectionItems = selectionItems;
	}
    
    
}
