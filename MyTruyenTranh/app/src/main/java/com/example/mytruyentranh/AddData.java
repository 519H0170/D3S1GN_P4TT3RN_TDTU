package com.example.mytruyentranh;

import android.content.ContentResolver;
import android.net.Uri;
import android.webkit.MimeTypeMap;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class AddData {

    public DatabaseReference reference;
    public Uri uri;

    public String getFileExtension(Uri fileUri){
        ContentResolver contentResolver = null;
        //contentResolver = getApplicationContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }

    final void setDatabase(){
        setData();
        setIMG(uri);
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    final void Database(String path){
        reference = FirebaseDatabase.getInstance().getReference(path);
    }

    protected abstract void setData();
    protected abstract void setIMG(Uri uri);
}
