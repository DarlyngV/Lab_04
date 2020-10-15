package com.example.estudiantes_v2;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String nEstudiantes[]={"Darlyng Rondon", "Kevin Quiñones", "Jose Suri", "Juanita Lopez", "Rosa Perez"};
    String cui[]={"20172130", "20173369", "20173385", "20172132", "20174456"};
    int images[]={R.drawable.chica, R.drawable.chico, R.drawable.chico, R.drawable.chica, R.drawable.chica};
    private static final int REQUEST_CODE= 222;
    public static final String estudiante= "estudiante";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);
        Estudiantes estudiante= new Estudiantes(this, nEstudiantes, cui, images);
        listView.setAdapter(estudiante);


    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.mas:
                Toast.makeText(getApplicationContext(), "Agregar Estudiante", Toast.LENGTH_SHORT).show();
                Intent siguiente = new Intent(this, Formulario.class);
              /*  startActivity(siguiente);*/
                startActivityForResult(siguiente, REQUEST_CODE );

            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            if(resultCode==RESULT_OK){
               Toast.makeText(getApplicationContext(), "Logrado", Toast.LENGTH_SHORT).show();
                /*Bundle objeto = getIntent().getExtras();*/
                Bundle objeto= data.getExtras();
                Estudiante estudiante_nuevo= null;
                if(objeto!=null){
                    estudiante_nuevo= (Estudiante) objeto.getSerializable(estudiante);
                    Toast.makeText(getApplicationContext(), estudiante_nuevo.getNombre(), Toast.LENGTH_SHORT).show();
                }




            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public   class Estudiantes extends ArrayAdapter<String> {
        Context context;
        String nEstudiantes_2[];
        String cui_2[];
        int images_2[];
        Estudiantes(Context c, String nombre[], String cui[], int imgs[] ){
            super(c, R.layout.row, R.id.textView1,nombre);
            this.context=c;
            this.nEstudiantes_2= nombre;
            this.cui_2=cui;
            this.images_2=imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row= layoutInflater.inflate(R.layout.row,parent,false);
            ImageView images= row.findViewById(R.id.image);
            TextView nombre= row.findViewById(R.id.textView1);
            TextView cui= row.findViewById(R.id.textView2);
            images.setImageResource(images_2[position]);
            nombre.setText(nEstudiantes_2[position]);
            cui.setText(cui_2[position]);
            return row;
        }
    }
}