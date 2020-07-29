package com.samir.appligadajustica.classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.samir.appligadajustica.adapter.AdapterCons;
import com.samir.appligadajustica.adapter.AdapterConsVil;
import com.samir.appligadajustica.adapter.AdapterEquip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Methods {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static boolean verify(EditText... editTexts){
        return Arrays.stream(editTexts).anyMatch(e -> e.getText().toString().isEmpty());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void clearFields(EditText... editTexts){
        Arrays.asList(editTexts).forEach(e -> e.setText(""));
    }

    public static void configRecycler(RecyclerView recycler, ArrayList arrayList, Context context, byte adapter){

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recycler.setLayoutManager(layoutManager);
        recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new DividerItemDecoration(context, LinearLayout.VERTICAL));

        if (adapter == 1) {
            AdapterEquip adapterEquip = new AdapterEquip(arrayList, context);
            recycler.setAdapter(adapterEquip);
            adapterEquip.notifyDataSetChanged();
        }
        if (adapter == 2){
            AdapterCons adapterCons = new AdapterCons(arrayList, context);
            recycler.setAdapter(adapterCons);
            adapterCons.notifyDataSetChanged();
        }
        if (adapter == 3){
            AdapterConsVil adapterConsVil = new AdapterConsVil(arrayList, context);
            recycler.setAdapter(adapterConsVil);
            adapterConsVil.notifyDataSetChanged();
        }
    }

    public static void swipe(final RecyclerView recycler, final ArrayList arrayList, final Context context, final Boolean direction){
        ItemTouchHelper.Callback itemTouch = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
                int swipeFlags = 0;
                if (direction == true)  swipeFlags = ItemTouchHelper.END;
                if (direction == false)  swipeFlags = ItemTouchHelper.START;
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Excluir");
                alertDialog.setMessage("Deseja excluir esse item?");
                alertDialog.setCancelable(false);

                alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position = viewHolder.getAdapterPosition();
                        arrayList.remove(position);
                        recycler.getAdapter().notifyItemRemoved(position);
                    }
                });

                alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        recycler.getAdapter().notifyDataSetChanged();
                    }
                });

                AlertDialog dialog = alertDialog.create();
                dialog.show();
            }
        };
        new ItemTouchHelper(itemTouch).attachToRecyclerView(recycler);
    }

}
