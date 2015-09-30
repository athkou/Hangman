package gr.kourtzis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteDB implements Database
{
    public SQLiteDB() throws SQLException
    {
        result_set_ = null;
        connection_ = null;

        try
        {
            Register(SQLITE_DRIVER);
            Open(DATABASE_NAME);
        }
        catch(SQLException | ClassNotFoundException ex)
        {
            System.err.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
            CleanUp();
        }
    }

    @Override
    public void Register(String driver) throws ClassNotFoundException
    { Class.forName(driver); }

    @Override
    public void Open(String database) throws SQLException
    { connection_ = DriverManager.getConnection(database); }

    @Override
    public void Execute(Statement statement, String sql) throws SQLException
    {
        statement = connection_.createStatement();

        if(sql.startsWith("SELECT")) result_set_ = statement.executeQuery(sql);
        else                         statement.executeUpdate(sql);
    }

    @Override
    public void CleanUp() throws SQLException
    {
        if(result_set_ != null) result_set_.close();
        if(connection_ != null) connection_.close();
    }

    public ResultSet Result() { return result_set_; }

    private Connection connection_;
    private ResultSet result_set_;

    private final String SQLITE_DRIVER = "org.sqlite.JDBC";
    private final String DATABASE_NAME = "jdbc:sqlite:hangman.db";
}
