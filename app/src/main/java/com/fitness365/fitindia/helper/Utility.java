package com.fitness365.fitindia.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fitness365.fitindia.R;
import com.fitness365.fitindia.activities.LoginActivity;

import androidx.appcompat.app.AlertDialog;

public class Utility {
    public static boolean verify(String paramString, Context ctx) {
        return paramString
                .matches(ctx.getResources().getString(R.string.email_verification_symbols));
    }

    public static void showExitDialog(final Activity context, final Class activity) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setCancelable(false);

        Resources resources = context.getResources();

        alertDialog.setMessage(resources.getString(R.string.leave_message));

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.alarm);

        alertDialog.setPositiveButton(resources.getString(R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                context.startActivity(new Intent(context, activity));
                context.finish();
            }
        });

        alertDialog.setNegativeButton(resources.getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    public static void showResponseDialog(String message, final Activity context, int icon, final boolean forFinishingActivity) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.response_alert_dialog, null);
        builder.setView(view);
        builder.setCancelable(false);

        ImageView responseIcon = view.findViewById(R.id.response_icon);
        TextView responseMsg = view.findViewById(R.id.response_msg);
        TextView doneBtn = view.findViewById(R.id.dismiss_btn);

        responseIcon.setImageResource(icon);
        responseMsg.setText(message);

        final android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                if (forFinishingActivity) {
                    //context.setResult(AppConfig.INTENT_REQUEST_CODE, new Intent());
                    context.finish();
                }
            }
        });
    }
}
