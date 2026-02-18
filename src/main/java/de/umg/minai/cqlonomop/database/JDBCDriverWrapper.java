package de.umg.minai.cqlonomop.database;

import java.sql.*;
import java.util.Properties;

/**
 * Wrapper that delegates to a driver loaded by an isolated ClassLoader.
 * <br/>
 * Registering this wrapper (which is loaded by the application classloader)
 * prevents the JDBC DriverManager from later rejecting the driver when it compares the classloader that loaded the driver to the classloader that calls into JDBC at that later time
 * in order to enforce JDBC-specific visibility rules.
 */
final class JDBCDriverWrapper implements Driver {

    private final Driver targetDriver;

    JDBCDriverWrapper(final Driver delegate) {
        this.targetDriver = delegate;
    }

    @Override
    public Connection connect(final String url, final Properties info) throws SQLException {
        return targetDriver.connect(url, info);
    }

    @Override
    public boolean acceptsURL(final String url) throws SQLException {
        return targetDriver.acceptsURL(url);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(final String url, final Properties info) throws SQLException {
        return targetDriver.getPropertyInfo(url, info);
    }

    @Override
    public int getMajorVersion() {
        return targetDriver.getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return targetDriver.getMinorVersion();
    }

    @Override
    public boolean jdbcCompliant() {
        return targetDriver.jdbcCompliant();
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return targetDriver.getParentLogger();
    }

}
