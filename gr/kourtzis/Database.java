package gr.kourtzis;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * The class Database. An interface for accessing
 * data on a physical database server.
 */
public interface Database
{
    /**
     * The method Register(). initializes the driver in order
     * to open a connection with the database
     * @param driver A String variable containing the driver name
     * @throws ClassNotFoundException
     */
    void Register(String driver) throws ClassNotFoundException;

    /**
     * The method Open(). Creates a connection object
     * and connects with the database server.
     * @param database
     * @throws SQLException
     */
    void Open(String database) throws SQLException;

    /**
     * The method Execute(). Built and submits SQL statements to the database.
     * @param statement a Statement variable.
     * @param sql a String representation of one of the operations that doesn't return a result set
     *            Possible operations are: CREATE, INSERT, SELECT, UPDATE, DELETE
     * @throws SQLException
     */
    void Execute(Statement statement, String sql) throws SQLException;

    /**
     * The method CleanUp(). Closes all database resources.
     * @throws SQLException
     */
    void CleanUp() throws SQLException;
}
