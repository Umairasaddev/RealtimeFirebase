package com.example.realtimefirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);


    }
}
//
//       final EditText et_name = findViewById(R.id.et_name);
//       final EditText et_pos = findViewById(R.id.et_pos);
//
//        Button button = findViewById(R.id.button);
//
//        AdminEmployee dao = new AdminEmployee();
//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//
//                Employee emp = new Employee(et_name.getText().toString(),et_pos.getText().toString());
//                dao.add(emp).addOnSuccessListener(suc->
//                        {
//                            Toast.makeText(MainActivity.this, "Record is inserted", Toast.LENGTH_SHORT).show();
//                        }).addOnFailureListener(er->
//                        {
//                                Toast.makeText(MainActivity.this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
//                        });
//            }
//
//        });
//    }
//}
