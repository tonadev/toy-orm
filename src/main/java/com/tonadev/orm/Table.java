package com.tonadev.orm;

import java.lang.reflect.Field;
import java.lang.annotation.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Enumeration;

import com.tonadev.orm.Database;
import com.tonadev.orm.SQLType;

public abstract class Table {
	public static String getCreateSql(Class cls) {
		String className = cls.getSimpleName().toLowerCase();
		ArrayList<String> classFields = new ArrayList<String>();
		String test = "Dont work";

		try {
			for (Field field : cls.getDeclaredFields()) {
				String javaType = field.getGenericType().getTypeName();
				Column fieldAnnotation = field.getAnnotation(Column.class);
				String sqlType = fieldAnnotation.sqlType().getSqlType();

				if (fieldAnnotation.primaryKey()) {
						sqlType = sqlType.concat(" PRIMARY KEY AUTOINCREMENT");
				}

				classFields.add(String.format("%s %s", field.getName(), sqlType));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String fields = String.join(", ", classFields);
		return String.format(Database.CREATE_TABLE_SQL, className, fields);
	}

	public static String getSelectAllSql(Class cls) {
		String className = cls.getSimpleName().toLowerCase();
		return String.format(Database.SELECT_ALL_SQL, className);
	}

	public static String getSelectWhereSql(Class cls, Hashtable<String, String> filters) {
		String className = cls.getSimpleName().toLowerCase();
		ArrayList<String> sqlFilters = new ArrayList<String>();

		Enumeration<String> keys = filters.keys();

		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = filters.get(key);

			try {
				Field field = cls.getDeclaredField(key);
				Column fieldAnnotation = field.getAnnotation(Column.class);
				SQLType fieldType = fieldAnnotation.sqlType();
				if (fieldType == SQLType.TEXT) {
					sqlFilters.add(String.format("%s = \"%s\"", key, value));
				} else {
					sqlFilters.add(String.format("%s = %s", key, value));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String whereFilters = String.join(" AND ", sqlFilters);
		return String.format(Database.SELECT_WHERE_SQL, className, whereFilters);
	}

	public String getInsertSql() {
		String className = this.getClass().getSimpleName();

		ArrayList<String> classFields = new ArrayList<String>();
		ArrayList<String> instanceValues = new ArrayList<String>();

		try {
			for (Field field : this.getClass().getDeclaredFields()) {
				String fieldName = field.getName();

				classFields.add(fieldName);

				/* Get values
				String fieldNameCapitalized = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				String getFieldMethod = "get" + fieldNameCapitalized;
				String value = (String) this.getClass().getDeclaredMethods(getFieldMethod).invoke(this, null);
				instanceValues.add(value);
				*/

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String fields = String.join(", ", classFields);
		String values = String.join(", ", instanceValues);

		return String.format(Database.INSERT_SQL, className, fields, values);
	}
}
