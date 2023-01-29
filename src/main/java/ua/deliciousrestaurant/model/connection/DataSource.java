package ua.deliciousrestaurant.model.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ua.deliciousrestaurant.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

import static ua.deliciousrestaurant.model.connection.ConnectionDBConstants.*;

public class DataSource {

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource dataSource;

    static {
        config.setJdbcUrl( JDBC_URL );
        config.setUsername( USERNAME );
        config.setPassword( PASSWORD );
        config.setDriverClassName( DRIVER_CLASS_NAME );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        dataSource = new HikariDataSource( config );
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void rollback(Connection con) throws DaoException {
        try {
            con.rollback();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static void close(Connection con) throws DaoException {
        try {
            con.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}