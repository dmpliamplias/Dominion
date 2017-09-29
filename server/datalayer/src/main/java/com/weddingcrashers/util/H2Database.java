package com.weddingcrashers.util;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;

import static com.weddingcrashers.util.DatabaseStatics.DATABASE_URL;
import static java.sql.DriverManager.getConnection;
import static org.h2.tools.Server.createTcpServer;

/**
 * H2Database provider.
 *
 * @author dmpliamplias
 */
public final class H2Database {

    // ---- Members

    /** The database server. */
    private Server server;


    // ----- Constructor

    /**
     * Constructor.
     */
    private H2Database() {
        try {
            server = establishConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private Server establishConnection() throws ClassNotFoundException, SQLException {
        Server server = null;
        try {
            server = createTcpServer("-tcpAllowOthers", "-tcpPort", "9092").start();
            Class.forName("org.h2.Driver");
            Connection connection = getConnection(DATABASE_URL, "sa", "sa");
            System.out.println("Connection Established: " + connection.getMetaData().getDatabaseProductName() + "/" + connection.getCatalog());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return server;
    }

}
