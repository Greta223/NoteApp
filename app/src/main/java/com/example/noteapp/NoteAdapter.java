package com.example.noteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    public interface OnNoteDeleteListener {
        void onNoteDelete(int position);
    }

    private Context context;
    private List<Note> notes;
    private OnNoteDeleteListener deleteListener;

    public NoteAdapter(Context context, List<Note> notes, OnNoteDeleteListener deleteListener) {
        this.context = context;
        this.notes = notes;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.textViewTitle.setText(note.getName());

        holder.imageViewDelete.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onNoteDelete(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle;
        ImageView imageViewDelete;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
        }
    }
}
