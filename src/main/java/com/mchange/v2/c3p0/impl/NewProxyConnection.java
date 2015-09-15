package com.mchange.v2.c3p0.impl;

import com.mchange.v2.c3p0.C3P0ProxyConnection;
import com.mchange.v2.log.MLevel;
import com.mchange.v2.log.MLog;
import com.mchange.v2.log.MLogger;
import com.mchange.v2.sql.SqlUtils;
import com.mchange.v2.util.ResourceClosedException;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.Map;

/**
 * Created by wangxichun on 2015/9/15.
 */
public final class NewProxyConnection implements Connection, C3P0ProxyConnection {
    protected Connection inner;
    boolean txn_known_resolved;
    DatabaseMetaData metaData;
    private static final MLogger logger = MLog.getLogger("com.mchange.v2.c3p0.impl.NewProxyConnection");
    volatile NewPooledConnection parentPooledConnection;
    ConnectionEventListener cel;

    public NewProxyConnection(Connection inner) {
        this.txn_known_resolved = true;
        this.metaData = null;
        this.cel = new ConnectionEventListener() {
            public void connectionErrorOccurred(ConnectionEvent evt) {
            }

            public void connectionClosed(ConnectionEvent evt) {
                NewProxyConnection.this.detach();
            }
        };
        this.inner = inner;
    }

    public synchronized Statement createStatement(int a, int b, int c) throws SQLException {
        try {
            this.txn_known_resolved = false;
            Statement exc = this.inner.createStatement(a, b, c);
            this.parentPooledConnection.markActiveUncachedStatement(exc);
            return new NewProxyStatement(exc, this.parentPooledConnection, false, this);
        } catch (NullPointerException var5) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var5);
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

    public synchronized Statement createStatement(int a, int b) throws SQLException {
        try {
            this.txn_known_resolved = false;
            Statement exc = this.inner.createStatement(a, b);
            this.parentPooledConnection.markActiveUncachedStatement(exc);
            return new NewProxyStatement(exc, this.parentPooledConnection, false, this);
        } catch (NullPointerException var4) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var4);
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

