package br.edu.ifsc.firebase_android_luana;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {
    Context mContext;
    int mResource;
    ArrayList<Pessoa> mDataset;

    public PessoaAdapter(Context mContext, int mResource, ArrayList<Pessoa> mDataset) {
        this.mContext = mContext;
        this.mResource = mResource;
        this.mDataset = mDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(mResource, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pessoa pessoa = mDataset.get(position);
        holder.textViewNome.setText(pessoa.getNome());
        holder.textViewCpf.setText(pessoa.getCpf());
        holder.textViewSexo.setText(pessoa.getSexo());
        holder.textViewId.setText(pessoa.getId());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewId, textViewNome, textViewCpf, textViewSexo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewId = itemView.findViewById((R.id.textViewId));
            textViewNome = itemView.findViewById((R.id.textViewNome));
            textViewCpf = itemView.findViewById((R.id.textViewCpf));
            textViewSexo = itemView.findViewById((R.id.textViewSexo));
        }
    }
}

