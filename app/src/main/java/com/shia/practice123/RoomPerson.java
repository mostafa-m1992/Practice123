package com.shia.practice123;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_one")
public class RoomPerson {

    @PrimaryKey
    @NonNull
    private String id;
    private String name;

    public RoomPerson(@NonNull String id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoomPerson() {
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
