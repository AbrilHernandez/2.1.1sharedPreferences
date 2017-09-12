package com.example.abril.sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String NAME="NAME";
    private EditText mEditTextName;
    private EditText edt_nombre,edt_email,edt_noctrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_noctrl=(EditText) findViewById(R.id.edt_nocontrol);
        edt_nombre=(EditText)findViewById(R.id.edt_nombre);
        edt_email=(EditText) findViewById(R.id.edt_email);
        final TextView textView = (TextView)findViewById(R.id.txt_msj);


        final SharedPreferences[] sharedPreferences = {getPreferences(MODE_PRIVATE)};
        final String[] texto = {sharedPreferences[0].getString(NAME, null)};
        if (texto[0] ==null) {textView.setText("Introduzca valores en sus campos");}
        else {textView.setText("--> " + texto[0]);}

        Button btn_leer=(Button) findViewById(R.id.btn_leer);
        btn_leer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences[0] = getPreferences(MODE_PRIVATE);
                texto[0] = sharedPreferences[0].getString(NAME,null);
                textView.setText("--> " + texto[0]);
            }
        });
        final Button btn_save =(Button)findViewById(R.id.btn_guardar);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveText(btn_save);
            }
        });

    }
    public void saveText(View view) {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        String valor="Nombre: "+edt_nombre.getText().toString()+"\nNo ctrl:"+
                edt_noctrl.getText().toString()+"\nCorreo: "+edt_email.getText().toString();
        editor.putString(NAME, valor);
        editor.commit();
    }
}