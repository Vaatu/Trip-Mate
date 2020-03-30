package com.vaatu.tripmate.utils.AlarmManagerReciever;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class MyDialogActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //setContentView(R.layout.activity_my_dialog);

        AlertDialog.Builder Builder=new AlertDialog.Builder(MyDialogActivity.this)
                .setMessage("Remimder ")
                .setTitle("exit")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyDialogActivity.this.finish();
                    }
                })
                .setPositiveButton("Yes",null);
        AlertDialog alertDialog=Builder.create();
        alertDialog.show();


    }
}
