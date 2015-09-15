package com.mchange.v2.c3p0.impl;

import com.mchange.v2.c3p0.C3P0ProxyStatement;
import com.mchange.v2.log.MLevel;
import com.mchange.v2.log.MLog;
import com.mchange.v2.log.MLogger;
import com.mchange.v2.sql.SqlUtils;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

/**
 * Created by wangxichun on 2015/9/15.
 */
public final class NewProxyPreparedStatement implements PreparedStatement, C3P0ProxyStatement {
    protected PreparedStatement inner;
    private static final MLogger logger = MLog.getLogger("com.mchange.v2.c3p0.impl.NewProxyPreparedStatement");
    volatile NewPooledConnection parentPooledConnection;
    ConnectionEventListener cel;
    boolean is_cached;
    NewProxyConnection creatorProxy;

    public NewProxyPreparedStatement(PreparedStatement inner) {
        this.cel = new ConnectionEventListener() {
            public void connectionErrorOccurred(ConnectionEvent evt) {
            }

            public void connectionClosed(ConnectionEvent evt) {
                NewProxyPreparedStatement.this.detach();
            }
        };
        this.inner = inner;
    }

    public final ResultSetMetaData getMetaData() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.getMetaData();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final ResultSet executeQuery() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            ResultSet exc = this.inner.executeQuery();
            if (exc == null) {
                return null;
            } else {
                this.parentPooledConnection.markActiveResultSetForStatement(this.inner, exc);
                return new NewProxyResultSet(exc, this.parentPooledConnection, this.inner, this);
            }
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final int executeUpdate() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.executeUpdate();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final void addBatch() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.addBatch();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final void setNull(int a, int b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setNull(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setNull(int a, int b, String c) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setNull(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var5);
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

    public final void setBigDecimal(int a, BigDecimal b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setBigDecimal(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setBytes(int a, byte[] b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setBytes(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setAsciiStream(int a, InputStream b, int c) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setAsciiStream(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var5);
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

    public final void setUnicodeStream(int a, InputStream b, int c) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setUnicodeStream(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var5);
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

    public final void setBinaryStream(int a, InputStream b, int c) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setBinaryStream(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var5);
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

    public final void clearParameters() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.clearParameters();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final void setObject(int a, Object b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setObject(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setObject(int a, Object b, int c) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setObject(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var5);
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

    public final void setObject(int a, Object b, int c, int d) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setObject(a, b, c, d);
        } catch (NullPointerException var6) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var6);
            } else {
                throw var6;
            }
        } catch (Exception var7) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var7);
            } else {
                throw SqlUtils.toSQLException(var7);
            }
        }
    }

    public final void setCharacterStream(int a, Reader b, int c) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setCharacterStream(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var5);
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

