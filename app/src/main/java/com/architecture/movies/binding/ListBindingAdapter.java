package com.architecture.movies.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.architecture.movies.data.Resource;
import com.architecture.movies.ui.BaseAdapter;

import java.util.List;

public final class ListBindingAdapter {
    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            return;
        }
        if (resource == null || resource.data == null) {
            return;
        }
        if (adapter instanceof BaseAdapter) {
            ((BaseAdapter) adapter).setData((List) resource.data);
        }
    }
}
