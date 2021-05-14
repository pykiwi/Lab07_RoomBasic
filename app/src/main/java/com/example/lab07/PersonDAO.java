package com.example.lab07;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDAO {
    @Query("SELECT * FROM person")
    List<Person> getAll();
    @Insert
    void addPerson(Person person);
    @Delete
    void deletePerson(Person person);

    Person getPersonById(int id);
}
