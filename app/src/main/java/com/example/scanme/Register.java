package com.example.scanme;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.scanme.databinding.FragmentRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;


public class Register extends Fragment {

    private FragmentRegisterBinding registerBinding;
    private FirebaseAuth mAuth;
    NavController navController;

    public Register() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        registerBinding = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        registerBinding = FragmentRegisterBinding.inflate(inflater, container, false);
        return registerBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);


        registerBinding.tvSignIn.setOnClickListener(v -> navController.navigate(R.id.action_register_to_login));

        registerBinding.btnCreateAccount.setOnClickListener(v -> {
            String name = registerBinding.etName.getText().toString().trim();
            String fName = registerBinding.etFamilyName.getText().toString().trim();
            String email = registerBinding.etEmail.getText().toString().trim();
            String password = registerBinding.etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(name)){
                registerBinding.etName.setError("Oops, please enter your name");
                return;
            }
            if (TextUtils.isEmpty(fName)){
                registerBinding.etFamilyName.setError("Oops, please enter your family name");
                return;
            }
            if (TextUtils.isEmpty(email)){
                registerBinding.etEmail.setError("Oops, please enter your email address");
                return;
            }
            if (TextUtils.isEmpty(password)){
                registerBinding.etPassword.setError("Oops, please set a password");
                return;
            }

            registerBinding.progressBar.setVisibility(View.VISIBLE);

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getActivity(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                navController.navigate(R.id.action_register_to_menuNavigation);
                                registerBinding.progressBar.setVisibility(View.INVISIBLE);
                            }
                            else {
                                Toast.makeText(getActivity(), "Error!"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                registerBinding.progressBar.setVisibility(View.INVISIBLE);
                            }
                        }
                    });

        });
    }
}