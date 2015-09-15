package com.mchange.v2.c3p0.impl;

import com.mchange.v2.log.MLevel;
import com.mchange.v2.log.MLog;
import com.mchange.v2.log.MLogger;
import com.mchange.v2.sql.SqlUtils;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by wangxichun on 2015/9/15.
 */
public final class NewProxyResultSet implements ResultSet {
    protected ResultSet inner;
    private static final MLogger logger = MLog.getLogger("com.mchange.v2.c3p0.impl.NewProxyResultSet");
    volatile NewPooledConnection parentPooledConnection;
    ConnectionEventListener cel;
    Object creator;
    Object creatorProxy;
    NewProxyConnection proxyConn;

    public NewProxyResultSet(ResultSet inner) {
        this.cel = new ConnectionEventListener() {
            public void connectionErrorOccurred(ConnectionEvent evt) {
            }

            public void connectionClosed(ConnectionEvent evt) {
                NewProxyResultSet.this.detach();
            }
        };
        this.inner = inner;
    }

    public final ResultSetMetaData getMetaData() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getMetaData();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final Statement getStatement() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            if (this.creator instanceof Statement) {
                return (Statement) this.creatorProxy;
            } else if (this.creator instanceof DatabaseMetaData) {
                return null;
            } else {
                throw new InternalError("Must be Statement or DatabaseMetaData -- Bad Creator: " + this.creator);
            }
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final SQLWarning getWarnings() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getWarnings();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void clearWarnings() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.clearWarnings();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void setFetchDirection(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.setFetchDirection(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final int getFetchDirection() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getFetchDirection();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void setFetchSize(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.setFetchSize(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final int getFetchSize() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getFetchSize();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final boolean wasNull() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.wasNull();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final Blob getBlob(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getBlob(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Blob getBlob(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getBlob(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Clob getClob(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getClob(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Clob getClob(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getClob(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final InputStream getAsciiStream(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getAsciiStream(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final InputStream getAsciiStream(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getAsciiStream(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final InputStream getUnicodeStream(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getUnicodeStream(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final InputStream getUnicodeStream(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getUnicodeStream(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final InputStream getBinaryStream(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getBinaryStream(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final InputStream getBinaryStream(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getBinaryStream(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final String getCursorName() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getCursorName();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final int findColumn(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.findColumn(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Reader getCharacterStream(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getCharacterStream(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Reader getCharacterStream(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getCharacterStream(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final boolean isBeforeFirst() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.isBeforeFirst();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final boolean isAfterLast() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.isAfterLast();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final boolean isLast() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.isLast();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void beforeFirst() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.beforeFirst();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void afterLast() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.afterLast();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final int getRow() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getRow();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final boolean absolute(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.absolute(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final boolean relative(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.relative(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final int getConcurrency() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getConcurrency();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final boolean rowUpdated() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.rowUpdated();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final boolean rowInserted() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.rowInserted();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final boolean rowDeleted() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.rowDeleted();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void updateNull(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateNull(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final void updateNull(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateNull(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final void updateBoolean(int a, boolean b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateBoolean(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateBoolean(String a, boolean b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateBoolean(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateByte(int a, byte b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateByte(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateByte(String a, byte b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateByte(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateShort(int a, short b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateShort(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateShort(String a, short b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateShort(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateInt(String a, int b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateInt(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateInt(int a, int b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateInt(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateLong(int a, long b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateLong(a, b);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var5);
            } else {
                throw var5;
            }
        } catch (Exception var6) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var6);
            } else {
                throw SqlUtils.toSQLException(var6);
            }
        }
    }

    public final void updateLong(String a, long b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateLong(a, b);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var5);
            } else {
                throw var5;
            }
        } catch (Exception var6) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var6);
            } else {
                throw SqlUtils.toSQLException(var6);
            }
        }
    }

    public final void updateFloat(int a, float b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateFloat(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateFloat(String a, float b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateFloat(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateDouble(String a, double b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateDouble(a, b);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var5);
            } else {
                throw var5;
            }
        } catch (Exception var6) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var6);
            } else {
                throw SqlUtils.toSQLException(var6);
            }
        }
    }

    public final void updateDouble(int a, double b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateDouble(a, b);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var5);
            } else {
                throw var5;
            }
        } catch (Exception var6) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var6);
            } else {
                throw SqlUtils.toSQLException(var6);
            }
        }
    }

    public final void updateBigDecimal(String a, BigDecimal b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateBigDecimal(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateBigDecimal(int a, BigDecimal b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateBigDecimal(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateString(String a, String b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateString(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateString(int a, String b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateString(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateBytes(String a, byte[] b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateBytes(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateBytes(int a, byte[] b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateBytes(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateDate(int a, Date b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateDate(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateDate(String a, Date b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateDate(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateTimestamp(String a, Timestamp b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateTimestamp(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateTimestamp(int a, Timestamp b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateTimestamp(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateAsciiStream(String a, InputStream b, int c) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateAsciiStream(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var5);
            } else {
                throw var5;
            }
        } catch (Exception var6) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var6);
            } else {
                throw SqlUtils.toSQLException(var6);
            }
        }
    }

    public final void updateAsciiStream(int a, InputStream b, int c) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateAsciiStream(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var5);
            } else {
                throw var5;
            }
        } catch (Exception var6) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var6);
            } else {
                throw SqlUtils.toSQLException(var6);
            }
        }
    }

    public final void updateBinaryStream(String a, InputStream b, int c) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateBinaryStream(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var5);
            } else {
                throw var5;
            }
        } catch (Exception var6) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var6);
            } else {
                throw SqlUtils.toSQLException(var6);
            }
        }
    }

    public final void updateBinaryStream(int a, InputStream b, int c) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateBinaryStream(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var5);
            } else {
                throw var5;
            }
        } catch (Exception var6) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var6);
            } else {
                throw SqlUtils.toSQLException(var6);
            }
        }
    }

    public final void updateCharacterStream(String a, Reader b, int c) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateCharacterStream(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var5);
            } else {
                throw var5;
            }
        } catch (Exception var6) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var6);
            } else {
                throw SqlUtils.toSQLException(var6);
            }
        }
    }

    public final void updateCharacterStream(int a, Reader b, int c) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateCharacterStream(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var5);
            } else {
                throw var5;
            }
        } catch (Exception var6) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var6);
            } else {
                throw SqlUtils.toSQLException(var6);
            }
        }
    }

    public final void updateObject(int a, Object b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateObject(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateObject(int a, Object b, int c) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateObject(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var5);
            } else {
                throw var5;
            }
        } catch (Exception var6) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var6);
            } else {
                throw SqlUtils.toSQLException(var6);
            }
        }
    }

    public final void updateObject(String a, Object b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateObject(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateObject(String a, Object b, int c) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateObject(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var5);
            } else {
                throw var5;
            }
        } catch (Exception var6) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var6);
            } else {
                throw SqlUtils.toSQLException(var6);
            }
        }
    }

    public final void insertRow() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.insertRow();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void updateRow() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateRow();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void deleteRow() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.deleteRow();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void refreshRow() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.refreshRow();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void cancelRowUpdates() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.cancelRowUpdates();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void moveToInsertRow() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.moveToInsertRow();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void moveToCurrentRow() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.moveToCurrentRow();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void updateRef(String a, Ref b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateRef(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateRef(int a, Ref b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateRef(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateBlob(String a, Blob b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateBlob(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateBlob(int a, Blob b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateBlob(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateClob(String a, Clob b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateClob(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateClob(int a, Clob b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateClob(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateArray(int a, Array b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateArray(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateArray(String a, Array b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateArray(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final Object getObject(String a, Map b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getObject(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final Object getObject(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getObject(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Object getObject(int a, Map b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getObject(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final Object getObject(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getObject(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final boolean getBoolean(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getBoolean(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final boolean getBoolean(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getBoolean(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final byte getByte(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getByte(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final byte getByte(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getByte(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final short getShort(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getShort(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final short getShort(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getShort(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final int getInt(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getInt(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final int getInt(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getInt(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final long getLong(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getLong(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final long getLong(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getLong(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final float getFloat(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getFloat(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final float getFloat(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getFloat(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final double getDouble(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getDouble(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final double getDouble(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getDouble(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final byte[] getBytes(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getBytes(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final byte[] getBytes(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getBytes(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Array getArray(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getArray(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Array getArray(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getArray(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final boolean next() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.next();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final URL getURL(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getURL(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final URL getURL(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getURL(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final int getType() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getType();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final boolean previous() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.previous();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final void close() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            if (!this.isDetached()) {
                if (this.creator instanceof Statement) {
                    this.parentPooledConnection.markInactiveResultSetForStatement((Statement) this.creator, this.inner);
                } else if (this.creator instanceof DatabaseMetaData) {
                    this.parentPooledConnection.markInactiveMetaDataResultSet(this.inner);
                } else {
                    if (!(this.creator instanceof Connection)) {
                        throw new InternalError("Must be Statement or DatabaseMetaData -- Bad Creator: " + this.creator);
                    }

                    this.parentPooledConnection.markInactiveRawConnectionResultSet(this.inner);
                }

                this.detach();
                this.inner.close();
                this.inner = null;
            }
        } catch (NullPointerException var2) {
            if (!this.isDetached()) {
                throw var2;
            }

            if (logger.isLoggable(MLevel.FINE)) {
                logger.log(MLevel.FINE, this + ": close() called more than once.");
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            }

            throw SqlUtils.toSQLException(var3);
        }

    }

    public final Ref getRef(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getRef(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Ref getRef(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getRef(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final boolean first() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.first();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final Time getTime(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getTime(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Time getTime(String a, Calendar b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getTime(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final Time getTime(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getTime(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Time getTime(int a, Calendar b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getTime(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final Date getDate(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getDate(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Date getDate(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getDate(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Date getDate(int a, Calendar b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getDate(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final Date getDate(String a, Calendar b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getDate(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final String getString(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getString(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final String getString(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getString(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final boolean last() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.last();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final Timestamp getTimestamp(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getTimestamp(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Timestamp getTimestamp(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getTimestamp(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final Timestamp getTimestamp(int a, Calendar b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getTimestamp(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final Timestamp getTimestamp(String a, Calendar b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getTimestamp(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final boolean isFirst() throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.isFirst();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var2);
            } else {
                throw var2;
            }
        } catch (Exception var3) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var3);
            } else {
                throw SqlUtils.toSQLException(var3);
            }
        }
    }

    public final BigDecimal getBigDecimal(int a, int b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getBigDecimal(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final BigDecimal getBigDecimal(String a, int b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getBigDecimal(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final BigDecimal getBigDecimal(int a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getBigDecimal(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final BigDecimal getBigDecimal(String a) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            return this.inner.getBigDecimal(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var3);
            } else {
                throw var3;
            }
        } catch (Exception var4) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var4);
            } else {
                throw SqlUtils.toSQLException(var4);
            }
        }
    }

    public final void updateTime(int a, Time b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateTime(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    public final void updateTime(String a, Time b) throws SQLException {
        try {
            if (this.proxyConn != null) {
                this.proxyConn.maybeDirtyTransaction();
            }

            this.inner.updateTime(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed ResultSet!!!", var4);
            } else {
                throw var4;
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            } else {
                throw SqlUtils.toSQLException(var5);
            }
        }
    }

    void attach(NewPooledConnection parentPooledConnection) {
        this.parentPooledConnection = parentPooledConnection;
        parentPooledConnection.addConnectionEventListener(this.cel);
    }

    private void detach() {
        this.parentPooledConnection.removeConnectionEventListener(this.cel);
        this.parentPooledConnection = null;
    }

    NewProxyResultSet(ResultSet inner, NewPooledConnection parentPooledConnection) {
        this(inner);
        this.attach(parentPooledConnection);
    }

    boolean isDetached() {
        return this.parentPooledConnection == null;
    }

    NewProxyResultSet(ResultSet inner, NewPooledConnection parentPooledConnection, Object c, Object cProxy) {
        this(inner, parentPooledConnection);
        this.creator = c;
        this.creatorProxy = cProxy;
        if (this.creatorProxy instanceof NewProxyConnection) {
            this.proxyConn = (NewProxyConnection) cProxy;
        }

    }
}
