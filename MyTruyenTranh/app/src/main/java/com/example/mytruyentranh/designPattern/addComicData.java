package com.example.mytruyentranh.designPattern;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.mytruyentranh.TruyenTranh;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class addComicData extends AddData {
    private TruyenTranh truyenTranh;

    public addComicData(TruyenTranh truyenTranh){
        this.truyenTranh = truyenTranh;
    }

    @Override
    protected void setData() {
        Database("TruyenTranh");
        reference.child(this.truyenTranh.getName()).setValue(this.truyenTranh);
    }

    @Override
    protected void setIMG(Uri uri) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("User/" + this.truyenTranh.getName());
        final StorageReference imageReference = storageReference.child(this.truyenTranh.getName() + "." + getFileExtension(uri));

        imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }
}
