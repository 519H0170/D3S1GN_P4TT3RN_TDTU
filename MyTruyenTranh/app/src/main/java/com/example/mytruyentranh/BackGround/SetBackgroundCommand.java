package com.example.mytruyentranh.BackGround;

import android.view.View;

public class SetBackgroundCommand implements Command{
    private final View view;
    private final int color;

    public SetBackgroundCommand(View view, int color) {
        this.view = view;
        this.color = color;
    }

    @Override
    public void execute() {
        view.setBackgroundColor(color);
    }
}
