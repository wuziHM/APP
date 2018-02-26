package com.hlib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * RecyclerView适配器二次封装，封装了单击、长按、添加头部、添加底部功能。<br/>
 * <p/>
 * 使用方法：跟官方RecyclerView.Adapter使用方法基本一样，唯一不同的是不局文件在getItemViewLayoutId配置。<br/>
 * <p/>
 * 注意：<br/>
 * 1、添加头部、底部视图要在setAdapter之前调用<br/>
 * <p/>
 * 缺点：没有考虑到不同布局的处理<br/>
 * Created by moguangjian on 15/10/23 10:36.
 */
public abstract class MRecyclerViewAdapter<VH extends RecyclerView.ViewHolder,T> extends RecyclerView.Adapter<VH> implements MRecyclerViewAdapterAble {

    private static final int VIEW_TYPE_HEADER_VIEW = 1215;
    private static final int VIEW_TYPE_FOOTER_VIEW = 1216;
    private static final String TAG = MRecyclerViewAdapter.class.getSimpleName();

    private RelativeLayout headerView;
    private RelativeLayout footerView;
    private Context context;
    protected List list;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Object data, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(View view, Object data, int position);
    }

    public MRecyclerViewAdapter(Context context){
        this.context = context;
    }

    public MRecyclerViewAdapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    protected abstract int getItemViewLayoutId();

    protected abstract VH getViewHolder(View view);

    protected abstract void onBindViewHolderData(VH holder, int position);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        View itemView = null;
        try {
            if (viewType == VIEW_TYPE_HEADER_VIEW && headerView != null) {
                view = LayoutInflater.from(context).inflate(getItemViewLayoutId(), headerView, false);
                view.setVisibility(View.GONE);
                headerView.removeView(view);
                headerView.addView(view);
                itemView = headerView;

            } else if (viewType == VIEW_TYPE_FOOTER_VIEW && footerView != null) {
                view = LayoutInflater.from(context).inflate(getItemViewLayoutId(), footerView, false);
                view.setVisibility(View.GONE);
                footerView.removeView(view);
                footerView.addView(view);
                itemView = footerView;
            } else {
                itemView = LayoutInflater.from(context).inflate(getItemViewLayoutId(), parent, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        if ((headerView != null && position == 0) || (footerView != null && position == getItemCount() - 1)) {
            // 如果是header或者是footer则不处理
        } else {
            int pos = position;
            if (headerView != null) {
                pos--;
            }

            initListener(holder, pos);
            onBindViewHolderData(holder, pos);
        }
    }

    @Override
    public int getItemCount() {
        int headerOrFooter = 0;
        if (headerView != null) {
            headerOrFooter++;
        }
        if (footerView != null) {
            headerOrFooter++;
        }
        int count = (list == null ? 0 : list.size());
        count = count + headerOrFooter;
        return count;
    }

    @Override
    public int getCount() {
        int count = (list == null ? 0 : list.size());
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (footerView != null && position == getItemCount() - 1) {
            return VIEW_TYPE_FOOTER_VIEW;

        } else if (headerView != null && position == 0) {
            return VIEW_TYPE_HEADER_VIEW;
        } else {
            if (headerView != null) {
                return super.getItemViewType(position - 1);
            }
            return super.getItemViewType(position);
        }
    }

    public Context getContext() {
        return context;
    }

    public Object getItem(int position) {
        Object object = null;
        try {
            if (list != null && !list.isEmpty()) {
                if (position >= getItemCount()) {
                    position = getItemCount() - 1;
                }

                if (position < 0) {
                    position = 0;
                }

                object = list.get(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public void addItem(Object object, int position) {
        list.add(position, object);
        notifyItemInserted(position);
    }

    @Override
    public void removeItem(int position) {
        try {
            if (isValidPosition(position)) {
                list.remove(position);
                notifyItemRemoved(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeItem(Object object) {
        int position = list.indexOf(object);
        removeItem(position);
    }

    @Override
    public boolean isValidPosition(int position) {
        if ((list == null) || list.isEmpty() || (position < 0) || (position >= getItemCount())) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isEndItem(int position) {
        if (getItemCount() > 0 && position == getItemCount() - 1) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isFirstItem(int position) {
        if (getItemCount() > 0 && position == 0) {
            return true;
        }

        return false;
    }

    private void initListener(VH holder, final int position) {
        final Object object = getItem(position);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, object, position);
                }
            });
        }

        if (onItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(v, object, position);
                    return false;
                }
            });
        }
    }

    @Override
    public void setFooterView(RelativeLayout footerView) {
        try {
            this.footerView = footerView;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFooterView(View footerView) {
        try {
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
            relativeLayout.setLayoutParams(params);
            relativeLayout.addView(footerView);

            this.footerView = relativeLayout;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RelativeLayout getFooterView() {
        return footerView;
    }

    public RelativeLayout getHeaderView() {
        return headerView;
    }

    public void setHeaderView(RelativeLayout headerView) {
        try {
            this.headerView = headerView;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addHeaderView(View headerView) {
        try {
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
            relativeLayout.setLayoutParams(params);
            relativeLayout.addView(headerView);

            this.headerView = relativeLayout;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
