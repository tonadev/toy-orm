package com.tonadev.orm;

public enum SQLType {
	TEXT ("java.lang.String", "TEXT"),
	INT ("int", "INT"),
	FLOAT ("float", "FLOAT"),
	BOOL ("boolean", "BOOL");
	
	private final String javaType;
	private final String sqlType;

	SQLType(String javaType, String sqlType) {
		this.javaType = javaType;
		this.sqlType = sqlType;
	}

	public String getJavaType() { return this.javaType;	}
	public String getSqlType() { return this.sqlType; }

	public static String getByJavaType(String javaType) {
		for (SQLType type : SQLType.values()) {
			if (type.getJavaType().equals(javaType)) {
				return type.getSqlType();
			}
		}
		return null;
	}
}
