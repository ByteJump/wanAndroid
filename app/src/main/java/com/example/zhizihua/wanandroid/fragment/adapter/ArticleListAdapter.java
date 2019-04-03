package com.example.zhizihua.wanandroid.fragment.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhizihua.wanandroid.Bean.ArticleBean;
import com.example.zhizihua.wanandroid.Bean.BannerBean;
import com.example.zhizihua.wanandroid.R;
import com.example.zhizihua.wanandroid.WebActivity;
import com.example.zhizihua.wanandroid.utils.GlideImageLoader;
import com.example.zhizihua.wanandroid.utils.imageLoader;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.LogUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhizihua on 2019/3/25.
 */

public class ArticleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ArticleBean.DataBean.DatasBean> datas = new ArrayList<>();
    private ArrayList<BannerBean.DataBean> bannerdata = new ArrayList<>();
    private OnItemClick onitemClick;
    private final int ITEM_TYPE_HEADER = 1;
    private final int ITEM_TYPE_NOMAL = 2;
    private List<String> images = new ArrayList<>();
    private List<String> titles = new ArrayList<>();

    public ArticleListAdapter(Context context, ArrayList<ArticleBean.DataBean.DatasBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    public void setBannerdata(ArrayList<BannerBean.DataBean> bannerdata) {
        this.bannerdata = bannerdata;
        for (int i = 0; i < bannerdata.size(); i++) {
            images.add(bannerdata.get(i).getImagePath());
            titles.add(bannerdata.get(i).getTitle());
        }
        Log.e("bannerdata", images.size() + "         " + titles.size() + "     " + bannerdata.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            View view = View.inflate(context, R.layout.article_item_header, null);
            return new HeaderViewHolder(view);
        } else {
            View view = View.inflate(context, R.layout.article_item, null);
            return new MyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder holder1 = (MyViewHolder) holder;
            holder1.title.setText(datas.get(position).getTitle());
            holder1.author.setText(datas.get(position).getAuthor());
            holder1.time.setText(datas.get(position).getNiceDate());
            if (!"".equals(datas.get(position).getEnvelopePic())) {
                holder1.image.setVisibility(View.VISIBLE);
                imageLoader.loadImage(context, datas.get(position).getEnvelopePic(), holder1.image);
            } else {
                holder1.image.setVisibility(View.GONE);
            }
            holder1.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onitemClick.onItemClick(position);
                }
            });
        }
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder holder2 = (HeaderViewHolder) holder;
            holder2.banner.setImageLoader(new GlideImageLoader());
            //设置banner样式
            holder2.banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
            //设置图片集合
            holder2.banner.setImages(images);
            holder2.banner.setBannerTitles(titles);
            //banner设置方法全部调用完毕时最后调用
            holder2.banner.start();
            holder2.banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("url", bannerdata.get(position).getUrl());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_HEADER;
        } else {
            return ITEM_TYPE_NOMAL;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    //定义设置点击事件监听的方法
    public void setOnitemClickLintener(OnItemClick onitemClick) {
        this.onitemClick = onitemClick;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, author, time;
        ImageView image;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            author = itemView.findViewById(R.id.tv_author);
            time = itemView.findViewById(R.id.tv_time);
            image = itemView.findViewById(R.id.iv_image);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        Banner banner;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }
}
