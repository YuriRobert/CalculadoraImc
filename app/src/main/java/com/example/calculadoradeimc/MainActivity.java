package com.example.calculadoradeimc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.calculadoradeimc.databinding.ActivityMainBinding;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //recuperando a id e criando evento de click para o botão calcular
        binding.btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recuperando o id, capturando o que está sendo digitado e convertendo String
                String peso = binding.editPeso.getText().toString();
                String altura = binding.editAltura.getText().toString();

                //irá exibir uma mensagem caso um dos campos estiver vazio
                if (peso.isEmpty()) {
                    binding.editPeso.setError("Imforme seu Peso!");
                } else if (altura.isEmpty()) {
                    binding.editAltura.setError("Informe sua Altura!");
                } else {
                    calcularImc();
                }
            }
        });
    }

    //metodo para fazer a logica do calculo
    private void calcularImc() {
        //recuperando id do peso e transformando em decimal
        //Usando o replace para alterar o ponto para virgula
        Float peso = Float.parseFloat(binding.editPeso.getText().toString().replace(",","."));
        Float altura = Float.parseFloat(binding.editAltura.getText().toString().replace(",","."));
        //calculo
        Float imc = peso / (altura * altura);

        //instanciando a classe Decimal Format para converter o resultado do ponto para virgula, e diminuindo as casas decimais para 2
        DecimalFormat decimalFormat = new DecimalFormat("0.00", new DecimalFormatSymbols(new Locale("pt","BR")));

        if (imc < 18.5) {
            binding.txtResultado.setText("Seu imc é de: " + decimalFormat.format(imc) + "\n" + "Peso Baixo");
        } else if (imc <= 24.9) {
            binding.txtResultado.setText("Seu imc é de: " + decimalFormat.format(imc) + "\n" + "Peso Normal");
        } else if (imc <= 29.9) {

            binding.txtResultado.setText("Seu imc é de: " + decimalFormat.format(imc) + "\n" + "Sobrepeso");
        } else if (imc <= 34.9) {
            binding.txtResultado.setText("Seu imc é de: " + decimalFormat.format(imc) + "\n" + "Obesidade (Grau I)");
        } else if (imc <= 39.9) {
            binding.txtResultado.setText("Seu imc é de: " + decimalFormat.format(imc) + "\n" + "Obesidade Severa (Grau II)");
        } else {
            binding.txtResultado.setText("Seu imc é de: " + decimalFormat.format(imc) + "\n" + "Obesidade Mórbida (Grau III)");
        }
    }
    //metodo para o menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflando o menu e definindo na barra de ação
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //definindo ações para os itens de menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemID = item.getItemId();

        if (itemID == R.id.ic_limpar){
            binding.editPeso.setText("");
            binding.editAltura.setText("");
            binding.txtResultado.setText("");
        }
        return super.onOptionsItemSelected(item);
    }
}