package com.example.realtimefirebase;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

//this class is responsible for manipulating real time database
public class FirebaseDatabaseHelper {

    //defining member variable for fiebase data base
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceBooks;
    private List<Book> books = new ArrayList<>();


//all the interactions for real time data base are asynchronous
    public interface DataStatus{
        void DataisLoaded(List<Book> books,List<String> keys);//one parameter for storing books and other for storing keys
        void DataisInserted();
        void DataisUpdated();
        void DataisDeleted();
    }

    public FirebaseDatabaseHelper() {

        //initializing
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceBooks = mDatabase.getReference("books");//we want to reference the books with all its children

    }

    public void readBooks(final DataStatus dataStatus) {
        mReferenceBooks.addValueEventListener(new ValueEventListener() {
            @Override//this is asynchronous method and it will not be called with this event listens
            //every time we update delete or store data this below function will execute
            public void onDataChange(@NonNull DataSnapshot datasnapshot)//parameter contains the key and value of book
            {
                books.clear();//clear the list of books from old data
                List<String> keys = new ArrayList<>();//storing the keys of books define and instantiate list of type string
                for (DataSnapshot keyNode : datasnapshot.getChildren()) {//this object will contain the key and value of sepcific node

                    keys.add(keyNode.getKey());
                    Book book = keyNode.getValue(Book.class);//creating book objects and initializing it from nodes value
                    books.add(book);//adding books to books list
                }
                dataStatus.DataisLoaded(books, keys);//now we have method for returning books for real time database
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //writing data on real time firebase

         public void addBook(Book book,final DataStatus dataStatus){
        String key  = mReferenceBooks.push().getKey();//to generate child node and this child will have new unique key automatically
        mReferenceBooks.child(key).setValue(book)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        dataStatus.DataisInserted();
                    }
                });

    }
}
