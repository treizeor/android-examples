package com.example.service;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

	private Integer id;
	private String name;
	private String pass;

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(Integer id, String name, String pass) {
		// TODO Auto-generated constructor stub
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		// 搞不懂这是干嘛的。。重写了equals()? 貌似没必要啊。。。
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		Person other = (Person) o;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (pass == null) {
			if (other.pass != null) {
				return false;
			}
		} else if (!pass.equals(other.pass)) {
			return false;
		}

		return true;
	}

	// 重写Parcelable接口必须实现的方法
	@Override
	public int describeContents() {

		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// 把该对象所包含的数据写到Parcel
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeString(pass);
	}

	// 添加一个静态成员，名为CREATOR，该对象实现了Parcelable.Creator接口
	public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {

		@Override
		public Person createFromParcel(Parcel source) {
			// 从Parcel中读取数据，放回Person对象
			return new Person(source.readInt(), source.readString(),
					source.readString());
		}

		@Override
		public Person[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Person[size];
		}
	};

}
