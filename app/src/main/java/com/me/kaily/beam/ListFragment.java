package com.me.kaily.beam;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment implements View.OnClickListener {

    databaseHelperBucketList mHelper;
    private EditText editTxt;
    private ImageButton btn;
    private ListView list;
    private TextView textview2;
    private ArrayAdapter<String> madapter;
    private ArrayList<String> newArrayList;
    String identity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        super.onCreate(savedInstanceState);

        list = (ListView) view.findViewById(R.id.bucket_list);
        editTxt = (EditText) view.findViewById(R.id.editText);
        btn = (ImageButton) view.findViewById(R.id.button);

        mHelper = new databaseHelperBucketList(getActivity());
        newArrayList = new ArrayList<String>();

        Cursor data = mHelper.getListContents();
        while (data.moveToNext()) {
            newArrayList.add(data.getString(1));
            madapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, newArrayList);
            list.setAdapter(madapter);
            list.setItemsCanFocus(false);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = editTxt.getText().toString();
                newArrayList = new ArrayList<String>();

                if (editTxt.length() != 0) {
                    //myDB = new databaseHelper(getActivity());
                    AddData(result);
                    editTxt.setText("");
                    Cursor data = mHelper.getListContents();

                    if (data.getCount() == 0) {
                        Toast.makeText(getActivity(), "The Database was empty  :(.", Toast.LENGTH_LONG).show();
                    } else {
                        while (data.moveToNext()) {
                            newArrayList.add(data.getString(1));
                            madapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, newArrayList);
                            list.setAdapter(madapter);
                        }
                    }
                }
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {
                final int which_item = position;
                final String identity = madapter.getItem(position);
                new AlertDialog.Builder(getActivity()).setIcon(android.R.drawable.ic_delete).setTitle("Are you sure?")
                        .setMessage("Do you want to delete this item? ").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        newArrayList.remove(which_item);
                        mHelper.deleteName(identity);
                        Toast.makeText(getActivity(), "Successfully Deleted", Toast.LENGTH_LONG).show();
                        madapter.notifyDataSetChanged();
                    }
                })
                        .setNegativeButton("No", null).show();
                return true;
            }
        });

        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                identity = madapter.getItem(position);
                if (identity.contains("✔")) {
                    identity = identity;
                } else {
                    String newIdentity = identity + "    ✔ --- Completed";
                    list.setItemChecked(position, true);
                    mHelper.update(newIdentity, position, identity);
                    Toast.makeText(getActivity(), "Successfully Completed", Toast.LENGTH_LONG).show();
                    madapter.notifyDataSetChanged();

                }
            }
        });

        return view;
    }

    public void AddData(String result) {
        mHelper = new databaseHelperBucketList(getActivity());
        boolean answer = mHelper.addData(result);
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
