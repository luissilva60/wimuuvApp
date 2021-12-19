package com.example.wimuuvapplication.UI.Org;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wimuuvapplication.databinding.FragmentFeedOrgBinding;

public class FeedOrgFragment extends Fragment {

    FragmentFeedOrgBinding binding;


    public FeedOrgFragment() {
        // Required empty public constructor
    }

    public static FeedOrgFragment newInstance(String param1, String param2) {
        FeedOrgFragment fragment = new FeedOrgFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFeedOrgBinding.inflate(inflater, container,false);
        View root = binding.getRoot();

        return root;
    }
}