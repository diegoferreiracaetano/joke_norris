package com.diegoferreiracaetano.chuckNorris.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.diegoferreiracaetano.chuckNorris.ui.main.adapter.CategoriesListAdapter
import com.diegoferreiracaetano.chuckNorris.util.ColorUtils
import com.github.pavlospt.roundedletterview.RoundedLetterView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("setReviewAdapter", "eventCallbacks", requireAll= false)
    fun RecyclerView.setReviewAdapter(items: List<String>?,callbacks: CategoriesListAdapter.Callbacks?) {
        items?.let {
            layoutManager = GridLayoutManager(context,2)
            adapter = CategoriesListAdapter(it,callbacks)
        }
    }

    @JvmStatic
    @BindingAdapter("setImageUrl")
    fun ImageView.setImageUrl(url: String?) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(this)
    }

    @JvmStatic
    @BindingAdapter("roundColor")
    fun RoundedLetterView.roundColor(s: String) {
        this.backgroundColor = ColorUtils.randomColor;
        this.titleText = s.take(1)
    }

    @JvmStatic
    @BindingAdapter("showLongMessage", "callback", requireAll = false)
    fun showLongMessage(view: View, throwable: Throwable?, callback: BaseTransientBottomBar.BaseCallback<Snackbar>? = null) {
        throwable?.let {
            val snackbar = Snackbar.make(view, "Error load", Snackbar.LENGTH_LONG)
            if (callback != null) snackbar.addCallback(callback)
            snackbar.show()
        }
    }
}