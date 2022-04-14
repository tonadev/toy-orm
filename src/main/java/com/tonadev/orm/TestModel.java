package com.tonadev.orm;

import com.tonadev.orm.Table;
import com.tonadev.orm.SQLType;

class TestModel extends Table {
	@Column (
		primaryKey = true,
		sqlType = SQLType.INT
	) 
	private int id;
	@Column (
		sqlType = SQLType.TEXT
	)
	private String name;

	public TestModel(String name) {
		this.name = name;
	}

	public int getId() { return this.id; }
	public String getName() { return this.name; }

	public void setId(int id) { this.id = id; }
	public void setName(String name) { this.name = name; }
}
