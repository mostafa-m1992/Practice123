package com.shia.practice123;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoomDao {

    @Query("select * from table_one")
    List<RoomPerson> getAll();

    @Query("select * from table_one where name = :strNameDao")
    List<RoomPerson> searchByName(String strNameDao);

    @Query("select * from table_one where name like '%' || :n || '%'")
    List<RoomPerson> searchByLikeOnName(String n);

    @Query("select * from table_one where id = :Id")
    RoomPerson searchById(int Id);

    @Insert
    Long insert(RoomPerson person);

    @Update
    int update(RoomPerson person);

    @Delete
    int delete(RoomPerson person);

    @Query("select count(*) from table_one")
    int countRoomPersons();
}
