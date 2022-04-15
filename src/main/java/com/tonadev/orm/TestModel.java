package com.tonadev.orm;

import java.util.Hashtable;

import com.tonadev.orm.Table;
import com.tonadev.orm.types.SQLType;
import com.tonadev.orm.types.Column;

class TestModel extends Table {
	@Column (
		primaryKey = true,
        autoincrement = true,
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

    public Hashtable<String, String> toDict() {
        Hashtable<String, String> data = new Hashtable<String, String>();
        data.put("id", Integer.toString(this.id));
        data.put("name", this.name);

        return data;
    }
}
