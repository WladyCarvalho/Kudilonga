package io.expressive.kudilonga.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import io.expressive.kudilonga.R;
import io.expressive.kudilonga.databinding.FragmentHomeBinding;
import io.expressive.kudilonga.db.KudiDB;
import io.expressive.kudilonga.helper.PalavraAdapter;
import io.expressive.kudilonga.model.Palavra;

public class HomeFragment extends Fragment implements PalavraAdapter.OnItemClickListener {

    private FragmentHomeBinding binding;
    private PalavraAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //configurar o recyclerView
        adapter = new PalavraAdapter(getContext(),this::onItemClick);
        binding.recyclerV.setAdapter(adapter);
        binding.recyclerV.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerV.setAdapter(adapter);

        binding.btnAddPalavra.setOnClickListener(view -> {
            Navigation.findNavController(getView()).navigate(R.id.action_nav_home_to_addPalavra);
        });

        loadPalavras();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void loadPalavras(){
        KudiDB db = KudiDB.getInstance(getContext());
        List<Palavra> palavras = db.appDao().getPalavraList();
        adapter.setUserList(palavras);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPalavras();
    }

    @Override
    public void onItemClick(Palavra item) {

        Bundle bundle = new Bundle();
        bundle.putInt("id_palavra", item.getId());
        Navigation.findNavController(getView()).navigate(R.id.action_nav_home_to_palavraDetails,bundle);
        Toast.makeText(getContext(),"Clicado",Toast.LENGTH_LONG).show();
    }

    interface SendMessage {
        void sendData(String message);
    }
}