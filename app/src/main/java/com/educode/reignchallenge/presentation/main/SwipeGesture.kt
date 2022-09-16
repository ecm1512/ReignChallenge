package com.educode.reignchallenge.presentation.main

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.util.TypedValue
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.educode.reignchallenge.R
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator

abstract class SwipeGesture(val context: Context): ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

            RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX,dY,actionState, isCurrentlyActive)
                .addSwipeLeftLabel(context.getString(R.string.swipe_label_delete))
                .setSwipeLeftLabelTextSize(TypedValue.COMPLEX_UNIT_SP,20F)
                .setSwipeLeftLabelColor(context.getColor(R.color.white))
                .addSwipeLeftBackgroundColor(context.getColor(R.color.red))
                .create()
                .decorate()
    }
}