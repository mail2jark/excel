package org.buzheng.commons.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.buzheng.excel.Column;
import org.buzheng.excel.ExcelBuilder;
import org.buzheng.excel.FieldFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExcelBuilderTest {
	
	private int max = 1000;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("======================== setUpBeforeClass");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("======================== tearDownAfterClass");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("======================== setUp");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("======================== tearDown");
	}

	@Test
	public void testMapToFile() throws IOException {
		
		List<Map<String, Object>> users = getMapData();
		
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("姓名", "name"));		
		cs.add(new Column("年龄", "age"));
		cs.add(new Column("性别", "sex", new FieldFormatter<Integer,  Map<String, Object>>() {
			public Object format(Integer age, Map<String, Object> user) {
				return age == null ? "" : (age.intValue() == 0 ? "男" : "女");
			}}));
		
		cs.add(new Column("生日", "birthday", "yyyy-MM-dd"));
		
		cs.add(new Column("财产", "balance", "#,##0.00", true));
		
		long start = System.currentTimeMillis();
		ExcelBuilder<Map<String, Object>> eb = new ExcelBuilder<Map<String, Object>>();
		eb.setData(users);
		eb.setColumns(cs);
		eb.setCaption("用户信息表");
		
		eb.toFile("D:\\users_map.xlsx");
		
		System.out.println(System.currentTimeMillis() - start);
		System.out.println("已导出");
		
	}
	
	@Test
	public void testToFile() throws IOException {
		
		
		List<User> users = getData();
		
		List<Column> cs = new ArrayList<Column>();
		cs.add(new Column("姓名", "name"));		
		cs.add(new Column("年龄", "age"));
		cs.add(new Column("性别", "sex", new FieldFormatter<Integer, User>() {
			public Object format(Integer age, User user) {
				return age == null ? "" : (age.intValue() == 0 ? "男" : "女");
			}}));
		
		cs.add(new Column("生日", "birthday", "yyyy-MM-dd"));
		
		cs.add(new Column("财产", "balance", "#,##0.00", true));
		
		long start = System.currentTimeMillis();
		ExcelBuilder<User> eb = new ExcelBuilder<User>();
		eb.setData(users);
		eb.setColumns(cs);
		eb.setCaption("用户信息表");
		
		eb.toFile("D:\\users.xlsx");
		
		System.out.println(System.currentTimeMillis() - start);
		System.out.println("已导出");
		
	}
	
	

	private List<Map<String, Object>> getMapData() {
		
		List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
		
		for (int i = 0; i < max; i++) {
			Map<String, Object> user = new HashMap<String, Object>();
			user.put("name", "张" + i);
			user.put("age", i);
			user.put("sex", 0);
			user.put("balance", 1100.1);
			user.put("birthday", new Date());
			
			users.add(user);
		}
		
		return users;
	}
	
	private List<User> getData() {
		List<User> users = new ArrayList<User>();
		
		for (int i = 0; i < max; i++) {
			User user = new User();
			user.setName("三天 - " + i);
			user.setAge(i + 1);
			user.setSex(0);
			user.setBalance(1100.1);
			user.setBirthday(new Date());
			
			users.add(user);
		}
		
		return users;
	}
	
}

class User {
	
	private String name;
	
	private Integer age;
	
	private Integer sex;
	
	private Date birthday;
	
	private Double balance;
	
	

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
}
