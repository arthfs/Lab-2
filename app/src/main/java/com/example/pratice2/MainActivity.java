package com.example.pratice2;



import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> editActivityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.edit);
        TextView question = findViewById(R.id.question);
        boolean empty_question = question.getText().toString().compareTo("Multiple choice question") ==0 ;
        imageView.setVisibility(empty_question ? View.INVISIBLE : View.VISIBLE);
        TextView answer = findViewById(R.id.answer1);
        answer.setVisibility(empty_question ? View.INVISIBLE : View.VISIBLE);
        TextView wrong1 = findViewById(R.id.answer2);
        wrong1.setVisibility(empty_question ? View.INVISIBLE : View.VISIBLE);
        TextView wrong2 = findViewById(R.id.answer3);
        wrong2.setVisibility(empty_question ? View.INVISIBLE : View.VISIBLE);
        System.out.println(question.getText());

        editActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data =  result.getData();
                    if (data!=null) {
                        String quest = data.getStringExtra("question");
                        question.setGravity(Gravity.CENTER);
                        question.setText(quest);

                        //TextView answer = findViewById(R.id.answer1);
                        String ans = data.getStringExtra("answer");
                        answer.setVisibility(View.VISIBLE);
                        answer.setText(ans);

                       // TextView wrong1 = findViewById(R.id.answer2);
                        String wr1 = data.getStringExtra("wrong1");
                        wrong1.setVisibility(View.VISIBLE);
                        wrong1.setText(wr1);

                        //TextView wrong2 = findViewById(R.id.answer3);
                        String wr2 =data.getStringExtra("wrong2");
                        wrong2.setVisibility(View.VISIBLE);
                        wrong2.setText(wr2);
                        imageView.setVisibility(question.getText().toString().compareTo(getResources().getString(R.string.question))==0 ? View.INVISIBLE : View.VISIBLE);

                    }

                });


        ImageView imageView2 = findViewById(R.id.add);

        imageView.setVisibility(question.getText().toString().compareTo(getResources().getString(R.string.question))==0 ? View.INVISIBLE : View.VISIBLE);
        imageView.setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, Question.class);
            intent.putExtra("option","edit");

            intent.putExtra("question", question.getText());
            intent.putExtra("answer",answer.getText());
            intent.putExtra("wrong1",wrong1.getText());
            intent.putExtra("wrong2",wrong2.getText());
            editActivityResultLauncher.launch(intent);
        });

        imageView2.setOnClickListener(v -> {
            // Launch the EditActivity using the ActivityResultLauncher
            Intent intent = new Intent(MainActivity.this, Question.class);
            intent.putExtra("option","add");
            editActivityResultLauncher.launch(intent);
        });

    }

    public void toogle(View v) {
        if (!Important.isvisible) {
            Toast.makeText(this, "Add the choices by clicking on the + button", Toast.LENGTH_SHORT).show();
        } else {

            LinearLayout layout = findViewById(R.id.answers);
            layout.setVisibility(layout.getVisibility() == View.INVISIBLE ? View.VISIBLE : View.INVISIBLE);

            TextView newview = findViewById(R.id.answer2);
            newview.setBackgroundColor(ContextCompat.getColor(this, R.color.white));

            TextView newview2 = findViewById(R.id.answer3);
            newview2.setBackgroundColor(ContextCompat.getColor(this,R.color.white));

            TextView newview3 = findViewById(R.id.answer1);
            newview3.setBackgroundColor(ContextCompat.getColor(this,R.color.white));

            ImageView imageView = findViewById(R.id.edit);
            TextView question = findViewById(R.id.question);
            imageView.setVisibility(question.getText().toString().compareTo(getResources().getString(R.string.question))==0 ? View.INVISIBLE : View.VISIBLE);

        }

    }

    public void show_answer(View v) {
        TextView choice = findViewById(v.getId());
        TextView answer = findViewById(R.id.answer1);
        TextView newview = findViewById(R.id.answer2);
        TextView newview2 = findViewById(R.id.answer3);


            if (v.getId() == findViewById(R.id.answer1).getId()) {
                choice.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
                newview.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                newview2.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            } else if (v.getId() == findViewById(R.id.answer2).getId()) {
                answer.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                choice.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
                newview2.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            } else {
                answer.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                newview.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
                choice.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
            }
        }

}