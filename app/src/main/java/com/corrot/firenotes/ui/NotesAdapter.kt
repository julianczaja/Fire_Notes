package com.corrot.firenotes.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.corrot.firenotes.R
import com.corrot.firenotes.model.Note
import com.corrot.firenotes.utils.inflate
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter(private var notes: List<Note>) :
    RecyclerView.Adapter<NotesAdapter.NoteHolder>() {

    class NoteHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val view = v

        fun bind(n: Note) {
            view.tv_item_note_title.text = n.title
            view.tv_item_note_body.text = n.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val inflatedView = parent.inflate(R.layout.item_note, attachToRoot = false)
        return NoteHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    fun setNotes(notes: List<Note>) {
        val diff = notifyNotesChanged(notes, this.notes)
        this.notes = notes
        diff.dispatchUpdatesTo(this)
    }

    private fun notifyNotesChanged(
        newNotes: List<Note>,
        oldNotes: List<Note>
    ): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = oldNotes.size
            override fun getNewListSize(): Int = newNotes.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldNotes[oldItemPosition].title == newNotes[newItemPosition].title

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldNotes[oldItemPosition].body == newNotes[newItemPosition].body
        })
    }
}