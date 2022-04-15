package com.tonadev.orm;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /* 
        Database db = new Database();
        for (String s : db.getTables()) {
            System.out.println(s);
        }
        // db.create(Test.class);
        Test test = new Test("Hello");
        // db.save(test);
        // Hashtable<String, String> data = db.all(Test.class);
        Hashtable<String, String> data = db.get(Test.class, 2);
        Enumeration<String> e = data.keys();
        while (e.hasMoreElements()) {
            String key = e.nextElement();

            System.out.println(key + ": " + data.get(key));
        }

        System.out.println(test.getCreateSql(Test.class));
        System.out.println(test.getSelectAllSql(Test.class));

        Hashtable<String, String> filters = new Hashtable<String, String>();
        filters.put("id", "1");
        filters.put("name", "Greg");

        System.out.println(test.getSelectWhereSql(Test.class, filters));
        System.out.println(test.getInsertSql());

        */
        System.out.println( "Hello World!" );
    }
}
