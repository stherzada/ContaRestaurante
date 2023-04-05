package com.example.contarestaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button calculoConta;
    private EditText consumo;
    private EditText couvertArtistico;
    private EditText dividirConta;
    private TextView taxaServico;
    private TextView contaTotal;
    private TextView valorPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText[] editTexts = new EditText[]{
            consumo = findViewById(R.id.editConsumo),
            couvertArtistico = findViewById(R.id.editArtistico),
            dividirConta = findViewById(R.id.editDividir)
        };

        taxaServico = findViewById(R.id.taxaServico);
        contaTotal = findViewById(R.id.contaTotal);
        valorPessoa = findViewById(R.id.valorPessoa);

        calculoConta = findViewById(R.id.btnCalcula);

        calculoConta.setEnabled(false);


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                boolean enableButton = true;

                for (EditText editText:editTexts){
                    if(editText.getText().toString().isEmpty()){
                        enableButton = false;
                        break;
                    }
                }

                calculoConta.setEnabled(enableButton);
            }
        };

        for (EditText editText : editTexts) {
            editText.addTextChangedListener(textWatcher);
        }
        calculoConta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                double consumoValor = Double.parseDouble(consumo.getText().toString());
                double consumoArtista = Double.parseDouble(couvertArtistico .getText().toString());
                int contaDividir = Integer.parseInt(dividirConta.getText().toString());

                double resultado = consumoValor + consumoArtista;
                contaTotal.setText("" + String.format("%.2f", resultado));

                double taxa = resultado * 0.1;
                taxaServico.setText("" + String.format("%.2f", resultado + taxa));

                double dividir = 0;
                        if(contaDividir != 0){
                            dividir = (resultado + taxa)/contaDividir;
                            valorPessoa.setText("" + String.format("%.2f", dividir));
                }
                        else{
                            valorPessoa.setText("NÃO PODE DIVIDIR POR ZERO!");

                        }

            }
        });

        }


}