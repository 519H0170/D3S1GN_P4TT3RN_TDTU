package com.example.mytruyentranh.designPattern;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytruyentranh.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Category> arrayList;
    private ArrayList<Category> listCategory = new ArrayList<>();

    public CategoryAdapter(Context context, ArrayList<Category> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(arrayList!=null &&  arrayList.size() >0) {
            Category category = arrayList.get(position);
            holder.textView.setText(category.getCategory());
            holder.checkBox.setChecked(category.isActive());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public ArrayList<Category> takeData(){
        return this.listCategory;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private CheckBox checkBox;
        private Category cat;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            this.textView = itemView.findViewById(R.id.category);
            this.checkBox = itemView.findViewById(R.id.checkBox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isChecked = ((CheckBox) v).isChecked();

                    if(isChecked){
                        arrayList.get(getAdapterPosition()).setActive(true);
                        listCategory.add(arrayList.get(getAdapterPosition()));
                    }else{
                        arrayList.get(getAdapterPosition()).setActive(false);
                        listCategory.remove(arrayList.get(getAdapterPosition()));
                    }
                    notifyDataSetChanged();
                    for(int i = 0; i<arrayList.size(); i++){
                        Log.d("TAG", arrayList.toString());
                    }
                }
            });
        }
    }
}
