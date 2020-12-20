package com.umeng.soexample.ui.easemob;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.umeng.soexample.R;

/**
 * 查找用户
 */
public class FindUserActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputUserId;
    Button btnFind;
    RecyclerView recyUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_find_user);
        inputUserId = findViewById( R.id.input_userid);
        btnFind = findViewById( R.id.btn_find);
        recyUsers = findViewById( R.id.recy_users);
        btnFind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_find:
                break;
        }
    }
}