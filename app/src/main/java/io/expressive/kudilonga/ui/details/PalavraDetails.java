package io.expressive.kudilonga.ui.details;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.expressive.kudilonga.R;
import io.expressive.kudilonga.databinding.FragmentAddPalavraBinding;
import io.expressive.kudilonga.databinding.FragmentPalavraDetailsBinding;
import io.expressive.kudilonga.db.KudiDB;
import io.expressive.kudilonga.helper.ExemploAdapter;
import io.expressive.kudilonga.helper.PalavraAdapter;
import io.expressive.kudilonga.model.Exemplo;
import io.expressive.kudilonga.model.Palavra;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PalavraDetails extends Fragment {

    private PalavraDetailsViewModel mViewModel;
    private FragmentPalavraDetailsBinding binding;
    private int id_palavra;
    private Palavra palavra;
    private ExemploAdapter adapter;

    public static PalavraDetails newInstance() {
        return new PalavraDetails();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        id_palavra = getArguments().getInt("id_palavra");
        palavra = getPalavra(id_palavra);

        binding = FragmentPalavraDetailsBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        binding.txtPalavra.setText(palavra.getPalavra());
        binding.txtSignificado2.setText(palavra.getSignificado());

        adapter = new ExemploAdapter(getContext());
        binding.recyclerDt.setAdapter(adapter);
        binding.recyclerDt.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerDt.setAdapter(adapter);

        try {
            getAllExamples(id_palavra);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PalavraDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

    private Palavra getPalavra(int id){
        KudiDB db = KudiDB.getInstance(getContext());
        Palavra palavra = db.appDao().getPalavra(id);
        return palavra;
    }

    private void getAllExamples(int id) throws InterruptedException {
        KudiDB db = KudiDB.getInstance(getContext());



        List<Exemplo> exemplos = db.appDao().getExemplos(id);
        Log.d("EXEMPLOS",exemplos.toString());
        adapter.setUserList(exemplos);






    }


    @Override
    public void onResume() {
        super.onResume();

        id_palavra = getArguments().getInt("id_palavra");
        palavra = getPalavra(id_palavra);
        try {
            getAllExamples(id_palavra);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}