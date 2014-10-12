package com.example.service;

import com.example.service.Pet;
import com.example.service.Person;

interface IPet {
	List<Pet> getPets(in Person owner);
}