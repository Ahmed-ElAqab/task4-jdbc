package com.aboelsyeed.jdbc;


import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class MyDataSourceFactory extends MysqlDataSource{
    private static MyDataSourceFactory myDataSourceFactory_instance= null;
    Properties props= new Properties();
    FileInputStream fis= null;

    private MyDataSourceFactory(){
        try {
            fis = new FileInputStream("db.properties");
            props.load(fis);
            this.setURL(props.getProperty("MYSQL_DB_URL"));
            this.setUser(props.getProperty("MYSQL_DB_USERNAME"));
            this.setPassword(props.getProperty("MYSQL_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MyDataSourceFactory getInstanace(){
        if(myDataSourceFactory_instance== null)
            myDataSourceFactory_instance= new MyDataSourceFactory();
        return myDataSourceFactory_instance;
    }

}
