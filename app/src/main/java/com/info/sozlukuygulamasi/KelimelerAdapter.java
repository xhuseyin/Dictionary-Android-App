package com.info.sozlukuygulamasi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kasimadalan on 1.05.2018.
 */

public class KelimelerAdapter extends RecyclerView.Adapter<KelimelerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Kelimeler> kelimelerListe;

    public KelimelerAdapter(Context mContext, List<Kelimeler> kelimelerListe) {
        this.mContext = mContext;
        this.kelimelerListe = kelimelerListe;
    }

    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(CardTasarimTutucu holder, int position) {
        final Kelimeler kelime = kelimelerListe.get(position);

        holder.textViewIngilizce.setText(kelime.getIngilizce());
        holder.textViewTurkce.setText(kelime.getTurkce());

        holder.kelime_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                Intent intent = new Intent(mContext,DetayActivity.class);

                intent.putExtra("nesne",kelime);

                mContext.startActivity(intent);
                 */

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Güncelle");
                View v = LayoutInflater.from(mContext).inflate(R.layout.dialog_item,null);

                EditText etEn = v.findViewById(R.id.etEn);
                EditText etTr = v.findViewById(R.id.etTr);

                etEn.setText(kelime.getIngilizce());
                etTr.setText(kelime.getTurkce());

                builder.setView(v);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });

        /*
        holder.kelime_card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Snackbar.make(view, kelime.getIngilizce(), Snackbar.LENGTH_SHORT).setAction("Action", null).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                View v = LayoutInflater.from(mContext).inflate(R.layout.dialog_item,null);

                EditText etEn = v.findViewById(R.id.etEn);
                EditText etTr = v.findViewById(R.id.etTr);

                etEn.setText(kelime.getIngilizce());
                etTr.setText(kelime.getTurkce());

                builder.setView(v);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
        */

    }

    @Override
    public int getItemCount() {
        return kelimelerListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private TextView textViewIngilizce;
        private TextView textViewTurkce;
        private CardView kelime_card;

        public CardTasarimTutucu(View itemView) {
            super(itemView);
            textViewIngilizce = itemView.findViewById(R.id.textViewIngilizce);
            textViewTurkce = itemView.findViewById(R.id.textViewTurkce);
            kelime_card = itemView.findViewById(R.id.kelime_card);
        }
    }
}
