package com.fitness365.fitindia.adapters;

import android.content.Context;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.fitness365.fitindia.R;
import com.fitness365.fitindia.models.Category;

import java.util.ArrayList;

/**
 * Created by shyju New System on 26-Feb-18.
 */

public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Category> categoryArrayList;
    Context context;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

//    class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView categoryTitle;
//        ImageView categoryImage;
//
//        private MyViewHolder(View view) {
//            super(view);
//            categoryTitle = view.findViewById(R.id.category_title);
//            categoryImage = view.findViewById(R.id.category_image);
//
//        }
//    }


    public CategoriesRecyclerAdapter(Context context, ArrayList<Category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            // Here Inflating your recyclerview item layout
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_category_banner_layout, parent, false);
            return new ItemViewHolder(itemView);

        } else if (viewType == TYPE_HEADER) {
            // Here Inflating your header view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_category_header, parent, false);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
            int deviceWidth = displayMetrics.widthPixels;
            int item_width = deviceWidth - (deviceWidth/100 * 15);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            params.width = item_width;
            itemView.setLayoutParams(params);
            return new HeaderViewHolder(itemView);
        }
        else return null;
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.category_layout, parent, false);
//
//        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //holder.categoryTitle.setText(category.getCategoryTitle());
        if (holder instanceof HeaderViewHolder){
//            setheadersdata_flag = true;
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
//            headerViewHolder.txt_needsreview.setText(“YOUR _HEADERVIEW_STRING”);

        }
        else if (holder instanceof ItemViewHolder){

            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

            // Following code give a row of header and decrease the one position from listview items
            final Category data = categoryArrayList.get(position-1);

            // You have to set your listview items values with the help of model class and you can modify as per your needs

            Glide.with(context).load(data.getBannerurl())
                    .thumbnail(0.5f)
                    .into(itemViewHolder.categoryImage);

        }


    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        CardView registerFitIndiaChampionCard;

        private HeaderViewHolder(View view) {
            super(view);
            registerFitIndiaChampionCard = view.findViewById(R.id.register_fit_champion);

        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView categoryTitle;
        ImageView categoryImage;

        private ItemViewHolder(View view) {
            super(view);
//            categoryTitle = view.findViewById(R.id.category_title);
            categoryImage = view.findViewById(R.id.category_image);

        }
    }
}
