package sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.sql.rowset.CachedRowSet;

import java.util.ArrayList;

import manager.platform_selection.structures.Platform;
import manager.platform_selection.structures.PlatformConnectionParameters;

import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

import com.sun.rowset.CachedRowSetImpl;

//import dialogs.Dialogs;

public class LocalStorage {

    private PoolingDataSource dbPoolConnections = null;

    private String dbPath = "jdbc:hsqldb:file:LocalStorage/DB";
    private String dbUser = "admin";
    private String dbPassword = "admin";

    public LocalStorage() throws ClassNotFoundException {
        Class.forName("org.hsqldb.jdbcDriver");

        String connectionString = dbPath + "?user=" + dbUser + "&password="
                + dbPassword;
        GenericObjectPool<Object> connectionPool = new GenericObjectPool<Object>(
                null);
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
                connectionString, null);
        new PoolableConnectionFactory(connectionFactory, connectionPool, null,
                null, false, true);
        dbPoolConnections = new PoolingDataSource(connectionPool);

    }

    private CachedRowSet ExecuteQuery(String query) throws SQLException {
        CachedRowSet cachedResult = new CachedRowSetImpl();
        ResultSet result = null;
        Connection db = dbPoolConnections.getConnection();
        Statement statement = null;
        statement = db.createStatement();
        result = statement.executeQuery(query);
        cachedResult.populate(result);
        statement.close();
        db.close();
        return cachedResult;
    }

    private void ExecuteUpdate(String query) throws SQLException {
        Connection db = dbPoolConnections.getConnection();
        Statement statement = null;
        statement = db.createStatement();
        statement.executeUpdate(query);
        statement.close();
        db.close();
    }

    public void InitializeStructure() throws SQLException {
        String query = "CREATE TABLE IF NOT EXISTS Platforms "
                + "(ID IDENTITY , " + "PlatformName LONGVARCHAR, "
                + "PlatformDescription LONGVARCHAR, " + "dbType LONGVARCHAR, "
                + "dbHost LONGVARCHAR, " + "dbPort BIGINT, "
                + "dbName LONGVARCHAR, " + "dbUser LONGVARCHAR, "
                + "dbPassword LONGVARCHAR) ";

        ExecuteUpdate(query);
    }

    public void AddPlatform(Platform platform) throws SQLException {
        String PlatformName = platform.GetName();
        String PlatformDescription = platform.GetDescription();
        PlatformConnectionParameters connectionParams = platform
                .GetConnectionParameters();
        String dbType = connectionParams.dbType;
        String dbHost = connectionParams.dbHost;
        Integer dbPort = connectionParams.dbPort;
        String dbName = connectionParams.dbName;
        String dbUser = connectionParams.dbUser;
        String dbPassword = connectionParams.dbPassword;

        String query = "INSERT INTO Platforms (PlatformName, PlatformDescription, dbType, dbHost, dbPort, dbName, dbUser, dbPassword) "
                + "VALUES('"
                + PlatformName
                + "', '"
                + PlatformDescription
                + "', '"
                + dbType
                + "', '"
                + dbHost
                + "', "
                + dbPort
                + ", '"
                + dbName + "', '" + dbUser + "', '" + dbPassword + "')";
        ExecuteUpdate(query);
    }

    public void EditPlatform(Platform platform) throws SQLException {
        String PlatformName = platform.GetName();
        String PlatformDescription = platform.GetDescription();
        PlatformConnectionParameters connectionParams = platform
                .GetConnectionParameters();
        String dbType = connectionParams.dbType;
        String dbHost = connectionParams.dbHost;
        Integer dbPort = connectionParams.dbPort;
        String dbName = connectionParams.dbName;
        String dbUser = connectionParams.dbUser;
        String dbPassword = connectionParams.dbPassword;

        String query = "UPDATE Platforms SET " + "PlatformName = '"
                + PlatformName + "', " + "PlatformDescription = '"
                + PlatformDescription + "', " + "dbType = '" + dbType + "', "
                + "dbHost = '" + dbHost + "', " + "dbPort = " + dbPort + ", "
                + "dbName = '" + dbName + "', " + "dbUser = '" + dbUser + "', "
                + "dbPassword = '" + dbPassword + "' " + "WHERE ID = "
                + platform.GetLocalStorageID();
        ExecuteUpdate(query);
    }

    public void DeletePlatform(Platform platform) throws SQLException {
        String query = "DELETE FROM Platforms " + "WHERE ID = "
                + platform.GetLocalStorageID();
        ExecuteUpdate(query);
    }

    public ArrayList<Platform> GetPlatforms() throws SQLException {
        ArrayList<Platform> platforms = new ArrayList<Platform>();
        String query = "SELECT ID, PlatformName, PlatformDescription, dbType, dbHost, dbPort, dbName, dbUser, dbPassword FROM Platforms";
        CachedRowSet result = ExecuteQuery(query);
        while (result.next()) {
            Platform platform = new Platform(result.getString(2),
                    result.getString(3));
            platform.SetLocalStorageID(result.getInt(1));
            PlatformConnectionParameters connectionParameters = new PlatformConnectionParameters();
            connectionParameters.dbType = result.getString(4);
            connectionParameters.dbHost = result.getString(5);
            connectionParameters.dbPort = result.getInt(6);
            connectionParameters.dbName = result.getString(7);
            connectionParameters.dbUser = result.getString(8);
            connectionParameters.dbPassword = result.getString(9);
            platform.SetConnectionParameters(connectionParameters);
            platforms.add(platform);
        }
        result.close();

        return platforms;
    }

}
