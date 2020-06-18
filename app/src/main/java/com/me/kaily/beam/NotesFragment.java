package com.me.kaily.beam;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;
import static android.os.Build.ID;

public class NotesFragment extends Fragment implements View.OnClickListener {

    databaseHelper myDB;
    private EditText editTxt;
    private Button btn;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_notes, container, false);
        super.onCreate(savedInstanceState);

        list = (ListView) myView.findViewById(R.id.list_view);
        editTxt = (EditText) myView.findViewById(R.id.editText4);
        btn = (Button) myView.findViewById(R.id.buttonAdd);

        myDB = new databaseHelper(getActivity());
        arrayList = new ArrayList<String>();
        Cursor data = myDB.getListContents();
        while (data.moveToNext()) {
            arrayList.add(data.getString(1));
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
            list.setAdapter(adapter);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = editTxt.getText().toString();
                arrayList = new ArrayList<String>();

                if (editTxt.length() != 0) {
                    //myDB = new databaseHelper(getActivity());
                    AddData(result);
                    editTxt.setText("");
                    Cursor data = myDB.getListContents();

                    if (data.getCount() == 0) {
                        Toast.makeText(getActivity(), "The Database was empty  :(.", Toast.LENGTH_LONG).show();
                    } else {
                        while (data.moveToNext()) {
                            arrayList.add(data.getString(1));
                            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);
                            list.setAdapter(adapter);
                        }
                    }
                }
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
                final int which_item = position;
                final String identity = adapter.getItem(position);
                new AlertDialog.Builder(getActivity()).setIcon(android.R.drawable.ic_delete).setTitle("Are you sure?")
                .setMessage("Do you want to delete this item? ").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        arrayList.remove(which_item);
                        myDB.deleteName(identity);
                        Toast.makeText(getActivity(), "Successfully Deleted", Toast.LENGTH_LONG).show();
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", null).show();
                return true;
            }
        });
        return myView;
    }

    public void AddData(String result) {
        myDB = new databaseHelper(getActivity());
        boolean answer = myDB.addData(result);
        if (answer == true) {
                Toast.makeText(getActivity(), "Successfully Entered", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getActivity(), "Something went wrong :(", Toast.LENGTH_LONG).show();
            }
        }
        
    @Override
    public void onClick(View view) {
    }
}

