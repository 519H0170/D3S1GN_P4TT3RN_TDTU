package com.example.mytruyentranh.BackGround;

import android.util.Log;

public class ButtonInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
        Log.v("BackGround", "Chế độ ban đêm - Bật. Không biết lưu ++!");
    }
}
