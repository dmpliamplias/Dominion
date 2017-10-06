package com.weddingcrashers.db;

import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import static com.weddingcrashers.db.DatabaseStatics.DATABASE_URL;
import static java.sql.DriverManager.getConnection;
import static org.h2.tools.Server.createTcpServer;

/**
 * H2Database provider.
 *
 * @author dmpliamplias
 */
public final class H2Database {

    // ---- Statics

    /** The logger */
    private static final Logger LOG = Logger.getLogger(H2Database.class.getSimpleName());


    // ---- Members

    /** The h2 tcp server. */
    private static Server tcpServer;


    // ----- Constructor

    /**
     * Constructor.
     */
    public H2Database() {
        try {
            tcpServer = establishConnection();
        } catch (Exception e) {
            LOG.severe(e.getMessage());
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
        final Server tcpServer = createTcpServer("-tcpAllowOthers", "-tcpPort", "9092").start();
        Class.forName("org.h2.Driver");
        Connection connection = getConnection(DATABASE_URL, "sa", "sa");
        LOG.info("Connection Established: " + connection.getMetaData().getDatabaseProductName() + "/" + connection.getCatalog());
        return tcpServer;
    }

    /**
     * Shuts down the database.
     */
    public void shutdownDatabase() {
        tcpServer.stop();
        LOG.info("Shutdown the tcp server listening on port " + tcpServer.getPort());
    }

}
