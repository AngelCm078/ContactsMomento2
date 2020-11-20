package com.angelcm.contactsmomento2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.angelcm.contactsmomento2.models.ContactsModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateContactActivity extends AppCompatActivity {

    private EditText et_create_contact_nombre, et_create_contact_apellido, et_create_contact_celular, et_create_contact_fijo, et_create_contact_correo, et_create_contact_empresa;
    private Button btn_create_contact_create;
    private ContactsModel model;
    protected FirebaseFirestore db;
    private String TAG = "LFNOT";
    final private String collection = "contacts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        db = FirebaseFirestore.getInstance();
        et_create_contact_nombre = findViewById(R.id.et_create_contact_nombre);
        et_create_contact_apellido = findViewById(R.id.et_create_contact_apellido);
        et_create_contact_celular = findViewById(R.id.et_create_contact_celular);
        et_create_contact_fijo = findViewById(R.id.et_create_contact_fijo);
        et_create_contact_correo = findViewById(R.id.et_create_contact_correo);
        et_create_contact_empresa = findViewById(R.id.et_create_contact_empresa);
        btn_create_contact_create = findViewById(R.id.btn_create_contact_create);

        btn_create_contact_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre, apellido, celular, fijo, correo, empresa;

                nombre = et_create_contact_nombre.getText().toString();
                apellido = et_create_contact_apellido.getText().toString();
                celular = et_create_contact_celular.getText().toString();
                fijo = et_create_contact_fijo.getText().toString();
                correo = et_create_contact_correo.getText().toString();
                empresa = et_create_contact_empresa.getText().toString();

                model = new ContactsModel(nombre,apellido,celular,fijo,correo,empresa);

                db.collection(collection)
                        .add(model)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added wit ID: " + documentReference.getId());
                                Toast.makeText(getApplicationContext(), "Contacto creado Correctamente", Toast.LENGTH_LONG).show();
                                goToContacts();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Log.w(TAG, "Error adding document", e);
                                Toast.makeText(getApplicationContext(), "Error al crear contacto" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
    private void goToContacts(){
        Intent mostrar = new Intent(this, MainActivity.class);
        startActivity(mostrar);
    }
}