    public synchronized Statement createStatement() throws SQLException {
        try {
            this.txn_known_resolved = false;
            Statement exc = this.inner.createStatement();
            this.parentPooledConnection.markActiveUncachedStatement(exc);
            return new NewProxyStatement(exc, this.parentPooledConnection, false, this);
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized PreparedStatement prepareStatement(String a, String[] b) throws SQLException {
        try {
            this.txn_known_resolved = false;
            PreparedStatement exc;
            if (this.parentPooledConnection.isStatementCaching()) {
                try {
                    Class[] e = new Class[]{String.class, String[].class};
                    Method method = Connection.class.getMethod("prepareStatement", e);
                    Object[] args = new Object[]{a, b};
                    exc = (PreparedStatement) this.parentPooledConnection.checkoutStatement(method, args);
                    return new NewProxyPreparedStatement(exc, this.parentPooledConnection, true, this);
                } catch (ResourceClosedException var7) {
                    if (logger.isLoggable(MLevel.FINE)) {
                        logger.log(MLevel.FINE, "A Connection tried to prepare a Statement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", var7);
                    }

                    exc = this.inner.prepareStatement(a, b);
                    this.parentPooledConnection.markActiveUncachedStatement(exc);
                    return new NewProxyPreparedStatement(exc, this.parentPooledConnection, false, this);
                }
            } else {
                exc = this.inner.prepareStatement(a, b);
                this.parentPooledConnection.markActiveUncachedStatement(exc);
                return new NewProxyPreparedStatement(exc, this.parentPooledConnection, false, this);
            }
        } catch (NullPointerException var8) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var8);
            } else {
                throw var8;
            }
        } catch (Exception var9) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var9);
            } else {
                throw SqlUtils.toSQLException(var9);
            }
        }
    }

    public synchronized PreparedStatement prepareStatement(String a) throws SQLException {
        try {
            this.txn_known_resolved = false;
            PreparedStatement exc;
            if (this.parentPooledConnection.isStatementCaching()) {
                try {
                    Class[] e = new Class[]{String.class};
                    Method method = Connection.class.getMethod("prepareStatement", e);
                    Object[] args = new Object[]{a};
                    exc = (PreparedStatement) this.parentPooledConnection.checkoutStatement(method, args);
                    return new NewProxyPreparedStatement(exc, this.parentPooledConnection, true, this);
                } catch (ResourceClosedException var6) {
                    if (logger.isLoggable(MLevel.FINE)) {
                        logger.log(MLevel.FINE, "A Connection tried to prepare a Statement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", var6);
                    }

                    exc = this.inner.prepareStatement(a);
                    this.parentPooledConnection.markActiveUncachedStatement(exc);
                    return new NewProxyPreparedStatement(exc, this.parentPooledConnection, false, this);
                }
            } else {
                exc = this.inner.prepareStatement(a);
                this.parentPooledConnection.markActiveUncachedStatement(exc);
                return new NewProxyPreparedStatement(exc, this.parentPooledConnection, false, this);
            }
        } catch (NullPointerException var7) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var7);
            } else {
                throw var7;
            }
        } catch (Exception var8) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var8);
            } else {
                throw SqlUtils.toSQLException(var8);
            }
        }
    }

    public synchronized PreparedStatement prepareStatement(String a, int b, int c) throws SQLException {
        try {
            this.txn_known_resolved = false;
            PreparedStatement exc;
            if (this.parentPooledConnection.isStatementCaching()) {
                try {
                    Class[] e = new Class[]{String.class, Integer.TYPE, Integer.TYPE};
                    Method method = Connection.class.getMethod("prepareStatement", e);
                    Object[] args = new Object[]{a, new Integer(b), new Integer(c)};
                    exc = (PreparedStatement) this.parentPooledConnection.checkoutStatement(method, args);
                    return new NewProxyPreparedStatement(exc, this.parentPooledConnection, true, this);
                } catch (ResourceClosedException var8) {
                    if (logger.isLoggable(MLevel.FINE)) {
                        logger.log(MLevel.FINE, "A Connection tried to prepare a Statement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", var8);
                    }

                    exc = this.inner.prepareStatement(a, b, c);
                    this.parentPooledConnection.markActiveUncachedStatement(exc);
                    return new NewProxyPreparedStatement(exc, this.parentPooledConnection, false, this);
                }
            } else {
                exc = this.inner.prepareStatement(a, b, c);
                this.parentPooledConnection.markActiveUncachedStatement(exc);
                return new NewProxyPreparedStatement(exc, this.parentPooledConnection, false, this);
            }
        } catch (NullPointerException var9) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var9);
            } else {
                throw var9;
            }
        } catch (Exception var10) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var10);
            } else {
                throw SqlUtils.toSQLException(var10);
            }
        }
    }

    public synchronized PreparedStatement prepareStatement(String a, int b, int c, int d) throws SQLException {
        try {
            this.txn_known_resolved = false;
            PreparedStatement exc;
            if (this.parentPooledConnection.isStatementCaching()) {
                try {
                    Class[] e = new Class[]{String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE};
                    Method method = Connection.class.getMethod("prepareStatement", e);
                    Object[] args = new Object[]{a, new Integer(b), new Integer(c), new Integer(d)};
                    exc = (PreparedStatement) this.parentPooledConnection.checkoutStatement(method, args);
                    return new NewProxyPreparedStatement(exc, this.parentPooledConnection, true, this);
                } catch (ResourceClosedException var9) {
                    if (logger.isLoggable(MLevel.FINE)) {
                        logger.log(MLevel.FINE, "A Connection tried to prepare a Statement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", var9);
                    }

                    exc = this.inner.prepareStatement(a, b, c, d);
                    this.parentPooledConnection.markActiveUncachedStatement(exc);
                    return new NewProxyPreparedStatement(exc, this.parentPooledConnection, false, this);
                }
            } else {
                exc = this.inner.prepareStatement(a, b, c, d);
                this.parentPooledConnection.markActiveUncachedStatement(exc);
                return new NewProxyPreparedStatement(exc, this.parentPooledConnection, false, this);
            }
        } catch (NullPointerException var10) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var10);
            } else {
                throw var10;
            }
        } catch (Exception var11) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var11);
            } else {
                throw SqlUtils.toSQLException(var11);
            }
        }
    }

    public synchronized PreparedStatement prepareStatement(String a, int b) throws SQLException {
        try {
            this.txn_known_resolved = false;
            PreparedStatement exc;
            if (this.parentPooledConnection.isStatementCaching()) {
                try {
                    Class[] e = new Class[]{String.class, Integer.TYPE};
                    Method method = Connection.class.getMethod("prepareStatement", e);
                    Object[] args = new Object[]{a, new Integer(b)};
                    exc = (PreparedStatement) this.parentPooledConnection.checkoutStatement(method, args);
                    return new NewProxyPreparedStatement(exc, this.parentPooledConnection, true, this);
                } catch (ResourceClosedException var7) {
                    if (logger.isLoggable(MLevel.FINE)) {
                        logger.log(MLevel.FINE, "A Connection tried to prepare a Statement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", var7);
                    }

                    exc = this.inner.prepareStatement(a, b);
                    this.parentPooledConnection.markActiveUncachedStatement(exc);
                    return new NewProxyPreparedStatement(exc, this.parentPooledConnection, false, this);
                }
            } else {
                exc = this.inner.prepareStatement(a, b);
                this.parentPooledConnection.markActiveUncachedStatement(exc);
                return new NewProxyPreparedStatement(exc, this.parentPooledConnection, false, this);
            }
        } catch (NullPointerException var8) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var8);
            } else {
                throw var8;
            }
        } catch (Exception var9) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var9);
            } else {
                throw SqlUtils.toSQLException(var9);
            }
        }
    }

    public synchronized PreparedStatement prepareStatement(String a, int[] b) throws SQLException {
        try {
            this.txn_known_resolved = false;
            PreparedStatement exc;
            if (this.parentPooledConnection.isStatementCaching()) {
                try {
                    Class[] e = new Class[]{String.class, int[].class};
                    Method method = Connection.class.getMethod("prepareStatement", e);
                    Object[] args = new Object[]{a, b};
                    exc = (PreparedStatement) this.parentPooledConnection.checkoutStatement(method, args);
                    return new NewProxyPreparedStatement(exc, this.parentPooledConnection, true, this);
                } catch (ResourceClosedException var7) {
                    if (logger.isLoggable(MLevel.FINE)) {
                        logger.log(MLevel.FINE, "A Connection tried to prepare a Statement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", var7);
                    }

                    exc = this.inner.prepareStatement(a, b);
                    this.parentPooledConnection.markActiveUncachedStatement(exc);
                    return new NewProxyPreparedStatement(exc, this.parentPooledConnection, false, this);
                }
            } else {
                exc = this.inner.prepareStatement(a, b);
                this.parentPooledConnection.markActiveUncachedStatement(exc);
                return new NewProxyPreparedStatement(exc, this.parentPooledConnection, false, this);
            }
        } catch (NullPointerException var8) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var8);
            } else {
                throw var8;
            }
        } catch (Exception var9) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var9);
            } else {
                throw SqlUtils.toSQLException(var9);
            }
        }
    }

    public synchronized CallableStatement prepareCall(String a, int b, int c, int d) throws SQLException {
        try {
            this.txn_known_resolved = false;
            CallableStatement exc;
            if (this.parentPooledConnection.isStatementCaching()) {
                try {
                    Class[] e = new Class[]{String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE};
                    Method method = Connection.class.getMethod("prepareCall", e);
                    Object[] args = new Object[]{a, new Integer(b), new Integer(c), new Integer(d)};
                    exc = (CallableStatement) this.parentPooledConnection.checkoutStatement(method, args);
                    return new NewProxyCallableStatement(exc, this.parentPooledConnection, true, this);
                } catch (ResourceClosedException var9) {
                    if (logger.isLoggable(MLevel.FINE)) {
                        logger.log(MLevel.FINE, "A Connection tried to prepare a CallableStatement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", var9);
                    }

                    exc = this.inner.prepareCall(a, b, c, d);
                    this.parentPooledConnection.markActiveUncachedStatement(exc);
                    return new NewProxyCallableStatement(exc, this.parentPooledConnection, false, this);
                }
            } else {
                exc = this.inner.prepareCall(a, b, c, d);
                this.parentPooledConnection.markActiveUncachedStatement(exc);
                return new NewProxyCallableStatement(exc, this.parentPooledConnection, false, this);
            }
        } catch (NullPointerException var10) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var10);
            } else {
                throw var10;
            }
        } catch (Exception var11) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var11);
            } else {
                throw SqlUtils.toSQLException(var11);
            }
        }
    }

    public synchronized CallableStatement prepareCall(String a, int b, int c) throws SQLException {
        try {
            this.txn_known_resolved = false;
            CallableStatement exc;
            if (this.parentPooledConnection.isStatementCaching()) {
                try {
                    Class[] e = new Class[]{String.class, Integer.TYPE, Integer.TYPE};
                    Method method = Connection.class.getMethod("prepareCall", e);
                    Object[] args = new Object[]{a, new Integer(b), new Integer(c)};
                    exc = (CallableStatement) this.parentPooledConnection.checkoutStatement(method, args);
                    return new NewProxyCallableStatement(exc, this.parentPooledConnection, true, this);
                } catch (ResourceClosedException var8) {
                    if (logger.isLoggable(MLevel.FINE)) {
                        logger.log(MLevel.FINE, "A Connection tried to prepare a CallableStatement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", var8);
                    }

                    exc = this.inner.prepareCall(a, b, c);
                    this.parentPooledConnection.markActiveUncachedStatement(exc);
                    return new NewProxyCallableStatement(exc, this.parentPooledConnection, false, this);
                }
            } else {
                exc = this.inner.prepareCall(a, b, c);
                this.parentPooledConnection.markActiveUncachedStatement(exc);
                return new NewProxyCallableStatement(exc, this.parentPooledConnection, false, this);
            }
        } catch (NullPointerException var9) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var9);
            } else {
                throw var9;
            }
        } catch (Exception var10) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var10);
            } else {
                throw SqlUtils.toSQLException(var10);
            }
        }
    }

    public synchronized CallableStatement prepareCall(String a) throws SQLException {
        try {
            this.txn_known_resolved = false;
            CallableStatement exc;
            if (this.parentPooledConnection.isStatementCaching()) {
                try {
                    Class[] e = new Class[]{String.class};
                    Method method = Connection.class.getMethod("prepareCall", e);
                    Object[] args = new Object[]{a};
                    exc = (CallableStatement) this.parentPooledConnection.checkoutStatement(method, args);
                    return new NewProxyCallableStatement(exc, this.parentPooledConnection, true, this);
                } catch (ResourceClosedException var6) {
                    if (logger.isLoggable(MLevel.FINE)) {
                        logger.log(MLevel.FINE, "A Connection tried to prepare a CallableStatement via a Statement cache that is already closed. This can happen -- rarely -- if a DataSource is closed or reset() while Connections are checked-out and in use.", var6);
                    }

                    exc = this.inner.prepareCall(a);
                    this.parentPooledConnection.markActiveUncachedStatement(exc);
                    return new NewProxyCallableStatement(exc, this.parentPooledConnection, false, this);
                }
            } else {
                exc = this.inner.prepareCall(a);
                this.parentPooledConnection.markActiveUncachedStatement(exc);
                return new NewProxyCallableStatement(exc, this.parentPooledConnection, false, this);
            }
        } catch (NullPointerException var7) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var7);
            } else {
                throw var7;
            }
        } catch (Exception var8) {
            if (!this.isDetached()) {
                throw this.parentPooledConnection.handleThrowable(var8);
            } else {
                throw SqlUtils.toSQLException(var8);
            }
        }
    }

    public synchronized DatabaseMetaData getMetaData() throws SQLException {
        try {
            this.txn_known_resolved = false;
            if (this.metaData == null) {
                DatabaseMetaData exc = this.inner.getMetaData();
                this.metaData = new NewProxyDatabaseMetaData(exc, this.parentPooledConnection, this);
            }

            return this.metaData;
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized void setTransactionIsolation(int a) throws SQLException {
        try {
            this.inner.setTransactionIsolation(a);
            this.parentPooledConnection.markNewTxnIsolation(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var3);
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

    public synchronized void setCatalog(String a) throws SQLException {
        try {
            this.inner.setCatalog(a);
            this.parentPooledConnection.markNewCatalog(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var3);
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

    public synchronized void setHoldability(int a) throws SQLException {
        try {
            this.inner.setHoldability(a);
            this.parentPooledConnection.markNewHoldability(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var3);
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

    public synchronized void setTypeMap(Map a) throws SQLException {
        try {
            this.inner.setTypeMap(a);
            this.parentPooledConnection.markNewTypeMap(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var3);
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

    public synchronized void commit() throws SQLException {
        try {
            this.txn_known_resolved = true;
            this.inner.commit();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized void rollback(Savepoint a) throws SQLException {
        try {
            this.txn_known_resolved = true;
            this.inner.rollback(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var3);
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

    public synchronized void rollback() throws SQLException {
        try {
            this.txn_known_resolved = true;
            this.inner.rollback();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized void setAutoCommit(boolean a) throws SQLException {
        try {
            this.txn_known_resolved = true;
            this.inner.setAutoCommit(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var3);
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

    public synchronized SQLWarning getWarnings() throws SQLException {
        try {
            this.txn_known_resolved = false;
            return this.inner.getWarnings();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized void clearWarnings() throws SQLException {
        try {
            this.txn_known_resolved = false;
            this.inner.clearWarnings();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized String nativeSQL(String a) throws SQLException {
        try {
            this.txn_known_resolved = false;
            return this.inner.nativeSQL(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var3);
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

    public synchronized boolean getAutoCommit() throws SQLException {
        try {
            this.txn_known_resolved = false;
            return this.inner.getAutoCommit();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized String getCatalog() throws SQLException {
        try {
            this.txn_known_resolved = false;
            return this.inner.getCatalog();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized int getTransactionIsolation() throws SQLException {
        try {
            this.txn_known_resolved = false;
            return this.inner.getTransactionIsolation();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized Map getTypeMap() throws SQLException {
        try {
            this.txn_known_resolved = false;
            return this.inner.getTypeMap();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized int getHoldability() throws SQLException {
        try {
            this.txn_known_resolved = false;
            return this.inner.getHoldability();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized Savepoint setSavepoint() throws SQLException {
        try {
            this.txn_known_resolved = false;
            return this.inner.setSavepoint();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized Savepoint setSavepoint(String a) throws SQLException {
        try {
            this.txn_known_resolved = false;
            return this.inner.setSavepoint(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var3);
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

    public synchronized void releaseSavepoint(Savepoint a) throws SQLException {
        try {
            this.txn_known_resolved = false;
            this.inner.releaseSavepoint(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var3);
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

    public synchronized void setReadOnly(boolean a) throws SQLException {
        try {
            this.inner.setReadOnly(a);
            this.parentPooledConnection.markNewReadOnly(a);
        } catch (NullPointerException var3) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var3);
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

    public synchronized boolean isReadOnly() throws SQLException {
        try {
            this.txn_known_resolved = false;
            return this.inner.isReadOnly();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public synchronized void close() throws SQLException {
        try {
            if (!this.isDetached()) {
                NewPooledConnection exc = this.parentPooledConnection;
                this.detach();
                exc.markClosedProxyConnection(this, this.txn_known_resolved);
                this.inner = null;
            } else if (logger.isLoggable(MLevel.FINE)) {
                logger.log(MLevel.FINE, this + ": close() called more than once.");
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

    public synchronized boolean isClosed() throws SQLException {
        try {
            return this.isDetached();
        } catch (NullPointerException var2) {
            if (this.isDetached()) {
                throw SqlUtils.toSQLException("You can\'t operate on a closed Connection!!!", var2);
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

    public Object rawConnectionOperation(Method m, Object target, Object[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
        this.maybeDirtyTransaction();
        if (this.inner == null) {
            throw new SQLException("You cannot operate on a closed Connection!");
        } else {
            if (target == C3P0ProxyConnection.RAW_CONNECTION) {
                target = this.inner;
            }

            int out = 0;

            for (int innerRs = args.length; out < innerRs; ++out) {
                if (args[out] == C3P0ProxyConnection.RAW_CONNECTION) {
                    args[out] = this.inner;
                }
            }

            Object var6 = m.invoke(target, args);
            if (var6 instanceof CallableStatement) {
                CallableStatement var7 = (CallableStatement) var6;
                this.parentPooledConnection.markActiveUncachedStatement(var7);
                var6 = new NewProxyCallableStatement(var7, this.parentPooledConnection, false, this);
            } else if (var6 instanceof PreparedStatement) {
                PreparedStatement var8 = (PreparedStatement) var6;
                this.parentPooledConnection.markActiveUncachedStatement(var8);
                var6 = new NewProxyPreparedStatement(var8, this.parentPooledConnection, false, this);
            } else if (var6 instanceof Statement) {
                Statement var9 = (Statement) var6;
                this.parentPooledConnection.markActiveUncachedStatement(var9);
                var6 = new NewProxyStatement(var9, this.parentPooledConnection, false, this);
            } else if (var6 instanceof ResultSet) {
                ResultSet var10 = (ResultSet) var6;
                this.parentPooledConnection.markActiveRawConnectionResultSet(var10);
                var6 = new NewProxyResultSet(var10, this.parentPooledConnection, this.inner, this);
            } else if (var6 instanceof DatabaseMetaData) {
                var6 = new NewProxyDatabaseMetaData((DatabaseMetaData) var6, this.parentPooledConnection);
            }

            return var6;
        }
    }

    synchronized void maybeDirtyTransaction() {
        this.txn_known_resolved = false;
    }

    void attach(NewPooledConnection parentPooledConnection) {
        this.parentPooledConnection = parentPooledConnection;
        parentPooledConnection.addConnectionEventListener(this.cel);
    }

    private void detach() {
        this.parentPooledConnection.removeConnectionEventListener(this.cel);
        this.parentPooledConnection = null;
    }

    NewProxyConnection(Connection inner, NewPooledConnection parentPooledConnection) {
        this(inner);
        this.attach(parentPooledConnection);
    }

    boolean isDetached() {
        return this.parentPooledConnection == null;
    }
}
