package com.angelcm.contactsmomento2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.angelcm.contactsmomento2.models.ContactsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailActivity extends AppCompatActivity {

    private EditText et_detail_nombre, et_detail_apellido, et_detail_celular, et_detail_fijo, et_detail_correo, et_detail_empresa;
    private Button btn_detail_save;
    protected FirebaseFirestore db;
    private String TAG = "LFNOT";
    final private String collection = "contacts";
    private ContactsModel model;
    private DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = FirebaseFirestore.getInstance();

        et_detail_nombre = findViewById(R.id.et_detail_nombre);
        et_detail_apellido = findViewById(R.id.et_detail_apellido);
        et_detail_celular = findViewById(R.id.et_detail_celular);
        et_detail_fijo = findViewById(R.id.et_detail_fijo);
        et_detail_correo = findViewById(R.id.et_detail_correo);
        et_detail_empresa = findViewById(R.id.et_detail_empresa);
        btn_detail_save = findViewById(R.id.btn_detail_save);

        String id = getIntent().getStringExtra("id");
        if(id != null){
            et_detail_nombre.setText(id);
            updateUI(id);
        }else{
            et_detail_nombre.setText("Sin Informacion");
        }

        btn_detail_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre, apellido, celular, fijo, correo, empresa;

                nombre = et_detail_nombre.getText().toString();
                apellido = et_detail_apellido.getText().toString();
                celular = et_detail_celular.getText().toString();
                fijo = et_detail_fijo.getText().toString();
                correo = et_detail_correo.getText().toString();
                empresa = et_detail_empresa.getText().toString();

                if(!nombre.equals("")){
                    String id = model.getIdFireBase();

                    if(id != null && !id.equals("")){
                        model.setNombre(nombre);
                        model.setApellido(apellido);
                        model.setCelular(celular);
                        model.setFijo(fijo);
                        model.setCorreo(correo);
                        model.setEmpresa(empresa);



                        documentReference = db.collection(collection).document(model.getIdFireBase());

                        documentReference.set(model)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        Log.d(TAG, "DocumentSnapshot added wit ID: " + documentReference.getId());
                                        Toast.makeText(getApplicationContext(), "Contacto Editado Correctamente", Toast.LENGTH_LONG).show();
                                        goToContacts();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Log.w(TAG, "Error adding document", e);
                                        Toast.makeText(getApplicationContext(), "Error al Editar contacto" + e.getMessage(), Toast.LENGTH_LONG).show();


                                    }
                                });
                    }
                }
            }
        });
    }

    private void updateUI(String id){
        documentReference = db.collection(collection).document(id);
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            model = document.toObject(ContactsModel.class);
                            model.setIdFireBase(document.getId());
                            if(model != null){
                                et_detail_nombre.setText(model.getNombre());
                                et_detail_apellido.setText(model.getApellido());
                                et_detail_celular.setText(model.getCelular());
                                et_detail_fijo.setText(model.getFijo());
                                et_detail_correo.setText(model.getCorreo());
                                et_detail_empresa.setText(model.getEmpresa());
                            }
                    }
                    }
                });
    }

    private void goToContacts(){
        Intent mostrar = new Intent(this, MainActivity.class);
        startActivity(mostrar);
    }



}