package com.angelcm.contactsmomento2.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.res.ResourcesCompat;

import com.angelcm.contactsmomento2.R;
import com.angelcm.contactsmomento2.models.ContactsModel;

import java.util.ArrayList;
import java.util.Random;

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ContactsModel> list;

    public ContactAdapter(Context context, ArrayList<ContactsModel> list){
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ContactsModel getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view = layoutInflater.inflate(R.layout.contact_item, viewGroup, false);
        }

        TextView item_nombre,item_celular, item_correo, item_fecha;
        ImageView item_imagen;
        String nombre, apellido, celular, fijo, correo, empresa;

        item_imagen = view.findViewById(R.id.item_imagen);
        item_nombre = view.findViewById(R.id.item_nombre);
        item_celular = view.findViewById(R.id.item_celular);
        item_correo = view.findViewById(R.id.item_correo);
        item_fecha = view.findViewById(R.id.item_fecha);


        item_nombre.setText(list.get(i).getNombre());
        item_celular.setText(list.get(i).getCelular());
        item_correo.setText(list.get(i).getCorreo());
        item_fecha.setText("18/11/2020");

        Drawable img = ResourcesCompat.getDrawable(view.getResources(), R.drawable.ic_account_circle_24px, null);
        item_imagen.setImageDrawable(img);

        Random r =new Random();
        String[] colores = {"#ff33b5e5", "#ffff8800", "#ff99cc00", "#ffff4444", "#ff0099cc", "#ff669900", "#ffaa66cc", "#ffffbb33"};
        int randomNumber = r.nextInt(colores.length);
        view.setBackgroundColor(Color.parseColor(colores[randomNumber]));

        return view;
    }
}
