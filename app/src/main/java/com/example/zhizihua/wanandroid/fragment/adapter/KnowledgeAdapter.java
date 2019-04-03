package com.example.zhizihua.wanandroid.fragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhizihua.wanandroid.Bean.KnowledgeBean;
import com.example.zhizihua.wanandroid.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhizihua on 2019/3/27.
 */

public class KnowledgeAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<KnowledgeBean.DataBean> datas = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();
    private OnItemClick onItemClick;
    public KnowledgeAdapter(Context context, ArrayList<KnowledgeBean.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_knowledge, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ViewHolder holder1 = (ViewHolder) holder;
        holder1.tvTitle.setText(datas.get(position).getName());
        tags.clear();
        for (int i = 0; i < datas.get(position).getChildren().size(); i++){
            tags.add(datas.get(position).getChildren().get(i).getName());
        }
        holder1.idFlowlayout.setAdapter(new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.tv,
                        holder1.idFlowlayout, false);
                tv.setText(s);
                return tv;
            }
        });
        holder1.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.id_flowlayout)
        TagFlowLayout idFlowlayout;
        @BindView(R.id.iv_more)
        ImageView ivMore;
        @BindView(R.id.rl)
        RelativeLayout rl;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
    public void setOnItemClick(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }
    public interface OnItemClick{
        void onItemClick(int position);
    }
}
