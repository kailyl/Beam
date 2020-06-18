package com.me.kaily.beam.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.me.kaily.beam.R;
import com.me.kaily.beam.model.Entry;
import com.me.kaily.beam.utils.EntryUtils;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.JournalHolder>{

    private ArrayList<Entry> entries;
    private Context context;

    public JournalAdapter(Context context, ArrayList<Entry> entries) {
        this.context = context;
        this.entries = entries;
    }

    @Override
    public JournalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.journal_layout, null, false);
        return new JournalHolder(v);
    }

    @Override
    public void onBindViewHolder(JournalHolder holder, int position) {
        Entry entry = getEntry(position);
        if (entry != null) {
            holder.journalText.setText(entry.getEntryText());
            holder.journalDate.setText(EntryUtils.dataFromLong(entry.getEntryDate()));
        }
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    private Entry getEntry(int position) {
        return entries.get(position);
    }

    class JournalHolder extends RecyclerView.ViewHolder {

        TextView journalText, journalDate;

        public JournalHolder(@NonNull View itemView) {
            super(itemView);
            journalDate = itemView.findViewById(R.id.entry_date);
            journalText = itemView.findViewById(R.id.journal_text);
        }
    }
}
