package UTIL;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ducnt
 */
public class Pagging {

    /**
     * Phuong thuc phan trang
     * @param col:
     * @param start:
     * @param end:
     * @param totalRows:
     * @param urlParrent:
     * @return
     */
    public static String pagging(int currentPage, int rowPerPages, int totalRows, String urlParrent) {

        String page = "<div class='oferta_pagination'>";

        Integer next = new Integer(currentPage + 1);
        Integer prev = new Integer(currentPage - 1);

        int numPage = totalRows / rowPerPages + 1;
        if (prev > 0) {
            page += "<a href='" + urlParrent + "?p=" + prev + "'> &nbsp;< &nbsp; </a>";
        }
        page += "<span class='current'>&nbsp;" + (new Integer(currentPage)).toString() + "&nbsp;</span>";
        //while(next < numPage){
        if (next < numPage + 1) { /// neu van con trang ke tiep ti -> next
            page += "<a href='" + urlParrent + "?p=" + next + " '> &nbsp;> &nbsp;</a> ";
            next += 1;
        }
        page += "</div>";
        return page;
    }
}
