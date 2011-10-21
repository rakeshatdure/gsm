package UTIL;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * @author wohlgemuth
 * generates the minimal hibernate configfile
 */
public class GenerateConfig extends Task{

    String outputDir;
   
    public void execute_en() throws BuildException {
        super.execute();
       
        if(outputDir == null){
            throw new BuildException("you must set a directory");
        }
       
        File file = new File(outputDir+"/hibernate.cfg.xml");

       
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<!DOCTYPE hibernate-configuration PUBLIC \"-//Hibernate/Hibernate Configuration DTD 3.0//EN\" \"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd\">\n");
            writer.write("<hibernate-configuration>\n");
            writer.write("<session-factory>\n");
            writer.write("<property name=\"hibernate.dialect\">org.hibernate.dialect.MySQLDialect</property>\n");
            writer.write("<property name=\"hibernate.connection.driver_class\">com.mysql.jdbc.Driver</property>\n");
            writer.write("<property name=\"hibernate.connection.url\">jdbc:mysql://localhost:3306/mallshopping_en</property>\n");
            writer.write("<property name=\"hibernate.connection.username\">root</property>\n");
            writer.write("<!--property name=\"hibernate.connection.password\">5678</property-->\n");
            writer.write("<mapping resource=\"POJO/Promotions.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Productphotos.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Manufacturer.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Productorder.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Newscategory.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Transport.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/CategoryChild.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/News.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Stateorder.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Category.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Products.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Role.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/User.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Thamso.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Newsphotos.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Productorderdetail.hbm.xml\"/>\n");
            System.out.println("lang=en");
            writer.write("</session-factory>");
            writer.write("</hibernate-configuration>");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }
    
    public void execute_vn() throws BuildException {
        super.execute();
       
        if(outputDir == null){
            throw new BuildException("you must set a directory");
        }
       
        File file = new File(outputDir+"/hibernate.cfg.xml");

       
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<!DOCTYPE hibernate-configuration PUBLIC \"-//Hibernate/Hibernate Configuration DTD 3.0//EN\" \"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd\">\n");
            writer.write("<hibernate-configuration>\n");
            writer.write("<session-factory>\n");
            writer.write("<property name=\"hibernate.dialect\">org.hibernate.dialect.MySQLDialect</property>\n");
            writer.write("<property name=\"hibernate.connection.driver_class\">com.mysql.jdbc.Driver</property>\n");
            writer.write("<property name=\"hibernate.connection.url\">jdbc:mysql://localhost:3306/mallshopping_vi</property>\n");
            writer.write("<property name=\"hibernate.connection.username\">root</property>\n");
            writer.write("<!--property name=\"hibernate.connection.password\">5678</property-->\n");
            writer.write("<mapping resource=\"POJO/Promotions.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Productphotos.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Manufacturer.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Productorder.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Newscategory.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Transport.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/CategoryChild.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/News.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Stateorder.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Category.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Products.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Role.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/User.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Thamso.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Newsphotos.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Productorderdetail.hbm.xml\"/>\n");
            System.out.println("lang=vn");
            writer.write("</session-factory>");
            writer.write("</hibernate-configuration>");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }
    
    public void execute_kr() throws BuildException {
        super.execute();
       
        if(outputDir == null){
            throw new BuildException("you must set a directory");
        }
       
        File file = new File(outputDir+"/hibernate.cfg.xml");

       
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<!DOCTYPE hibernate-configuration PUBLIC \"-//Hibernate/Hibernate Configuration DTD 3.0//EN\" \"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd\">\n");
            writer.write("<hibernate-configuration>\n");
            writer.write("<session-factory>\n");
            writer.write("<property name=\"hibernate.dialect\">org.hibernate.dialect.MySQLDialect</property>\n");
            writer.write("<property name=\"hibernate.connection.driver_class\">com.mysql.jdbc.Driver</property>\n");
            writer.write("<property name=\"hibernate.connection.url\">jdbc:mysql://localhost:3306/mallshopping_kr</property>\n");
            writer.write("<property name=\"hibernate.connection.username\">root</property>\n");
            writer.write("<!--property name=\"hibernate.connection.password\">5678</property-->\n");
            writer.write("<mapping resource=\"POJO/Promotions.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Productphotos.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Manufacturer.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Productorder.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Newscategory.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Transport.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/CategoryChild.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/News.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Stateorder.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Category.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Products.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Role.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/User.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Thamso.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Newsphotos.hbm.xml\"/>\n");
            writer.write("<mapping resource=\"POJO/Productorderdetail.hbm.xml\"/>\n");
            System.out.println("lang=Kr");
            writer.write("</session-factory>");
            writer.write("</hibernate-configuration>");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }



    public GenerateConfig() {
        super();
    }

    public String getOutputDir() {
        return this.outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

 
}
