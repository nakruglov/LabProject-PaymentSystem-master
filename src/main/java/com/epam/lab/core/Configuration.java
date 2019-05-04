package com.epam.lab.core;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.impl.GenericObjectPool;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private static Properties properties = new Properties();
    private static String PATH_TO_PROPERTIES = "/config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        InputStream fileInputStream = Configuration.class.getResourceAsStream(PATH_TO_PROPERTIES);
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено");
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        DataSource dataSource = null;
        GenericObjectPool gPool;
        String jdbcDriver = properties.getProperty("JDBC_DRIVER");
        String jdbcDbUrl = properties.getProperty("JDBC_DB_URL");
        String jdbcUser = properties.getProperty("JDBC_USER");
        String jdbcPass = properties.getProperty("JDBC_PASS");
        try {
            Class.forName(jdbcDriver);
            ConnectionFactory cf = new DriverManagerConnectionFactory(jdbcDbUrl, jdbcUser, jdbcPass);
            System.out.println("jdbcDbUrl"+jdbcDbUrl);
            PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, null);
            gPool = new GenericObjectPool(pcf);
            gPool.setMaxIdle(5);
            dataSource = new PoolingDataSource(gPool);
        } catch (Exception e) {
            System.out.println("SQL exeption occured");
        }
        return dataSource;
    }

}