package com.mahmoud.dahdouh.archcomponents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {

    //private List<Note> notes = new ArrayList<>();

    public static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };
    private OnItemClickListener listener;

    protected NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);

        return new NoteHolder(v);
    }

//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }
//
//    public void setNotes(List<Note> notes) {
//        this.notes = notes;
//        notifyDataSetChanged();
//    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        //holder.bind(position);
        Note currentNote = getItem(position);
        holder.tv_priority.setText(String.valueOf(currentNote.getPriority()));
        holder.tv_description.setText(currentNote.getDescription());
        holder.tv_title.setText(currentNote.getTitle());
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView tv_title;
        private TextView tv_priority;
        private TextView tv_description;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_priority = itemView.findViewById(R.id.tv_priority);
            tv_description = itemView.findViewById(R.id.tv_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }

//        void bind(int position) {
//            tv_priority.setText(String.valueOf(notes.get(position).getPriority()));
//            tv_description.setText(notes.get(position).getDescription());
//            tv_title.setText(notes.get(position).getTitle());
//        }

    }
}
