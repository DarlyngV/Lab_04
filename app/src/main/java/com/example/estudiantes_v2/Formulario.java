package com.example.estudiantes_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.estudiantes_v2.MainActivity.estudiante;

public class Formulario extends AppCompatActivity {
    private Button aceptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        aceptar= (Button)findViewById(R.id.aceptar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Estudiante est1= new Estudiante(((EditText)findViewById(R.id.nombre)).getText().toString(),((EditText)findViewById(R.id.cui)).getText().toString(), true);
                Intent intent= new Intent();
               Bundle bundle= new Bundle();
                bundle.putSerializable(estudiante, est1);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

}