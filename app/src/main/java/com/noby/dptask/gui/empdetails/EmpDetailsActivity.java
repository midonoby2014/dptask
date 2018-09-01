package com.noby.dptask.gui.empdetails;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.noby.dptask.Data.beans.Employee;
import com.noby.dptask.R;
import com.noby.dptask.baseClasses.BaseActivity;
import com.noby.dptask.baseClasses.BaseModels;
import com.noby.dptask.baseClasses.BaseView;

import  com.noby.dptask.databinding.ActivityEmpDetailsBinding;
import com.noby.dptask.gui.maproute.MapRouteActivity;

public class EmpDetailsActivity extends BaseActivity  implements  EmpDetailsVIew, BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    EmpDetailsModelView  modelView ;
    Employee employee ;
    ActivityEmpDetailsBinding binding ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding   = DataBindingUtil.setContentView(this,R.layout.activity_emp_details);

        inilizeVaribles();
    }

    private void inilizeVaribles() {
        modelView = new EmpDetailsModelView();
        modelView.attachView(this);

        Intent intent = getIntent();
       // employee = (Employee) intent.getSerializableExtra("empdetails");
        employee = (Employee) intent.getExtras().getSerializable("empdetails");

        binding.setEmp(employee);
        if (employee.getPhone_numbers().size() !=0){
            String phonenumber =  " "  ;
            for (String  number : employee.getPhone_numbers()){
                phonenumber += number +"  ";
            }
            binding.txtphone.setText(phonenumber);
        }
        binding.btnroute.setOnClickListener((View) ->{
           openMapRoute();
        });
        inilizeSlider();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        modelView.detachView();
    }

    private void inilizeSlider() {
        binding.slider.setCustomIndicator(binding.customIndicator);
     //   private void inilizeSliderView( HashMap<String,String> file_maps) {
            for(String imageurl : employee.getProfile_pictures()) {
              //  Log.e("imageurlslider",imageurl);
                TextSliderView textSliderView = new TextSliderView(EmpDetailsActivity.this);
                textSliderView
                        //.description(name)

                        .image(imageurl)
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);

                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra",imageurl);

                binding.slider.addSlider(textSliderView);
            }
                binding.slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
                binding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                binding.slider.setCustomAnimation(new DescriptionAnimation());
                binding.slider.setDuration(4000);
                binding.slider.addOnPageChangeListener(EmpDetailsActivity.this);
       // }
    }

    @Override
    public BaseModels<? extends BaseView> getModel() {
        return modelView;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void openMapRoute() {
        Intent intent = new Intent(EmpDetailsActivity.this, MapRouteActivity.class);
        intent.putExtra("empdetails",employee);
        startActivity(intent);
    }
}
