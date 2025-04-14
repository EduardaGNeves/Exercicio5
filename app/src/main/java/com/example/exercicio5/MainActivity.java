package com.example.exercicio5;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private TextView txtContador;
    private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtContador = findViewById(R.id.txtContador);
        btnIniciar = findViewById(R.id.btnIniciar);

        btnIniciar.setOnClickListener(v -> iniciarContador());
    }

    private void iniciarContador() {
        btnIniciar.setEnabled(false);
        btnIniciar.setTextColor(ContextCompat.getColor(this, R.color.white));

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            for (int i = 10; i >= 0; i--) {
                int valorAtual = i;

                runOnUiThread(() -> txtContador.setText(String.valueOf(valorAtual)));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            runOnUiThread(() -> {
                btnIniciar.setEnabled(true);
                btnIniciar.setTextColor(ContextCompat.getColor(this, R.color.black));
            });
        });
    }
}