package com.zhuyongit.recyclerviewdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuyongit.recyclerviewdemo.R;
import com.zhuyongit.recyclerviewdemo.bean.NewsBean;

import java.util.List;

/**
 * @author  zeno
 *
 * @email   zhuyongluck@qq.com
 *
 * @website http://www.zhuyongit.com
 *
 * @version 1.0
 *
 * RecyclerView基本使用 ，类似ListView控件 ， 列表显示
 */
public class BasicRecyclerAdapter extends RecyclerView.Adapter{

    private static final String TAG = "BasicRecyclerAdapter" ;

    private List<NewsBean> mDatas ;

    private OnLongClickListener     mOnLongClickListener ;

    public BasicRecyclerAdapter(List<NewsBean> datas) {
        this.mDatas = datas;
    }

    /**
     * 创建一个ViewHolder 复用控件对象
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle ;
        private TextView tvContent ;

        public ViewHolder(View rootView) {
            super(rootView);

            tvTitle = (TextView) rootView.findViewById(R.id.tvTitle);
            tvContent = (TextView) rootView.findViewById(R.id.tvContent);

            /**
             * 设置RecyclerView 单个Item对象长按事件
             */
            rootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnLongClickListener.onLongCick(v, getLayoutPosition());
                    return false;
                }
            });

        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvContent() {
            return tvContent;
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 返回单个Item的View对象
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basic_recycler_item_layout,parent,false) ;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 将数据绑定到控件上
        ViewHolder _viewHolder = (ViewHolder) holder;
        NewsBean  _newsBean = (NewsBean) mDatas.get(position);
        _viewHolder.getTvTitle().setText(_newsBean.getTitle());
        _viewHolder.getTvContent().setText(_newsBean.getContent());
    }

    @Override
      public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 向RecyclerView添加单个Item
     * @param position
     */
    public void addItem(int position) {
        mDatas.add(position, new NewsBean("落花虽有意", "奈何流水却无情"));

        // 插入Item的时候刷新单个Item
        notifyItemInserted(position);
    }

    /**
     * 删除ReclcerView单个Item
     * @param position
     */
    public void deleteItem(int position) {
        mDatas.remove(position) ;

        // 移除Item的时候刷新单个Item
        notifyItemRemoved(position);


    }

    /**
     * 设置单个Item的长按事件
     * @param listener
     */
    public void setOnLongClickListener(OnLongClickListener listener) {
            this.mOnLongClickListener = listener ;
    }

    /**
     * 定义个长按事件接口 ， 用于向外部提供长按事件处理接口
     */
    public interface OnLongClickListener {
        public void onLongCick(View view , int position) ;
    }
}
