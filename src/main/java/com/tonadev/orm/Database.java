package com.tonadev.orm;

public class Database {
	public static final String SELECT_TABLES_SQL = "SHOW TABLES;";
    public static final String CREATE_TABLE_SQL = "CREATE TABLE %s (%s);";
    public static final String SELECT_ALL_SQL = "SELECT * FROM %s;";
    public static final String SELECT_WHERE_SQL = "SELECT * FROM %s WHERE %s;";
    public static final String INSERT_SQL = "INSERT INTO %s (%s) VALUES (%s);";
}
