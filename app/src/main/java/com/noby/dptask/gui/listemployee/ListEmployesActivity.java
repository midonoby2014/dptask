package com.noby.dptask.gui.listemployee;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.noby.dptask.Adapter.ListEmployesAdapter;
import com.noby.dptask.Data.beans.Employee;
import com.noby.dptask.R;
import com.noby.dptask.baseClasses.BaseActivity;
import com.noby.dptask.baseClasses.BaseModels;
import com.noby.dptask.baseClasses.BaseView;
import com.noby.dptask.databinding.ActivityListEmployesBinding;

import java.util.List;

public class ListEmployesActivity extends BaseActivity
        implements  ListEmployesView , ListEmployesAdapter.ListEmployesAdapterListener {

    private ListEmployesAdapter adapter;
    ActivityListEmployesBinding  binding ;
    private boolean loading = false;
    private int pageNumber = 1,TotalPage =0 ;
    private LinearLayoutManager layoutManager;
    ListEmployesViewModel viewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_list_employes);
        inilizeVaribles();
    }
    private void inilizeVaribles() {
        viewModel  = new ListEmployesViewModel();
        viewModel.attachView(this);
         viewModel.SendData(ListEmployesActivity.this,binding.coorlist,pageNumber,false);
        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (! binding.recyclerView.canScrollVertically(1)) {
                        Log.e("isloading", loading + "");
                        if (loading && pageNumber < TotalPage) {
                            GetNewData();
                        }
                }
            }
        });
    }
    @Override
    public BaseModels<? extends BaseView> getModel() {
        return viewModel;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.detachView();
    }
    @Override
    public void onitemPostClicked(Employee employee) {
    }
    @Override
    public void LoadingData(List<Employee> employes,boolean Loading,  int TotalPageNUmber) {
        loading =  Loading ;
        TotalPage =  TotalPageNUmber ;
        Log.e("daatasize",""+employes.size());
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapter = new ListEmployesAdapter(ListEmployesActivity.this ,employes,this);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setNestedScrollingEnabled(true);
    }
    @Override
    public void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideProgressBar() {
        binding.progressBar.setVisibility(View.INVISIBLE);
    }
    @Override
    public void LoadingNewData(List<Employee> employees,boolean Loading) {
        loading  =  Loading ;
        adapter.addItems(employees);
        adapter.notifyDataSetChanged();
    }
    private void GetNewData () {
        pageNumber ++ ;
        viewModel.SendData(ListEmployesActivity.this,binding.coorlist,pageNumber,true);
    }
}
