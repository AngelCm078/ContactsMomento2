package com.angelcm.contactsmomento2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
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

        String id = getIntent().getStringExtra("id");
        if(id != null){
            et_detail_nombre.setText(id);
            //updateUI(id);
        }else{
            et_detail_nombre.setText("No recibimos nada");
        }
    }

    /*

    private void updateUI(String id){
        documentReference = db.collection(collection).document(id);
        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            model = document.toObject(NotaModel.class);
                            model.setFbId(document.getId());
                            if(model != null){
                                tv_detalle_titulo.setText(model.getTitulo());
                                tv_detalle_contenido.setText(model.getContenido());
                            }

                            updateModel(model);
                        }
                    }
                });


    }
    */
}