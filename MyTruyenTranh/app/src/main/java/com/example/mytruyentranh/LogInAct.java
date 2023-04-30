package com.example.mytruyentranh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LogInAct extends AppCompatActivity {

    Button DangNhap;
    TextView DangKy, forget;
    EditText user, password;
    IValidation valid = new ValidString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);

        DangNhap = findViewById(R.id.DangNhap);
        DangKy = findViewById(R.id.DangKy);
        forget = findViewById(R.id.Forget);
        user = findViewById(R.id.UserName);
        password = findViewById(R.id.Password);

        DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!valids()){

                }else{
                    checkUser();
                }
            }
        });
        DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInAct.this, SignUpAct.class);
                startActivity(intent);
            }
        });
    }

    public boolean valids(){
        if(!valid.valid(user.getText().toString()) || !valid.valid(password.getText().toString())){
            Toast.makeText(LogInAct.this, "Vui Lòng Điền Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void checkUser(){
        String Username = user.getText().toString();
        String Userpass = password.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkUserdb = reference.orderByChild("username").equalTo(Username);

        checkUserdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    user.setError(null);
                    String passwordFromDB = snapshot.child(Username).child("pass").getValue(String.class);
                    if (passwordFromDB.equals(Userpass)) {
                        user.setError(null);
                        String emailFromDB = snapshot.child(Username).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(Username).child("username").getValue(String.class);

                        Intent intent = new Intent(LogInAct.this, MainActivity.class);

                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("pass", passwordFromDB);
                        startActivity(intent);
                    } else {
                        password.setError("Invalid Credentials");
                        password.requestFocus();
                    }
                } else {
                    user.setError("User does not exist");
                    user.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}