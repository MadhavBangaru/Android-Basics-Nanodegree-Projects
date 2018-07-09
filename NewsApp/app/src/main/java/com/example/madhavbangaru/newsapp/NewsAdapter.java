package com.example.madhavbangaru.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

/**
 * Created by Madhav Bangaru on 06-07-2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context,@NonNull List<News> news) {
        super(context, 0, news);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_item,parent,false);

        }
        News currentNews = getItem(position);

        TextView section = listItemView.findViewById(R.id.category);
        section.setText(currentNews.getCategory());

        TextView title =  listItemView.findViewById(R.id.title);
        title.setText(currentNews.getTitle());

        TextView author = listItemView.findViewById(R.id.author);
        author.setText(currentNews.getmAuthor());

        TextView date = listItemView.findViewById(R.id.date);
        date.setText(currentNews.getDateTime());

        return listItemView;
    }

}
