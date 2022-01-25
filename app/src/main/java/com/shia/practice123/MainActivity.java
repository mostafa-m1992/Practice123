package com.shia.practice123;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText roomName, roomId;
    Button roomInsert, roomShow, roomUpdate, roomDelete;

    TextView txtShowAll, txtSearchByName, txtSearchByLikeOnName, txtCount;
    Button btnShowAll, btnSearchByName, btnSearchByLikeOnName, btnCount;

    private RoomDao roomDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roomDao = AppDataBase.getAppDataBase(this).getRoomDao();

        roomId = findViewById(R.id.roomId);
        roomName = findViewById(R.id.roomName);

        roomInsert = findViewById(R.id.roomInsert);
        roomShow = findViewById(R.id.roomShow);
        roomUpdate = findViewById(R.id.roomUpdate);
        roomDelete = findViewById(R.id.roomDelete);

        roomInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strSaveId = roomId.getText().toString();
                String strSaveName = roomName.getText().toString();

                if (TextUtils.isEmpty(strSaveId) || TextUtils.isEmpty(strSaveName)){
                    Toast.makeText(getApplicationContext(), "insert your info", Toast.LENGTH_SHORT).show();
                } else {
                    RoomPerson roomPersonSave = new RoomPerson();
                    roomPersonSave.setId(strSaveId);
                    roomPersonSave.setName(strSaveName);

                    roomDao.insert(roomPersonSave);
                    Toast.makeText(getApplicationContext(), "your info saved", Toast.LENGTH_SHORT).show();
                }
            }
        });

        roomShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int strShowId = Integer.parseInt(roomId.getText().toString());
                RoomPerson roomPersonShow = roomDao.searchById(strShowId);
                roomName.setText(roomPersonShow.getName());
            }
        });

        roomUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUpdateId = roomId.getText().toString();
                String strUpdateName = roomName.getText().toString();

                RoomPerson roomPersonUpdate = new RoomPerson();
                roomPersonUpdate.setId(strUpdateId);
                roomPersonUpdate.setName(strUpdateName);

                roomDao.update(roomPersonUpdate);
            }
        });

        roomDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String strDeleteId = roomId.getText().toString();

                        RoomPerson roomPersonDelete = new RoomPerson();
                        roomPersonDelete.setId(strDeleteId);

                        long del = roomDao.delete(roomPersonDelete);

                        if (del == 0) {
                            return;
                        }
                    }
                });
            }
        });


        txtShowAll = findViewById(R.id.txtShowAll);
        txtSearchByName = findViewById(R.id.txtSearchByName);
        txtSearchByLikeOnName = findViewById(R.id.txtSearchByLikeOnName);

        btnShowAll = findViewById(R.id.btnShowAll);
        btnSearchByName = findViewById(R.id.btnSearchByName);
        btnSearchByLikeOnName = findViewById(R.id.btnSearchByLikeOnName);

        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<RoomPerson> list = new ArrayList<>();
                list = roomDao.getAll();

                txtShowAll.setText("");
                for (int i = 0; i < list.size(); i++){
                    txtShowAll.append("id: " + list.get(i).getId() + "  ,and name: " + list.get(i).getName() + "\n");
                }
            }
        });

        btnSearchByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strRoomName = roomName.getText().toString();
                List<RoomPerson> list = new ArrayList<>();
                list = roomDao.searchByName(strRoomName);

                txtSearchByName.setText("");
                for (int i = 0; i < list.size(); i++){
                    txtSearchByName.append("id: " + list.get(i).getId() + "  ,and name: " + list.get(i).getName() + "\n");
                }
            }
        });

        btnSearchByLikeOnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strRoomName = roomName.getText().toString();
                List<RoomPerson> list = new ArrayList<>();
                list = roomDao.searchByLikeOnName(strRoomName);

                txtSearchByLikeOnName.setText("");
                for (int i = 0; i < list.size(); i++){
                    txtSearchByLikeOnName.append("id: " + list.get(i).getId() + "  ,and name: " + list.get(i).getName() + "\n");
                }
            }
        });

        txtCount = findViewById(R.id.txtCount);
        btnCount = findViewById(R.id.btnCount);
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtCount.setText(roomDao.countRoomPersons());
            }
        });
    }
}