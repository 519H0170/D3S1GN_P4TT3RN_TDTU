package com.example.mytruyentranh.designPattern;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.mytruyentranh.HelpData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class addUserData extends AddData {
    private HelpData user;

    public addUserData(HelpData user){
        this.user = user;
    }

    @Override
    protected void setData() {
        Database("Users");
        reference.child(this.user.getUsername()).setValue(this.user);
    }

    @Override
    protected void setIMG(Uri uri) {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("User/" + this.user.getUsername());
        final StorageReference imageReference = storageReference.child(this.user.getUsername() + "." + getFileExtension(uri));

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
