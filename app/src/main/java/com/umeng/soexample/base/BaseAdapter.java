package com.umeng.soexample.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<D> extends RecyclerView.Adapter {

    List<D> mList;
    protected Context context;
    protected IListClick click;
    protected int pos;


    protected IItemViewClick iItemViewClick;

    public interface IItemViewClick<D> {
        //条目中的元素点击
        void itemViewClick(int viewid, D data);
    }

    //接口回调
    public void addItemViewClick(IItemViewClick click) {
        this.iItemViewClick = click;
    }

    public BaseAdapter(List<D> List, Context context) {
        this.mList = List;
        this.context = context;
    }

    protected ShowUnReadItem showUnReadItem;

    public interface ShowUnReadItem<D> {
        void showUnRead(int viewid, D data);
    }

    public void setShowUnReadItem(ShowUnReadItem showUnReadItem) {
        this.showUnReadItem = showUnReadItem;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( getLayout(), parent, false );
        VH vh = new VH( view );
        vh.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //接口回调调用
                if (click != null) {
                    click.itemClick( vh.getAdapterPosition() );
                }
            }
        } );
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        pos = position;
        bindData( mList.get( position ), (VH) holder );

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected List<D> getData() {
        return mList;
    }

    protected abstract int getLayout();

    protected abstract void bindData(D data, VH vh);


    public void setClick(IListClick click) {
        this.click = click;
    }

    public interface IListClick {
        void itemClick(int positions);
    }

    public class VH extends RecyclerView.ViewHolder {


        SparseArray views = new SparseArray();

        public VH(@NonNull View itemView) {
            super( itemView );
        }

        //查找item的view
        public View getViewById(int id) {
            View view = (View) views.get( id );
            if (view == null) {
                view = itemView.findViewById( id );
                views.append( id, view );
            }
            return view;

        }
    }
}
