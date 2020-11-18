package com.angelcm.contactsmomento2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.angelcm.contactsmomento2.adapters.ContactAdapter;
import com.angelcm.contactsmomento2.models.ContactsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected FirebaseFirestore db;
    private String TAG = "LFNOT";
    final private String collection = "contacts";
    private ListView lv_main_contacts;
    private Button btn_main_nuevo;
    private ArrayList<ContactsModel> list;
    private ContactAdapter adapter;
    private ContactsModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        lv_main_contacts = findViewById(R.id.lv_main_contacts);
        btn_main_nuevo = findViewById(R.id.btn_main_nuevo);

        btn_main_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreateContact();
            }
        });

        list = new ArrayList<>();

        db.collection(collection)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                Log.d(TAG, document.getId() + "=>" + document.getData());
                                model = document.toObject(ContactsModel.class);
                                list.add(model);
                            }
                            adapter = new ContactAdapter(getApplicationContext(), list);
                            lv_main_contacts.setAdapter(adapter);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        lv_main_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), String.valueOf("Celular: " + list.get(i).getCelular()), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void goToCreateContact(){
        Intent create = new Intent(this, CreateContactActivity.class);
        startActivity(create);
    }
}