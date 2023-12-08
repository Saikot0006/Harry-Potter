package com.example.harrypotter.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.harrypotter.R

@BindingAdapter("android:imageUrl")
fun setImageUrl(imageView: ImageView,url : String?){
   try {
       Glide.with(imageView)
           .load(url)
           .error(R.drawable.image_error_placeholder)
           .placeholder(R.drawable.image_loading_placeholder)
           .centerCrop()
           .into(imageView)
   }catch (e:Exception){}
}