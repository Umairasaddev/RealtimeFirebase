package com.example.realtimefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class NewBookActivity extends AppCompatActivity {

    private EditText author_edtxt,isbn_edtxt,title_edtxt;
    private Spinner book_categories;
    private Button add_btn, back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);

        author_edtxt = findViewById(R.id.author_edtxt);
        isbn_edtxt = findViewById(R.id.isbn_edtxt);
        title_edtxt = findViewById(R.id.title_edtxt);
        book_categories = findViewById(R.id.book_categories);
        add_btn = findViewById(R.id.add_btn);
        back_btn = findViewById(R.id.back_btn);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setAuthor(author_edtxt.getText().toString());
                book.setTitle(title_edtxt.getText().toString());
                book.setTitle(isbn_edtxt.getText().toString());
                book.setTitle(book_categories.getSelectedItem().toString());
                //instantiate firebase helper class
                new FirebaseDatabaseHelper().addBook(book, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataisLoaded(List<Book> books, List<String> keys) {
                        Toast.makeText(NewBookActivity.this, "The book has " +
                                "been inserted sucessfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void DataisInserted() {

                    }

                    @Override
                    public void DataisUpdated() {

                    }

                    @Override
                    public void DataisDeleted() {

                    }
                });

            }
        });
    back_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
            return;
        }
    });

    }
}