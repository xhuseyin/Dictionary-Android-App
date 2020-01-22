package com.info.sozlukuygulamasi;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class DialogItem extends AppCompatDialogFragment {

    private EditText etEn,etTr;
    private DialogItemListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_item,null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                // Set title
                .setTitle("Add to the list")
                // Add action buttons
                .setPositiveButton("ekle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                      String en = etEn.getText().toString();
                      String tr = etTr.getText().toString();
                      listener.addItem(en,tr);
                    }
                })
                .setNeutralButton("güncelle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("iptal", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogItem.this.getDialog().cancel();
                    }
                });

        etEn = view.findViewById(R.id.etEn);
        etTr = view.findViewById(R.id.etTr);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogItemListener)context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement DialogItemListener");
        }
    }

    public interface DialogItemListener{
        void addItem(String en,String tr);

    }

}
