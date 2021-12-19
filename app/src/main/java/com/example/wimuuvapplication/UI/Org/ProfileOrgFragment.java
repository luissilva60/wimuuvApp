package com.example.wimuuvapplication.UI.Org;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.wimuuvapplication.R;
import com.example.wimuuvapplication.databinding.FragmentMapsBinding;
import com.example.wimuuvapplication.databinding.FragmentProfileOrgBinding;

public class ProfileOrgFragment extends Fragment {
    private Button settings;
    FragmentProfileOrgBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);

        binding = FragmentProfileOrgBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        settings = binding.imageButton4;
    }
}