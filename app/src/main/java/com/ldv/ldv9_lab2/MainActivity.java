package com.ldv.ldv9_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    EditText editText_a; // Переменная для доступа к компоненту со значением "a"
    EditText editText_b; // Переменная для доступа к компоненту со значением "b"
    EditText editText_x;
    TextView textView_solve; // Переменная для доступа к компоненту со значением результата
    Button buttonSolve; // Переменная для доступа к компоненту кнопки

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Доступ к компонентам окна
        editText_a = findViewById(R.id.editText_a);
        editText_b = findViewById(R.id.editText_b);
        editText_x = findViewById(R.id.editText_x);
        textView_solve = findViewById(R.id.textView_solve);
        buttonSolve = findViewById(R.id.buttonSolve);


        if (savedInstanceState !=null) {
            textView_solve.setText(savedInstanceState.getString("y"));
            buttonSolve.setEnabled(savedInstanceState.getBoolean("VKL"));
        } else {
            buttonSolve.setEnabled(false);
        }

        // Собственный обработчик нажатий на клавиши
        View.OnKeyListener myKeyListener = new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // Проверка условия: если пусто в "a" или "b"
                if (editText_a.getText().toString().trim().equals("") ||
                        editText_b.getText().toString().trim().equals("") ||
                            editText_x.getText().toString().trim().equals("")) {
                    buttonSolve.setEnabled(false); // Выключаем доступность нажатия у кнопки
                } else {
                    buttonSolve.setEnabled(true); // Включаем доступность нажатия у кнопки
                }
                return false;
            }
        };
        editText_a.setOnKeyListener(myKeyListener);
        editText_b.setOnKeyListener(myKeyListener);
        editText_x.setOnKeyListener(myKeyListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("y", textView_solve.getText().toString());
        outState.putBoolean("VKL", buttonSolve.isEnabled());
    }

    // МЕТОД КНОПКИ РАСЧЕТА
    public void onCalc(View v) {
        // Объявление локальных переменных
        double a = 0, b = 0, x= 0, y;

        try { // НАЧАЛО ЗАЩИЩЕННОГО БЛОКА

            // Чтение данных из компонент
            try {
                a = Double.parseDouble(editText_a.getText().toString().trim());
            }
            catch (Exception e) {
                editText_a.setText("???");
                e.printStackTrace();
                displayExceptionMessage(e.getMessage());
            }
            try {
                b = Double.parseDouble(editText_b.getText().toString().trim());

            }
            catch (Exception e) {
                editText_b.setText("???");
                e.printStackTrace();
                displayExceptionMessage(e.getMessage());
            }
            try {
                x = Double.parseDouble(editText_x.getText().toString().trim());

            }
            catch (Exception e) {
                editText_x.setText("???");
                e.printStackTrace();
                displayExceptionMessage(e.getMessage());
            }

            // Основной алгоритм
            if (x > 6) {
                y = a / x + b / x * 2;
            }
            else {
                y = a * 2 * x + b;
            }
            // Вывод полученного значения в компонент
            textView_solve.setText(String.valueOf(y));

        } catch (Exception e) { // ЧТО ДЕЛАТЬ ЕСЛИ ВОЗНИКНЕТ ОШИБКА В БЛОКЕ МЕЖДУ "TRY" И "CATCH":

            // Вывод сообщения об ошибке
            textView_solve.setText("Неверные входные данные для расчета!");

        }  // КОНЕЦ ЗАЩИЩЕННОГО БЛОКА

    }
    public void displayExceptionMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}