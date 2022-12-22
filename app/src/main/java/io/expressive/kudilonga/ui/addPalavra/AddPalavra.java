package io.expressive.kudilonga.ui.addPalavra;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import io.expressive.kudilonga.R;
import io.expressive.kudilonga.databinding.FragmentAddPalavraBinding;
import io.expressive.kudilonga.db.KudiDB;
import io.expressive.kudilonga.model.Exemplo;
import io.expressive.kudilonga.model.Palavra;
import io.expressive.kudilonga.ui.home.HomeViewModel;

public class AddPalavra extends Fragment {

    private AddPalavraViewModel mViewModel;
    private FragmentAddPalavraBinding binding;
    private List<Exemplo> exemplos;
    private int id_palavra=0;
    private KudiDB db;

    public static AddPalavra newInstance() {
        return new AddPalavra();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        AddPalavraViewModel mViewModel = new ViewModelProvider(this).get(AddPalavraViewModel.class);
        binding = FragmentAddPalavraBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        exemplos = new ArrayList<>();

        binding.btnSalvar.setOnClickListener(view -> {
            try {
                AdicionarPalavra();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });


        binding.btnAddExemplo.setOnClickListener(view -> {
            AddExempl();
            binding.containerPalavra.setVisibility(View.GONE);
            binding.containerExemplo.setVisibility(View.VISIBLE);
            binding.btnSalvar.setVisibility(View.GONE);
            binding.btnAddExemplo.setVisibility((View.GONE));
            binding.edtExemplo.setText("");
        });

        binding.btnSalvarExemplo.setOnClickListener(view -> {
            AddExemplo();
            binding.containerPalavra.setVisibility(View.VISIBLE);
            binding.containerExemplo.setVisibility(View.GONE);
            binding.btnSalvar.setVisibility(View.VISIBLE);
            binding.btnAddExemplo.setVisibility((View.VISIBLE));
        });
        return root;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void AdicionarPalavra(){

        if (id_palavra==0){
            id_palavra = (int) db.appDao().insertPalavra(new Palavra(binding.edtPalavra.getText().toString(),binding.edtSignificado.getText().toString()));
            binding.edtPalavra.setText("");
            binding.edtSignificado.setText("");
            Navigation.findNavController(getView()).navigate(R.id.action_addPalavra_to_nav_home);
        }

       if (exemplos.size()>0){
           for (Exemplo e:exemplos
           ) {
               db.appDao().insertExemplo(e);
           }
       }
        binding.edtPalavra.setText("");
        binding.edtSignificado.setText("");
        Navigation.findNavController(getView()).navigate(R.id.action_addPalavra_to_nav_home);
    }

    public void AddExemplo(){
        Exemplo e = new Exemplo();
        e.setId_palavra(id_palavra);
        e.setDescricao(binding.edtExemplo.getText().toString());
        exemplos.add(e);
    }

    public void AddExempl(){
        if (id_palavra==0)
        {
            db = KudiDB.getInstance(getContext());
            id_palavra = (int) db.appDao().insertPalavra(new Palavra(binding.edtPalavra.getText().toString(),binding.edtSignificado.getText().toString()));

            Log.d("IDID","ID GERADO: "+String.valueOf(id_palavra));
        }

    }

    }