package com.example.formularz;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private EditText editImie;
    private EditText editNazwisko;
    private EditText editEmail;
    private EditText editHaslo;
    private Button buttonZarejestruj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editImie = findViewById(R.id.editImie);
        editNazwisko = findViewById(R.id.editNazwisko);
        editEmail = findViewById(R.id.editEmail);
        editHaslo = findViewById(R.id.editHaslo);
        buttonZarejestruj = findViewById(R.id.buttonZarejestruj);

        buttonZarejestruj.setOnClickListener(v -> zarejestruj());
    }

    private void zarejestruj() {

        String imie = editImie.getText().toString().trim();
        String nazwisko = editNazwisko.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String haslo = editHaslo.getText().toString();

        if (imie.isEmpty()) {
            editImie.setError("Podaj imię");
            editImie.requestFocus();
            return;
        }

        if (nazwisko.isEmpty()) {
            editNazwisko.setError("Podaj nazwisko");
            editNazwisko.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editEmail.setError("Podaj adres e-mail");
            editEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Podaj poprawny adres e-mail");
            editEmail.requestFocus();
            return;
        }

        if (haslo.isEmpty()) {
            editHaslo.setError("Podaj hasło");
            editHaslo.requestFocus();
            return;
        }

        if (haslo.length() < 6) {
            editHaslo.setError("Hasło musi mieć minimum 6 znaków");
            editHaslo.requestFocus();
            return;
        }

        Toast.makeText(
                this,
                "Witaj, " + imie+ "!",
                Toast.LENGTH_LONG
        ).show();
    }
    private void saveToFile() {
        String name = editImie.getText().toString().trim();
        String surname = editNazwisko.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editHaslo.getText().toString().trim();

        String data = "\nImię: " + name
                + "\nNazwisko: " + surname
                + "\nEmail: " + email
                + "\nHasło: " + password;

        try (FileOutputStream fos = openFileOutput(
                "dane.txt",
                MODE_APPEND
        )) {
            fos.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            Toast.makeText(
                    this,
                    "Błąd zapisu do pliku!",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}
