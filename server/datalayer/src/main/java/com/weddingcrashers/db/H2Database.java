package com.weddingcrashers.db;

import org.h2.tools.Server;

import java.sql.Connection;
import java.util.logging.Logger;

import static com.weddingcrashers.db.DatabaseStatics.DATABASE_URL;
import static java.sql.DriverManager.getConnection;
import static org.h2.tools.Server.createTcpServer;

/**
 * H2 database provider.
 *
 * @author dmpliamplias
 */
public final class H2Database {

    // ---- Statics

    /** The logger */
    private static final Logger LOG = Logger.getLogger(H2Database.class.getSimpleName());

    /** The database port. */
    private static final String PORT = "9092";


    // ---- Members

    /** The h2 tcp server. */
    private static Server tcpServer;


    // ---- Methods

    /**
     * Establish the connection to the H2 database.
     */
    public void establishConnection() {
        try {
            tcpServer = createTcpServer("-tcpAllowOthers", "-tcpPort", PORT).start();
            Class.forName("org.h2.Driver");
            Connection connection = getConnection(DATABASE_URL, "sa", "sa");
            LOG.info("Connection Established: " + connection.getMetaData().getDatabaseProductName() + "/" + connection.getCatalog() + " on port: " + PORT);
        }
        catch (Exception e) {
            LOG.severe(e.getMessage());
        }
    }

    /**
     * Shuts down the database.
     */
    public void shutdownDatabase() {
        tcpServer.stop();
        LOG.info("Shutdown the tcp server listening on port: " + tcpServer.getPort());
    }

}
