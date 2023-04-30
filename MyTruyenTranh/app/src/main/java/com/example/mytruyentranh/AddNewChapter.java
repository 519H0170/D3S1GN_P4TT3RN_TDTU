package com.example.mytruyentranh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class AddNewChapter extends AppCompatActivity {

    EditText TimTruyen;
    TextView TruyenThemChuong, TongSoChuong;
    Button btnTimTruyen, check;
    ImageView HienAnh;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_chapter);

        TimTruyen = findViewById(R.id.TimTruyen);
        TruyenThemChuong = findViewById(R.id.TruyenThemChuong);
        TongSoChuong = findViewById(R.id.TongSoChuong);
        btnTimTruyen = findViewById(R.id.btnTimTruyen);
        HienAnh = findViewById(R.id.HienAnh);

        btnTimTruyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeTruyen();
            }
        });

    }

    public void takeTruyen(){
        String TenTruyen = TimTruyen.getText().toString();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TruyenTranh");
        storageReference = FirebaseStorage.getInstance().getReference("TruyenTranh/" + TenTruyen + "/" + TenTruyen + ".jpg");

        Query checkTruyen = reference.orderByChild("name").equalTo(TenTruyen);
        checkTruyen.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    TimTruyen.setError(null);
                    String name = snapshot.child(TenTruyen).child("name").getValue(String.class);
                    int chapter = snapshot.child(TenTruyen).child("chap").getValue(int.class);

                    TruyenThemChuong.setText(name);
                    TongSoChuong.setText("Số Chương: "+chapter + ". Nhấn Để Thêm Chương Mới");

                    TruyenTranh truyenTranh = new TruyenTranh();
                    truyenTranh = snapshot.child(TenTruyen).getValue(TruyenTranh.class);
                    truyenTranh.newChap();

                    FirebaseDatabase database;
                    DatabaseReference ref;
                    database = FirebaseDatabase.getInstance();
                    ref = database.getReference("TruyenTranh");

                    ref.child(TenTruyen).setValue(truyenTranh);

                    try {
                        File localfile = File.createTempFile("tempfile", ".jpg");
                        storageReference.getFile(localfile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                        HienAnh.setImageBitmap(bitmap);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(AddNewChapter.this, "Không Lấy Được Ảnh", Toast.LENGTH_SHORT);
                                    }
                                });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(AddNewChapter.this, ThemChuong.class);
                    intent.putExtra("name", name);
                    int a= chapter+1;
                    intent.putExtra("chapter", "chap "+a);
                    startActivity(intent);
                }else {
                    TimTruyen.setError("Không Tìm Thấy Truyện");
                    TimTruyen.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}