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

import com.example.scanme.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends Fragment {

    NavController navController;
    private FragmentLoginBinding loginBinding;
    private FirebaseAuth mAuth;

    public Login() {
        // Required empty public constructor
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loginBinding = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false);
        return loginBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            Navigation.findNavController(requireView());
            navController.navigate(R.id.action_login_to_menuNavigation);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        loginBinding.tvJoinUs.setOnClickListener(v -> {
            navController.navigate(R.id.action_login_to_register);
        });

        loginBinding.btnSignIn.setOnClickListener(v -> {
            String email = loginBinding.etEmailLogin.getText().toString().trim();
            String password = loginBinding.etPasswordLogin.getText().toString().trim();

            if (TextUtils.isEmpty(email)){
                loginBinding.etEmailLogin.setError("Oops, please enter your email address");
                return;
            }
            if (TextUtils.isEmpty(password)){
                loginBinding.etEmailLogin.setError("Oops, please enter your password");
                return;
            }

            loginBinding.progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                navController.navigate(R.id.action_login_to_menuNavigation);
                                loginBinding.progressBar.setVisibility(View.INVISIBLE);
                            }
                            else {
                                Toast.makeText(getActivity(), "Error! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                loginBinding.progressBar.setVisibility(View.VISIBLE);
                            }
                        }
                    });

        });
    }
}