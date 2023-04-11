package com.example.pratice2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Question extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        getIntent().getStringExtra("option");
         if (getIntent().getStringExtra("option").compareTo("edit")==0  )
         {
            EditText question =  findViewById(R.id.newquestion);
            EditText answer=  findViewById(R.id.newanswer);
            EditText wrong1 =  findViewById(R.id.newwrong1);
            EditText wrong2 =  findViewById(R.id.newwrong2);

            question.setText(getIntent().getStringExtra("question"));
            answer.setText(getIntent().getStringExtra("answer"));
            wrong1.setText(getIntent().getStringExtra("wrong1"));
            wrong2.setText(getIntent().getStringExtra("wrong2"));
         }

    }
    public void savecancel(View v)
    {
        EditText question = findViewById(R.id.newquestion);
        EditText answer = findViewById(R.id.newanswer);
        EditText wrong1 = findViewById(R.id.newwrong1);
        EditText wrong2 = findViewById(R.id.newwrong2);
        if (v.getId() == findViewById(R.id.cancel).getId()) {
            finish();
        } else {

            if (question.getText().toString().length() < 10 || answer.getText().toString().length() < 1 || wrong1.getText().toString().length() < 1 || wrong2.getText().toString().length() < 1) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                Intent data = new Intent();
                data.putExtra("question", question.getText().toString());
                data.putExtra("answer", answer.getText().toString());
                data.putExtra("wrong1", wrong1.getText().toString());
                data.putExtra("wrong2", wrong2.getText().toString());
                setResult(RESULT_OK, data);


                Important.isvisible = true;

                Toast.makeText(this, "Question saved", Toast.LENGTH_SHORT).show();
                finish();
            }

        }
    }
}
