package com.example.johnmcmillan.mobo2;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by johnmcmillan on 13/12/2016.
 */

public class AboutDialogue extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder mcAboutDialog = new AlertDialog.Builder(getActivity());
        mcAboutDialog.setMessage(R.string.dialog_About)
                .setPositiveButton(R.string.dialog_About_OK_btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });
        mcAboutDialog.setTitle("About");

        // Create the AlertDialog object and return it
        return mcAboutDialog.create();
}}
