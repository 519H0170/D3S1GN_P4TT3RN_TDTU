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

public class SignUpAct extends AppCompatActivity {

    Button TaoTK;
    EditText email, taikhoan, pass, xacthucpass;
    TextView TroVe;

    FirebaseDatabase database;
    DatabaseReference reference;

    IValidation valid = new ValidString();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TaoTK = findViewById(R.id.TaoTaiKhoan);
        TroVe = findViewById(R.id.QuayVe);
        email = findViewById(R.id.TaoEmail);
        taikhoan = findViewById(R.id.TaoTenDangNhap);
        pass = findViewById(R.id.TaoMatKhau);
        xacthucpass = findViewById(R.id.XacNhanMatKhau);

        TaoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!valids() || !SamePass() || !Gmail()){

                }else {
                    addDatabase();
                }
            }
        });
        TroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpAct.this, LogInAct.class);
                startActivity(intent);

            }
        });
    }

    public void addDatabase(){
        /*database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users");*/

        String Username = taikhoan.getText().toString();
        String Email = email.getText().toString();
        String Password = pass.getText().toString();

        HelpData helpData = new HelpData(Email, Username, Password);
        //reference.child(Username).setValue(helpData);
        AddData addData = new addUserData(helpData);
        addData.setData();


        Toast.makeText(SignUpAct.this, "Bạn Tạo Tài Khoản Thành Công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignUpAct.this, LogInAct.class);
        startActivity(intent);
    }

    public boolean valids(){
        if(!valid.valid(email.getText().toString()) || !valid.valid(taikhoan.getText().toString()) || !valid.valid(pass.getText().toString())
            || !valid.valid(xacthucpass.getText().toString())){
            Toast.makeText(SignUpAct.this, "Vui Lòng Điền Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean SamePass(){
        if(!valid.valid(xacthucpass.getText().toString(), pass.getText().toString())){
            xacthucpass.setError("Mật Khẩu Không Khớp");
            return false;
        }else{
            xacthucpass.setError(null);
            return true;
        }
    }

    public boolean Gmail(){
        IValidation valid = new ValidGmail();
        if(!valid.valid(email.getText().toString())){
            email.setError("Đây không phải là Email");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }
    }
}