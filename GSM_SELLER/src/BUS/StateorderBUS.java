/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.*;
import POJO.*;
import java.util.List;
/**
 *
 * @author admin
 */
public class StateorderBUS {
    
     /** Get all Stateorder **/
    public static List<Stateorder> lstStateorder(String lang) {
        return StateorderDAO.lstStateorder(lang);
    }

     
    /** Get Stateorder in ID **/
    public static Stateorder getStateorder( int id, String lang) {
        return StateorderDAO.getStateorder(id, lang);
    }

    /** Insert Stateorder **/ 
    public static boolean insertStateorder(Stateorder p, String lang) {
        return StateorderDAO.insertStateorder(p, lang);
    }

    /** Update Stateorder **/
    public static boolean updateStateorder(Stateorder p, String lang) {
        return StateorderDAO.updateStateorder(p, lang);
    }

    /** Delete Stateorder **/
    public static boolean deleteStateorder(Stateorder p, String lang) {
        return StateorderDAO.deleteStateorder(p, lang);
    }
}
