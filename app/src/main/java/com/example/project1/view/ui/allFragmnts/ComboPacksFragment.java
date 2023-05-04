package com.example.project1.view.ui.allFragmnts;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project1.databinding.FragmentComboPacksBinding;
import com.example.project1.service.model.responseBody.PackDatum;
import com.example.project1.service.model.responseBody.PackageResponse;
import com.example.project1.view.adaptars.PackagesAdapter;
import com.example.project1.view.ui.PackagesFragment.RechargeConfirmFragment;
import com.example.project1.R;
import com.example.project1.viewModel.PackagesViewModel;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

public class ComboPacksFragment extends Fragment {

    String operatorName;
    TextView combo;

    FragmentComboPacksBinding binding;
    PackagesViewModel packagesViewModel;
    KProgressHUD kProgressHUD;
    PackagesAdapter packagesAdapter;
    ArrayList<PackDatum>comboPackages=new ArrayList<>();

    public ComboPacksFragment(String operatorName) {
        this.operatorName=operatorName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding=FragmentComboPacksBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       /* combo=view.findViewById(R.id.comboId);
        combo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new RechargeConfirmFragment(),"RechargeConfirmFragment").addToBackStack("RechargeConfirmFragment").commit();
            }
        });*/


        initial();
    }

    @Override
    public void onResume() {
        super.onResume();
        controlProgressBar(true);
        packagesViewModel.callForPackages(operatorName);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void initial() {

        binding.comboRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        packagesAdapter=new PackagesAdapter(getContext(),comboPackages,R.layout.package_single_row);
        binding.comboRecyclerView.setAdapter(packagesAdapter);


        packagesViewModel=new ViewModelProvider(this).get(PackagesViewModel.class);
        packagesViewModel.getData().observe(getViewLifecycleOwner(),packageResponse -> {
            comboPackages.addAll(packageResponse.getPackData());
            packagesAdapter.notifyDataSetChanged();
            controlProgressBar(false);
        });

    }

    public void controlProgressBar(boolean isShowProgressBar) {

        if (isShowProgressBar) {
            try {
                if (this.kProgressHUD != null && this.kProgressHUD.isShowing()) {
                    this.kProgressHUD.dismiss();
                }
                kProgressHUD= KProgressHUD.create(requireContext())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
            } catch (Exception e) {

            }
        } else if (this.kProgressHUD != null && this.kProgressHUD.isShowing()) {
            this.kProgressHUD.dismiss();
        }
    }
}