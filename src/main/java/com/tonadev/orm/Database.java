package com.tonadev.orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Hashtable;

import com.tonadev.orm.Table;

public class Database {
    public static final String SELECT_TABLES_SQL = "SHOW TABLES;";
    public static final String CREATE_TABLE_SQL = "CREATE TABLE %s (%s);";
    public static final String SELECT_ALL_SQL = "SELECT * FROM %s;";
    public static final String SELECT_WHERE_SQL = "SELECT * FROM %s WHERE %s;";
    public static final String INSERT_SQL = "INSERT INTO %s (%s) VALUES (%s);";

    public Connection getConnection() {
        Connection conn = null;

		final String url = "jdbc:mysql://localhost:3306/";
		final String dbName = "test";
		final String driver = "com.mysql.cj.jdbc.Driver";
		final String userName = "root";
		final String password = "";

        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
        } catch (Exception e) {
            System.err.println("Exception " + e.getMessage());
        }

        return conn;
    }

    public ResultSet execute(Connection conn, String sql) {
        try {
            if (!conn.isClosed()) {
                Statement stmt = conn.createStatement();

                if (stmt.execute(sql)) {
                    return stmt.getResultSet();
                }
            }
		} catch (Exception e) {
			System.err.println("Exception " + e.getMessage());
		}

        return null;
    }

    public String[] getTables() {
        Connection conn = this.getConnection();
        ResultSet rs = this.execute(conn, Database.SELECT_TABLES_SQL);

        ArrayList<String> tableNames = new ArrayList<String>();

        try { // rs.next() could throws an SQLException
            if (rs != null) {
                while (rs.next()) {
                    tableNames.add(rs.getString(1));
                }
            }
        } catch (Exception e) {
            System.err.println("Exception " + e.getMessage());
        } finally {
            try {
				if (conn != null) {
					conn.close();
				} 
			} catch (SQLException e) {

			}
        }
        
        String[] namesArr = new String[tableNames.size()];
        namesArr = tableNames.toArray(namesArr);

        return namesArr;
    }

    public void create(Class<? extends Table> tbl) {
        Connection conn = this.getConnection();
        try {
            this.execute(conn, Table.getCreateSql(tbl));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void save(Table tbl) {
        Connection conn = this.getConnection();
        try {
            this.execute(conn, tbl.getInsertSql());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Hashtable<String, String> all(Class<? extends Table> tbl) {
        Connection conn = this.getConnection();
        Hashtable<String, String> data = null;

        try {
            ResultSet rs = this.execute(conn, Table.getSelectAllSql(tbl));
            data = new Hashtable<String, String>();
            if (rs != null) {
                while (rs.next()) {
                    for (Field field : tbl.getDeclaredFields()) {
                        String fieldName = field.getName();
                        data.put(fieldName, rs.getString(fieldName));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public Hashtable<String, String> get(Class<? extends Table> tbl, int id) {
        Connection conn = this.getConnection();
        Hashtable<String, String> data = null;
        
        Hashtable<String, String> filters = new Hashtable<String, String>();
        filters.put("id", Integer.toString(id));

        try {
            ResultSet rs = this.execute(conn, Table.getSelectWhereSql(tbl, filters));
            data = new Hashtable<String, String>();
            if (rs != null) {
                while (rs.next()) {
                    for (Field field : tbl.getDeclaredFields()) {
                        String fieldName = field.getName();
                        data.put(fieldName, rs.getString(fieldName));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    } 
}
