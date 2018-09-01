package com.noby.dptask.Adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.noby.dptask.databinding.ListitemBinding;
import com.noby.dptask.Data.beans.Employee;
import com.noby.dptask.R;
import com.noby.dptask.gui.empdetails.EmpDetailsActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by A.Noby on 8/29/2018.
 */
public class ListEmployesAdapter extends RecyclerView.Adapter<ListEmployesAdapter.MyViewHolder> {

    private List<Employee> employeeList;
    private LayoutInflater layoutInflater;
    private ListEmployesAdapterListener listener;
    Context context ;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ListitemBinding binding;

        public MyViewHolder(final ListitemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
    public void addItems(List<Employee> items) {
        this.employeeList.addAll(items);
    }

    public ListEmployesAdapter(Context context, List<Employee> employeeList, ListEmployesAdapterListener listener) {
        this.employeeList = employeeList;
        this.listener = listener;
        this.context =  context ;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ListitemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.listitem, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.binding.setEmp(employeeList.get(position));
        if (employeeList.get(position).getProfile_pictures().size() != 0  ) {
            holder.binding.progressBar.setVisibility(View.VISIBLE);
            holder.binding.imgLoc.setVisibility(View.GONE);
            Picasso.with(context)
                    .load(employeeList.get(position).getProfile_pictures().get(0))
                    .into(holder.binding.imgLoc, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder.binding.progressBar.setVisibility(View.GONE);
                            holder.binding.imgLoc.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onError() {
                            holder.binding.progressBar.setVisibility(View.GONE);
                            holder.binding.imgLoc.setVisibility(View.VISIBLE);
                        }
                    });
        }
        holder.binding.revCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(context, EmpDetailsActivity.class);
                    intent.putExtra("empdetails", employeeList.get(position));
               //     intent.putExtra("emplat", employeeList.get(position).);
                 //   intent.putExtra("empdetails", employeeList.get(position));
                    context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public interface ListEmployesAdapterListener {
        void onitemPostClicked(Employee employee);
    }
}
