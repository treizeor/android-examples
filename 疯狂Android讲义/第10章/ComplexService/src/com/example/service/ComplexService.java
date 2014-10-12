package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.service.IPet.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class ComplexService extends Service {
	private PetBinder petBinder;
	private static Map<Person, List<Pet>> pets = new HashMap<Person, List<Pet>>();

	static {
		ArrayList<Pet> list1 = new ArrayList<Pet>();
		list1.add(new Pet("旺财", 4.3));
		list1.add(new Pet("来福", 5.1));
		pets.put(new Person(1, "sun", "sun"), list1);
		ArrayList<Pet> list2 = new ArrayList<Pet>();
		list2.add(new Pet("kitty", 2.3));
		list2.add(new Pet("garfield", 3.1));
		pets.put(new Person(2, "bai", "bai"), list2);

	}

	public class PetBinder extends Stub {
		@Override
		public List<Pet> getPets(Person owner) throws RemoteException {
			// 返回Service内部数据
			return pets.get(owner);
		}

	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		petBinder = new PetBinder();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return petBinder;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
