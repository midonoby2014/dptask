package com.noby.dptask.gui.listemployee;

import com.noby.dptask.Data.beans.Employee;
import com.noby.dptask.baseClasses.BaseView;

import java.util.List;

/**
 * Created by A.Noby on 8/29/2018.
 */
interface ListEmployesView extends BaseView  {

    void LoadingData(List<Employee>  employees,boolean Loading ,  int TotalPageNUmber);
    void showProgressBar();
    void hideProgressBar();
    void LoadingNewData (List<Employee>  employees,boolean Loading);
}
