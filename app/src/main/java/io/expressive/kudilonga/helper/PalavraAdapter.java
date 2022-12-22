package io.expressive.kudilonga.helper;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.expressive.kudilonga.R;
import io.expressive.kudilonga.model.Palavra;

public class PalavraAdapter extends RecyclerView.Adapter<PalavraAdapter.MyViewHolder> {

    private Context context;
    private List<Palavra> userList;
    private OnItemClickListener listener;

    public PalavraAdapter(Context context,OnItemClickListener listener) {

        this.context = context;
        this.listener = listener;
    }

    public void setUserList(List<Palavra> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PalavraAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_palavra, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PalavraAdapter.MyViewHolder holder, int position) {
        holder.bind(this.userList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return  this.userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView palavra;

        public MyViewHolder(View view) {
            super(view);
            palavra = view.findViewById(R.id.palavra_it);
        }

        public void bind(final Palavra p,final OnItemClickListener listener){
                palavra.setText(p.getPalavra());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClick(p);
                    }
                });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Palavra item);
    }
}
