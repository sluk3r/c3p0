package com.mchange.v2.c3p0.impl;

import com.mchange.v2.c3p0.C3P0ProxyStatement;
import com.mchange.v2.log.MLevel;
import com.mchange.v2.log.MLog;
import com.mchange.v2.log.MLogger;
import com.mchange.v2.sql.SqlUtils;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;

/**
 * Created by wangxichun on 2015/9/15.
 */
public final class NewProxyStatement implements Statement, C3P0ProxyStatement {
    protected Statement inner;
    private static final MLogger logger = MLog.getLogger("com.mchange.v2.c3p0.impl.NewProxyStatement");
    volatile NewPooledConnection parentPooledConnection;
    ConnectionEventListener cel;
    boolean is_cached;
    NewProxyConnection creatorProxy;

    public NewProxyStatement(Statement inner) {
        this.cel = new ConnectionEventListener() {
            public void connectionErrorOccurred(ConnectionEvent evt) {
            }

            public void connectionClosed(ConnectionEvent evt) {
                NewProxyStatement.this.detach();
            }
        };
        this.inner = inner;
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

    NewProxyStatement(Statement inner, NewPooledConnection parentPooledConnection) {
        this(inner);
        this.attach(parentPooledConnection);
    }

    boolean isDetached() {
        return this.parentPooledConnection == null;
    }

    NewProxyStatement(Statement inner, NewPooledConnection parentPooledConnection, boolean cached, NewProxyConnection cProxy) {
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