    public final void setRef(int a, Ref b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setRef(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setBlob(int a, Blob b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setBlob(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setClob(int a, Clob b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setClob(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setArray(int a, Array b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setArray(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final ParameterMetaData getParameterMetaData() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.getParameterMetaData();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final void setBoolean(int a, boolean b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setBoolean(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setByte(int a, byte b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setByte(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setShort(int a, short b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setShort(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setInt(int a, int b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setInt(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setLong(int a, long b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setLong(a, b);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var5);
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

    public final void setFloat(int a, float b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setFloat(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setDouble(int a, double b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setDouble(a, b);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var5);
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

    public final void setTimestamp(int a, Timestamp b, Calendar c) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setTimestamp(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var5);
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

    public final void setTimestamp(int a, Timestamp b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setTimestamp(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setURL(int a, URL b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setURL(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setTime(int a, Time b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setTime(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setTime(int a, Time b, Calendar c) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setTime(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var5);
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

    public final void setDate(int a, Date b, Calendar c) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setDate(a, b, c);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var5);
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

    public final void setDate(int a, Date b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setDate(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final void setString(int a, String b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setString(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final boolean execute() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.execute();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final ResultSet executeQuery(String a) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            ResultSet exc = this.inner.executeQuery(a);
            if (exc == null) {
                return null;
            } else {
                this.parentPooledConnection.markActiveResultSetForStatement(this.inner, exc);
                return new NewProxyResultSet(exc, this.parentPooledConnection, this.inner, this);
            }
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var3);
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

    public final int executeUpdate(String a) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.executeUpdate(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var3);
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

    public final int executeUpdate(String a, String[] b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.executeUpdate(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final int executeUpdate(String a, int b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.executeUpdate(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final int executeUpdate(String a, int[] b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.executeUpdate(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final int getMaxFieldSize() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.getMaxFieldSize();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final void setMaxFieldSize(int a) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setMaxFieldSize(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var3);
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

    public final int getMaxRows() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.getMaxRows();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final void setMaxRows(int a) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setMaxRows(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var3);
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

    public final void setEscapeProcessing(boolean a) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setEscapeProcessing(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var3);
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

    public final int getQueryTimeout() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.getQueryTimeout();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final void setQueryTimeout(int a) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setQueryTimeout(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var3);
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

    public final SQLWarning getWarnings() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.getWarnings();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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
            this.maybeDirtyTransaction();
            this.inner.clearWarnings();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final void setCursorName(String a) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setCursorName(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var3);
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

    public final ResultSet getResultSet() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            ResultSet exc = this.inner.getResultSet();
            if (exc == null) {
                return null;
            } else {
                this.parentPooledConnection.markActiveResultSetForStatement(this.inner, exc);
                return new NewProxyResultSet(exc, this.parentPooledConnection, this.inner, this);
            }
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final int getUpdateCount() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.getUpdateCount();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final boolean getMoreResults() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.getMoreResults();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final boolean getMoreResults(int a) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.getMoreResults(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var3);
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

    public final void setFetchDirection(int a) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.setFetchDirection(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var3);
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
            this.maybeDirtyTransaction();
            return this.inner.getFetchDirection();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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
            this.maybeDirtyTransaction();
            this.inner.setFetchSize(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var3);
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
            this.maybeDirtyTransaction();
            return this.inner.getFetchSize();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final int getResultSetConcurrency() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.getResultSetConcurrency();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final int getResultSetType() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.getResultSetType();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final void addBatch(String a) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.addBatch(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var3);
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

    public final void clearBatch() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.clearBatch();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final int[] executeBatch() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.executeBatch();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final ResultSet getGeneratedKeys() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            ResultSet exc = this.inner.getGeneratedKeys();
            if (exc == null) {
                return null;
            } else {
                this.parentPooledConnection.markActiveResultSetForStatement(this.inner, exc);
                return new NewProxyResultSet(exc, this.parentPooledConnection, this.inner, this);
            }
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final int getResultSetHoldability() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.getResultSetHoldability();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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
            this.maybeDirtyTransaction();
            if (!this.isDetached()) {
                if (this.is_cached) {
                    this.parentPooledConnection.checkinStatement(this.inner);
                } else {
                    this.parentPooledConnection.markInactiveUncachedStatement(this.inner);

                    try {
                        this.inner.close();
                    } catch (Exception var3) {
                        if (logger.isLoggable(MLevel.WARNING)) {
                            logger.log(MLevel.WARNING, "Exception on close of inner statement.", var3);
                        }

                        SQLException sqle = SqlUtils.toSQLException(var3);
                        throw sqle;
                    }
                }

                this.detach();
                this.inner = null;
                this.creatorProxy = null;
            }
        } catch (NullPointerException var4) {
            if (!this.isDetached()) {
                throw var4;
            }

            if (logger.isLoggable(MLevel.FINE)) {
                logger.log(MLevel.FINE, this + ": close() called more than once.");
            }
        } catch (Exception var5) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var5);
            }

            throw SqlUtils.toSQLException(var5);
        }

    }

    public final void cancel() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            this.inner.cancel();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final Connection getConnection() throws SQLException {
        try {
            this.maybeDirtyTransaction();
            if (!this.isDetached()) {
                return this.creatorProxy;
            } else {
                throw new SQLException("You cannot operate on a closed Statement!");
            }
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var2);
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

    public final boolean execute(String a, int b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.execute(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final boolean execute(String a, int[] b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.execute(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final boolean execute(String a, String[] b) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.execute(a, b);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var4);
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

    public final boolean execute(String a) throws SQLException {
        try {
            this.maybeDirtyTransaction();
            return this.inner.execute(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Statement!!!", var3);
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

    void attach(NewPooledConnection parentPooledConnection) {
        this.parentPooledConnection = parentPooledConnection;
        parentPooledConnection.addConnectionEventListener(this.cel);
    }

    private void detach() {
        this.parentPooledConnection.removeConnectionEventListener(this.cel);
        this.parentPooledConnection = null;
    }

    NewProxyPreparedStatement(PreparedStatement inner, NewPooledConnection parentPooledConnection) {
        this(inner);
        this.attach(parentPooledConnection);
    }

    boolean isDetached() {
        return this.parentPooledConnection == null;
    }

    NewProxyPreparedStatement(PreparedStatement inner, NewPooledConnection parentPooledConnection, boolean cached, NewProxyConnection cProxy) {
        this(inner, parentPooledConnection);
        this.is_cached = cached;
        this.creatorProxy = cProxy;
    }

    public Object rawStatementOperation(Method m, Object target, Object[] args) throws IllegalAccessException, InvocationTargetException, SQLException {
        this.maybeDirtyTransaction();
        if (target == C3P0ProxyStatement.RAW_STATEMENT) {
            target = this.inner;
        }

        int out = 0;

        for (int innerResultSet = args.length; out < innerResultSet; ++out) {
            if (args[out] == C3P0ProxyStatement.RAW_STATEMENT) {
                args[out] = this.inner;
            }
        }

        Object var7 = m.invoke(target, args);
        if (var7 instanceof ResultSet) {
            ResultSet var6 = (ResultSet) var7;
            this.parentPooledConnection.markActiveResultSetForStatement(this.inner, var6);
            var7 = new NewProxyResultSet(var6, this.parentPooledConnection, this.inner, this);
        }

        return var7;
    }

    void maybeDirtyTransaction() {
        this.creatorProxy.maybeDirtyTransaction();
    }
}
