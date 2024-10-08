package com.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private EditText fullNameEditText, phoneEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button signupButton;
    private TextView loginTextView;
    private ImageView togglePasswordVisibility, toggleConfirmPasswordVisibility;
    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_layout);

        // Initialize views
        fullNameEditText = findViewById(R.id.fullname);
        phoneEditText = findViewById(R.id.phone);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        signupButton = findViewById(R.id.signup_button);
        loginTextView = findViewById(R.id.log_in_new);
        togglePasswordVisibility = findViewById(R.id.togglePasswordVisibility);
        toggleConfirmPasswordVisibility = findViewById(R.id.toggleConfirmPasswordVisibility);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    registerUser();
                }
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });

        // Password visibility toggle
        togglePasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    togglePasswordVisibility.setImageResource(R.mipmap.hide); // Change to hide icon
                } else {
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    togglePasswordVisibility.setImageResource(R.mipmap.show); // Change to show icon
                }
                passwordEditText.setSelection(passwordEditText.length()); // Move cursor to end
                isPasswordVisible = !isPasswordVisible;
            }
        });

        // Confirm Password visibility toggle
        toggleConfirmPasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConfirmPasswordVisible) {
                    confirmPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    toggleConfirmPasswordVisibility.setImageResource(R.mipmap.hide); // Change to hide icon
                } else {
                    confirmPasswordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    toggleConfirmPasswordVisibility.setImageResource(R.mipmap.show); // Change to show icon
                }
                confirmPasswordEditText.setSelection(confirmPasswordEditText.length()); // Move cursor to end
                isConfirmPasswordVisible = !isConfirmPasswordVisible;
            }
        });
    }

    private boolean validateForm() {
        boolean isValid = true;

        String fullName = fullNameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (fullName.isEmpty()) {
            fullNameEditText.setError("Full name is required");
            isValid = false;
        }

        if (phone.isEmpty()) {
            phoneEditText.setError("Phone number is required");
            isValid = false;
        }

        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Enter a valid email address");
            isValid = false;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            isValid = false;
        } else if (password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters long");
            isValid = false;
        }

        if (!confirmPassword.equals(password)) {
            confirmPasswordEditText.setError("Passwords do not match");
            isValid = false;
        }

        return isValid;
    }

    private void registerUser() {
        String url = "http://10.0.2.2/fitness/register.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String message = jsonResponse.getString("message");
                            Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_LONG).show();

                            if (message.equals("Registration successful")) {
                                // Registration successful, navigate to the next activity
                                Intent intent = new Intent(SignUpActivity.this, GenderActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(SignUpActivity.this, "Error parsing response: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage = "An error occurred";
                        if (error instanceof com.android.volley.NetworkError || error instanceof com.android.volley.NoConnectionError) {
                            errorMessage = "Network error. Please check your connection.";
                        } else if (error instanceof com.android.volley.ServerError) {
                            errorMessage = "Server error. Please try again later.";
                        } else if (error instanceof com.android.volley.AuthFailureError) {
                            errorMessage = "Authentication failure. Please check your credentials.";
                        } else if (error instanceof com.android.volley.ParseError) {
                            errorMessage = "Error parsing server response.";
                        } else if (error instanceof com.android.volley.TimeoutError) {
                            errorMessage = "Request timed out. Please try again.";
                        }
                        Toast.makeText(SignUpActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("full_name", fullNameEditText.getText().toString().trim());
                params.put("phone", phoneEditText.getText().toString().trim());
                params.put("email", emailEditText.getText().toString().trim());
                params.put("password", passwordEditText.getText().toString());
                params.put("confirm_password", confirmPasswordEditText.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
