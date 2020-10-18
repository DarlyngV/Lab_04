package com.example.estudiantes_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static com.example.estudiantes_v2.MainActivity.estudiante_msg;


public class Formulario extends AppCompatActivity {
    private Button aceptar;
    private Button cancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        aceptar= (Button)findViewById(R.id.aceptar);
        cancelar= (Button)findViewById(R.id.cancelar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean sex;
                if(((RadioButton)(findViewById(R.id.radioButton2))).isChecked()){
                        sex= true;
                }
                else{
                    sex= false;
                }
                Estudiante est1= new Estudiante(((EditText)findViewById(R.id.nombre)).getText().toString(),((EditText)findViewById(R.id.cui)).getText().toString(),
                       sex);

                Intent intent= new Intent();
               Bundle bundle= new Bundle();
                bundle.putSerializable(estudiante_msg, est1);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}