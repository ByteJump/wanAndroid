package com.example.zhizihua.wanandroid.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhizihua.wanandroid.Bean.KnowledgeItemBean;
import com.example.zhizihua.wanandroid.R;
import com.example.zhizihua.wanandroid.WebActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhizihua on 2019/3/28.
 */

public class KnowledgeItemAdapter extends RecyclerView.Adapter {
    private ArrayList<KnowledgeItemBean.DataBean.DatasBean> datas = new ArrayList<>();
    private Context context;

    public KnowledgeItemAdapter(ArrayList<KnowledgeItemBean.DataBean.DatasBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.article_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder myholder = (ViewHolder) holder;
        myholder.tvAuthor.setText(datas.get(position).getAuthor());
        myholder.tvTime.setText(datas.get(position).getNiceDate());
        myholder.tvTitle.setText(datas.get(position).getTitle());
        myholder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url", datas.get(position).getLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.cardview)
        CardView cardview;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
