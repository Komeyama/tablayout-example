package com.komeyama.tablayout_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_simple.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = GroupAdapter<GroupieViewHolder>()

        adapter.add(SimpleListItem("dummy01"))
        adapter.add(SimpleListItem("dummy02"))
        adapter.add(SimpleListItem("dummy03"))
        adapter.add(SimpleListItem("dummy04"))
        adapter.add(SimpleListItem("dummy05"))

        recycler_view.adapter = adapter

    }
}

class SimpleListItem(private val text: String) : Item<GroupieViewHolder>() {
    override fun getLayout() = R.layout.item_simple

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.dummy_text.text = text
    }
}