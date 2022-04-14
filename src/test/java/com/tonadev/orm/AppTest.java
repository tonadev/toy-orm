package com.tonadev.orm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Hashtable;

import com.tonadev.orm.TestModel;
import com.tonadev.orm.Table;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldGetCreateSql()
    {
		String expectedSql = "CREATE TABLE testmodel (id INT PRIMARY KEY AUTOINCREMENT, name TEXT);";
		String actualSql = Table.getCreateSql(TestModel.class);
		assertEquals("failure - strings are not equal", expectedSql, actualSql);
    }

	@Test
	public void shouldGetSelectAllSql()
	{
		String expectedSql = "SELECT * FROM testmodel;";
		String actualSql = Table.getSelectAllSql(TestModel.class);
		assertEquals("failure - strings are not equal", expectedSql, actualSql);
	}

	@Test
	public void shouldGetSelectWhereSql()
	{
		Hashtable<String, String> filters = new Hashtable<String, String>();
		filters.put("id", "1");
		filters.put("name", "Test");
		String expectedSql = "SELECT * FROM testmodel WHERE name = \"Test\" AND id = 1;";
		String actualSql = Table.getSelectWhereSql(TestModel.class, filters);
		assertEquals("failure - strings are not equal", expectedSql, actualSql);
	}

	@Test
	public void shouldGetInsertSql()
	{
		TestModel testModel = new TestModel("Test");
		String expectedSql = "INSERT INTO testmodel (name) VALUES (\"Test\");";
		String actualSql = testModel.getInsertSql();
		assertEquals("failre - string are not equal", expectedSql, actualSql);
	}
}
