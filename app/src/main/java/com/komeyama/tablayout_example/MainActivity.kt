package com.komeyama.tablayout_example

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_simple.view.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private var halfWidth = 0
    private var changeTabIndex = 5
    private var listSize = 20
    private var labelWidth = 358

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        halfWidth = getScreenWidth() / 2

        val adapter = GroupAdapter<GroupieViewHolder>()
        for (i in 1..listSize) {
            adapter.add(SimpleListItem("dummy_$i", tab_layout, this))
        }
        recycler_view.adapter = adapter

        mutableListOf(1, 2, 3, 4, 5).forEach {
            tab_layout.addTab(tab_layout.newTab().setText("sample_0$it"))
        }

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(p0: TabLayout.Tab?) {
                Timber.d("on tab selected %s", p0)
            }
        })

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var scrollDx = 0

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                scrollDx += dx
                val changeTabPosition = ((358 * (changeTabIndex - 1)) / 2) + (358 * (changeTabIndex - 1) % halfWidth) / 2
                if (scrollDx < (changeTabIndex * labelWidth - changeTabPosition)) {
                    Timber.d("change tab %s !", changeTabIndex)
                    Handler().postDelayed(
                        {
                            tab_layout.isSmoothScrollingEnabled = true
                            tab_layout.setScrollPosition(0, 0f, true)
                        }, 100
                    )
                }


                if (scrollDx > (changeTabIndex * labelWidth - changeTabPosition) && scrollDx < (2 * changeTabIndex * labelWidth - changeTabPosition)) {
                    Timber.d("change tab %s !", changeTabIndex)
                    Handler().postDelayed(
                        {
                            tab_layout.isSmoothScrollingEnabled = true
                            tab_layout.setScrollPosition(1, 0f, true)
                        }, 100
                    )
                }

                if (scrollDx > (2 * changeTabIndex * labelWidth - changeTabPosition) &&  scrollDx < (3 * changeTabIndex * labelWidth - changeTabPosition)) {
                    Timber.d("change tab %s !", 2 * changeTabIndex)
                    Handler().postDelayed(
                        {
                            tab_layout.isSmoothScrollingEnabled = true
                            tab_layout.setScrollPosition(2, 0f, true)
                        }, 100
                    )
                }

                if (scrollDx > (3 * changeTabIndex * labelWidth - changeTabPosition) &&  scrollDx < (4 * changeTabIndex * labelWidth - changeTabPosition)) {
                    Timber.d("change tab %s !", 3 * changeTabIndex)
                    Handler().postDelayed(
                        {
                            tab_layout.isSmoothScrollingEnabled = true
                            tab_layout.setScrollPosition(3, 0f, true)
                        }, 100
                    )
                }

                if (scrollDx > (4 * changeTabIndex * labelWidth - changeTabPosition)) {
                    Timber.d("change tab %s !", 4 * changeTabIndex)
                    Handler().postDelayed(
                        {
                            tab_layout.isSmoothScrollingEnabled = true
                            tab_layout.setScrollPosition(4, 0f, true)
                        }, 100
                    )
                }
            }
        })
    }

    private fun getScreenWidth(): Int {
        val displayMetrics = DisplayMetrics()
        this.display?.getRealMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }
}

class SimpleListItem(
    private val text: String,
    private val tabLayout: TabLayout,
    private val context: Context
) :
    Item<GroupieViewHolder>() {
    override fun getLayout() = R.layout.item_simple

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.dummy_text.text = text
        val width = viewHolder.root.dummy_text.layoutParams.width
        Timber.d("item width px: %s", width)
    }
}