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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Estudiantes estudiante;
    ListView listView;
    String nEstudiantes[]={"Darlyng Rondon", "Kevin Quiñones", "Jose Suri", "Juanita Lopez", "Rosa Perez"};
    String cui[]={"20172130", "20173369", "20173385", "20172132", "20174456"};
    ArrayList<Estudiante> estudiantes= new ArrayList<>();
    int images[]={R.drawable.chica, R.drawable.chico, R.drawable.chico, R.drawable.chica, R.drawable.chica};
    private static final int REQUEST_CODE= 222;
    public static final String estudiante_msg= "estudiante";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listView);

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
                Bundle objeto= data.getExtras();
                Estudiante estudiante_nuevo= null;
                if(objeto!=null){
                    estudiante_nuevo= (Estudiante) objeto.getSerializable(estudiante_msg);
                    estudiantes.add(estudiante_nuevo);
                    setEstudiantes(this,estudiantes,sacarNombres(estudiantes));
                    Toast.makeText(getApplicationContext(), "Estudiante agregado", Toast.LENGTH_SHORT).show();
                }




            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public String[] sacarNombres(ArrayList  <Estudiante> lista){
        String[] nombres = new String[lista.size()];
        for(int i= 0; i< lista.size();i++){
            nombres[i]=lista.get(i).getNombre();
        }
        return nombres;
    }
    public   class Estudiantes extends ArrayAdapter<String> {
        Context context;


        String nombres[];
        ArrayList <Estudiante> estudiantes_show;




     Estudiantes(Context c, ArrayList  <Estudiante> lista, String nombres[]) {
         super(c, R.layout.row,R.id.textView1, nombres );
         this.nombres=nombres;
         this.context=c;
         this.estudiantes_show= lista;
     }


        @NonNull
        @Override

        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row= layoutInflater.inflate(R.layout.row,parent,false);
            ImageView images= row.findViewById(R.id.image);
            TextView nombre= row.findViewById(R.id.textView1);
            TextView cui= row.findViewById(R.id.textView2);
            if (estudiantes_show.get(position).isSex())  images.setImageResource(R.drawable.chica);
            else images.setImageResource(R.drawable.chico);
            nombre.setText(estudiantes_show.get(position).getNombre());
            cui.setText(estudiantes_show.get(position).getCUI());
            return row;
        }


    }
    public void setEstudiantes(Context c, ArrayList  <Estudiante> lista, String nombres[]){
        estudiante= new Estudiantes(c, lista , nombres);
        listView.setAdapter(estudiante);
    }
}