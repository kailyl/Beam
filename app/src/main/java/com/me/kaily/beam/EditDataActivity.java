//package com.me.kaily.beam;
//
//import android.app.FragmentTransaction;
//import android.content.Intent;
//import android.os.Bundle;
//import android.provider.ContactsContract;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//
//public class EditDataActivity extends AppCompatActivity {
//
//    private static final String TAG = "EditDataActivity";
//
//    private Button btnSave, btnDelete;
//    private EditText editable_item;
//
//    databaseHelper myDB;
//
//    private String selectedItem;
//    private int selectedID;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.editnotes);
//
//        btnSave = (Button) findViewById(R.id.btnSave);
//        btnDelete = (Button) findViewById(R.id.btnDelete);
//        editable_item = (EditText) findViewById(R.id.editable_item);
//        myDB = new databaseHelper(this);
//
//        Intent receivedIntent = getIntent();
//
//        selectedID = receivedIntent.getIntExtra("id", -1);
//
//        selectedItem = receivedIntent.getStringExtra("name");
//
//        editable_item.setText(selectedItem);
//
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String item = editable_item.getText().toString();
//                if (item.equals("")){
//                    Toast.makeText(EditDataActivity.this, "You must enter a value", Toast.LENGTH_LONG).show();
//                }else{
//                    myDB.updateName(item, selectedID, selectedItem);
//                    Toast.makeText(EditDataActivity.this, "successfully changed", Toast.LENGTH_LONG).show();
//                    Intent mainIntent = new Intent(EditDataActivity.this, journal.class);
//                    EditDataActivity.this.startActivity(mainIntent);
//                }
//            }
//        });
//
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myDB.deleteName(selectedID,selectedItem);
//                editable_item.setText("");
//                Toast.makeText(EditDataActivity.this, "removed from data", Toast.LENGTH_LONG).show();
//                Intent mainIntent = new Intent(EditDataActivity.this, journal.class);
//                EditDataActivity.this.startActivity(mainIntent);
//            }
//        });
//    }
//}
