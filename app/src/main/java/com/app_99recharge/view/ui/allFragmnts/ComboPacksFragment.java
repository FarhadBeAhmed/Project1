package com.app_99recharge.view.ui.allFragmnts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app_99recharge.R;
import com.app_99recharge.databinding.FragmentComboPacksBinding;
import com.app_99recharge.service.model.responseBody.PackDatum;
import com.app_99recharge.view.adaptars.PackagesAdapter;
import com.app_99recharge.view.ui.PackagesFragment.AllPackagesActivity;
import com.app_99recharge.viewModel.PackagesViewModel;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

public class ComboPacksFragment extends Fragment {

    String operatorName;
    FragmentComboPacksBinding binding;
    PackagesViewModel packagesViewModel;
    KProgressHUD kProgressHUD;
    PackagesAdapter packagesAdapter;
    ArrayList<PackDatum>comboPackages=new ArrayList<>();

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
        initial();
    }

    @Override
    public void onResume() {
        super.onResume();
        //controlProgressBar(true);
        packagesViewModel.callForPackages(AllPackagesActivity.val);


    }

    @SuppressLint("NotifyDataSetChanged")
    private void initial() {

        binding.comboRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        packagesAdapter=new PackagesAdapter(getContext(),comboPackages, R.layout.package_single_row);
        binding.comboRecyclerView.setAdapter(packagesAdapter);
        packagesViewModel=new ViewModelProvider(this).get(PackagesViewModel.class);
        packagesViewModel.getData().observe(getViewLifecycleOwner(),packageResponse -> {
           // controlProgressBar(false);
            comboPackages.addAll(packageResponse.getPackData());
            packagesAdapter.notifyDataSetChanged();

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