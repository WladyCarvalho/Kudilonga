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
import io.expressive.kudilonga.model.Exemplo;

public class ExemploAdapter extends RecyclerView.Adapter<ExemploAdapter.MyViewHolder> {

    private Context context;
    private List<Exemplo> userList;
    private PalavraAdapter.OnItemClickListener listener;

    public ExemploAdapter(Context context) {

        this.context = context;
    }

    public void setUserList(List<Exemplo> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExemploAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_exemplo, parent, false);
        return new ExemploAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExemploAdapter.MyViewHolder holder, int position) {
        holder.bind(this.userList.get(position));
    }

    @Override
    public int getItemCount() {
        return  this.userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView palavra;

        public MyViewHolder(View view) {
            super(view);
            palavra = view.findViewById(R.id.txt_exemplo);
        }

        public void bind(final Exemplo p){
            palavra.setText(p.getDescricao());

        }
    }


}
