package com.example.zhizihua.wanandroid.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.example.zhizihua.wanandroid.Bean.NavagationBean;
import com.example.zhizihua.wanandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhizihua on 2019/4/1.
 */

public class NavagationRightAdapter extends RvAdapter<NavagationBean.DataBean.TitlesBean> {
    private ArrayList<String> tags = new ArrayList<>();
    private Context context;
    public NavagationRightAdapter(Context context, List<NavagationBean.DataBean.TitlesBean> list, ItemClickListener listener) {
        super(context, list, listener);
        this.context = context;
    }

    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new NavagationRightHolder(view, viewType, listener);
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).isIstitle() ? 0 : 1;
    }

    @Override
    protected int getLayoutId(int viewType) {
        return viewType == 0 ? R.layout.item_title : R.layout.item_city;
    }

    class NavagationRightHolder extends RvHolder<NavagationBean.DataBean.TitlesBean> {

        private TextView title;
        private TextView city;

        public NavagationRightHolder(View itemView, int type, ItemClickListener listener) {
            super(itemView, type, listener);
            switch (type) {
                case 0:
                    title = itemView.findViewById(R.id.tv_title);
                    break;
                case 1:
                    city = itemView.findViewById(R.id.tv_tag);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void bindHolder(NavagationBean.DataBean.TitlesBean TitlesBean, int position) {
            int itemViewTtpe = NavagationRightAdapter.this.getItemViewType(position);
            switch (itemViewTtpe) {
                case 0:
                    title.setText(list.get(position).getTitleName());
                    break;
                case 1:
                    city.setText(list.get(position).getTitle());
                    break;
                case 2:
                    break;
            }
        }
    }
}
