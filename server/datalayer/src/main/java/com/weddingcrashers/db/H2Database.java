package com.weddingcrashers.db;

import com.weddingcrashers.model.User;
import com.weddingcrashers.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import static com.weddingcrashers.db.DatabaseStatics.DATABASE_URL;
import static java.sql.DriverManager.getConnection;
import static org.h2.tools.Server.createTcpServer;

/**
 * H2Database provider.
 *
 * @author dmpliamplias
 */
public final class H2Database {

    // ----- Constructor

    /**
     * Constructor.
     */
    public H2Database() {
        try {
            establishConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        final UserServiceImpl userService = new UserServiceImpl();
//        final User dyoni = new User().name("dmpliamplias").email("dyoni@pop.agri.ch").password("banana");
//        userService.create(dyoni);
    }


    // ---- Methods

    /**
     * Starts the h2 database.
     *
     * @param args the args.
     */
    public static void main(String[] args) {
        new H2Database();
    }

    /**
     * Establish the connection to the H2 database.
     */
    private void establishConnection() throws ClassNotFoundException, SQLException {
        createTcpServer("-tcpAllowOthers", "-tcpPort", "9092").start();
        Class.forName("org.h2.Driver");
        Connection connection = getConnection(DATABASE_URL, "sa", "sa");
        System.out.println("Connection Established: " + connection.getMetaData().getDatabaseProductName() + "/" + connection.getCatalog());
    }

}
