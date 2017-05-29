package com.epam.webapp.pool;


import com.epam.webapp.manager.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class ConnectionPool{
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private BlockingQueue<ProxyConnection> connections;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();

    private String url;
    private String login;
    private String password;

    private ConnectionPool(String url, String login, String password, int connectionsCount){
        this.connections = new ArrayBlockingQueue<ProxyConnection>(connectionsCount);
        this.url = url;
        this.login = login;
        this.password = password;

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, "SQLException: Can not register Driver!", e);
            throw new RuntimeException(e);
        }

        for (int i = 0; i < connectionsCount; i++){
            connections.add(createConnection());
        }
        checkConnectionsSize();
    }

    private void checkConnectionsSize(){
        int failConnections = connections.remainingCapacity();
        if(failConnections > 0){
            for (int i = 0; i < failConnections; i++){
                connections.add(createConnection());
            }
        }
        if(connections.isEmpty()){
            LOGGER.log(Level.FATAL, "Connections to DB can not create!");
            throw new RuntimeException();
        }
    }

    private ProxyConnection createConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "SQLException: Can not create connection", e);
        }
        return new ProxyConnection(connection);
    }

    public ProxyConnection takeConnection(){
        ProxyConnection connection = null;
        try {
            connection = connections.take();
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "InterruptedException", e);
        }
        return connection;
    }

    public static ConnectionPool getInstance(){
        if (!isCreated.get()){
            lock.lock();
            try {
                if (instance == null) {
                    String url = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_ADDRESS);
                    String admin = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN);
                    String password = ConfigurationManager.getInstance().getProperty(ConfigurationManager.PASSWORD);
                    int poolSize = Integer.parseInt(ConfigurationManager.getInstance().getProperty(ConfigurationManager.POOL_SIZE));
                    instance = new ConnectionPool(url, admin, password, poolSize);
                    isCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public int size() {
        return connections.size();
    }

    void returnConnection(ProxyConnection connection){
        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "InterruptedException", e);
        }
    }

    public void closePool(){
        while (!connections.isEmpty()){
            try {
                takeConnection().closeConnection();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "SQLException: Can not close connection!", e);
            }
        }
    }
}
