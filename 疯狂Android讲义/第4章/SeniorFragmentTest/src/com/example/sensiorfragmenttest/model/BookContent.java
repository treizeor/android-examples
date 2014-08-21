package com.example.sensiorfragmenttest.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookContent {

	// 定义一个内部类，作为系统的业务对象
	public static class Book{
		public Integer id;
		public String title;
		public String desc;
		public Book(Integer id, String title, String desc) {
			this.id = id;
			this.title = title;
			this.desc = desc;
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return title;
		}
	}
	
	// 使用List集合记录系统包含的Book对象
	public static List<Book> ITEMS = new ArrayList<BookContent.Book>();
	// 使用Map集合记录系统包含的Book对象
	public static Map<Integer, Book> ITEM_MAP = new HashMap<Integer, BookContent.Book>();
	static {
		//使用静态初始化代码，将Book对象添加到List集合，Map集合中
		addItem(new Book(1, "第一本书", "介绍没啥好讲的啊。关于爱情的吧"));
		addItem(new Book(2, "第二本书", "介绍没啥好讲的啊。关于泡妞的啦~~~~~~~~~"));
		addItem(new Book(3, "第三本书", "这是一本神奇的书，"));
	}
	private static void addItem(Book book) {
		ITEMS.add(book);
		ITEM_MAP.put(book.id, book);
	}
}
