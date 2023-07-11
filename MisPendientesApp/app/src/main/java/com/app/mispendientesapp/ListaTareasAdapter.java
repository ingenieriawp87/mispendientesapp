package com.app.mispendientesapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaTareasAdapter extends RecyclerView.Adapter<ListaTareasAdapter.ContactoViewHolder> {

    ArrayList<Tarea>listaTareas;
    public ListaTareasAdapter(ArrayList<Tarea> listaTareas){
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_items,parent,false);
        return  new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.textViewid.setText(listaTareas.get(position).getId());
        holder.textViewNombre.setText(listaTareas.get(position).getNombre());
        holder.textViewDetalle.setText(listaTareas.get(position).getDetalle());
        holder.textViewEstado.setText(listaTareas.get(position).getEstado());
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView textViewid, textViewNombre, textViewDetalle, textViewEstado;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewid = itemView.findViewById(R.id.textViewViewId);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            textViewDetalle = itemView.findViewById(R.id.textViewDetalle);
            textViewEstado = itemView.findViewById(R.id.textViewEstado);
        }
    }
}
