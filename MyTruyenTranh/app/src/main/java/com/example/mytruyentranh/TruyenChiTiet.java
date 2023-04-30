package com.example.mytruyentranh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TruyenChiTiet extends AppCompatActivity {

    TextView comic, auth, categ, chap, detail;
    ImageView img;
    ListView chaplist;
    String TenTruyen = "DR STONE";
    String cat="";
    int Chapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen_chi_tiet);

        init();
        takeTruyen();
    }

    public void init(){
        comic = findViewById(R.id.ComicsName);
        auth = findViewById(R.id.AuthorName);
        categ = findViewById(R.id.CategoryName);
        chap = findViewById(R.id.NumChapter);
        detail = findViewById(R.id.detail);
        img = findViewById(R.id.Image);
        chaplist = findViewById(R.id.chaplist);
    }

    public void takeTruyen(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("TruyenTranh");
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("TruyenTranh/" + TenTruyen + "/" + TenTruyen + ".jpg");

        Query checkTruyen = reference.orderByChild("name").equalTo(TenTruyen);
        checkTruyen.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    //TruyenTranh truyenTranh = snapshot.child(TenTruyen).child("name").getValue(TruyenTranh.class);
                    GenericTypeIndicator<ArrayList<String>> typeIndicator = new GenericTypeIndicator<ArrayList<String>>(){};

                    TruyenTranh truyenTranh = snapshot.child(TenTruyen).getValue(TruyenTranh.class);
                    ArrayList<String> cate = snapshot.child(TenTruyen).child("category").getValue(typeIndicator);

                    Chapter = truyenTranh.getChap();

                    for(int i = 0; i < cate.size(); i++){
                        cat = cat +" " + cate.get(i).toString();
                    }

                    comic.setText(truyenTranh.getName());
                    chap.setText(""+truyenTranh.getChap());
                    detail.setText(truyenTranh.getDetail());
                    auth.setText(truyenTranh.getAuthor());
                    categ.setText(cat);
                    makeChapList();
                    //toData();
                    try {
                        File localfile = File.createTempFile("tempfile", ".jpg");
                        storageReference.getFile(localfile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        Bitmap bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                        img.setImageBitmap(bitmap);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(TruyenChiTiet.this, "Không Lấy Được Ảnh", Toast.LENGTH_SHORT);
                                    }
                                });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //kệ đoạn này
                    /*Intent intent = new Intent(TruyenChiTiet.this, ThemChuong.class);
                    intent.putExtra("name", truyenTranh.getName());
                    intent.putExtra("chapter", "chap "+truyenTranh.getChap());*/
                }else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void makeChapList(){
        ArrayList<String> data = new ArrayList();
        int a;
        for(int i = 0; i<Chapter; i++){
            a=i+1;
            data.add("chap " + a);
        }

        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                R.layout.my_list_chapter,
                R.id.chapter,
                data
        );

        chaplist.setAdapter(adapter);
    }

    //kệ đoạn này nha ông ơi
    public void toData(){
        ComicChapList chapList = new ComicChapList();

        for(int i = 0; i<Chapter; i++){
            Chap chap = new Chap(i+1);
            chapList.addChap(chap);
        }
        ArrayList<String> data = new ArrayList<String>(Arrays.asList(chapList.getChap().split(",")));

        ArrayAdapter adapter = new ArrayAdapter<>(
                this,
                R.layout.my_list_chapter,
                R.id.chapter,
                data
        );

        chaplist.setAdapter(adapter);
    }
}