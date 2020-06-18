package com.me.kaily.beam.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.me.kaily.beam.R;
import com.me.kaily.beam.adapters.JournalAdapter;

public class JournalFragment extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private ArrayList<Entry> entries;
    private ImageButton btn;
    private JournalAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal, container, false);
        super.onCreate(savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.entries_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadEntries();

        btn = (ImageButton) view.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddNewEntry();
            }
        });

        return view;
    }

    private void loadEntries() {
        this.entries = new ArrayList<Entry>();
        entries.add(new Entry("this is a demo", new Date().getTime()));
        adapter = new JournalAdapter(getActivity(), entries);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void onAddNewEntry() {
        if (entries != null) {
            entries.add(new Entry("this is a new note", new Date().getTime()));
        }
        if (adapter != null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
    }
}
