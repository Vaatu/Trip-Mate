package com.vaatu.tripmate.ui.home.addButtonActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.here.sdk.core.GeoCoordinates;
import com.here.sdk.core.LanguageCode;
import com.here.sdk.core.TextFormat;
import com.here.sdk.core.errors.InstantiationErrorException;
import com.here.sdk.search.AutosuggestEngine;
import com.here.sdk.search.AutosuggestOptions;
import com.here.sdk.search.AutosuggestResult;
import com.here.sdk.search.AutosuggestResultType;
import com.here.sdk.search.SearchError;
import com.vaatu.tripmate.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddBtnActivity extends AppCompatActivity {
    private static final String LOG_TAG = "HERE Auto Suggest";

    AutosuggestEngine autosuggestEngine;
    List<String> results = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_btn);
        ButterKnife.bind(this);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                GeoCoordinates centerGeoCoordinates = new GeoCoordinates(30.8265076, 30.8149639);
                try {
                    autosuggestEngine = new AutosuggestEngine();
                    int maxSearchResults = 10;
                    AutosuggestOptions autosuggestOptions = new AutosuggestOptions(
                            LanguageCode.EN_US,
                            TextFormat.PLAIN,
                            maxSearchResults,
                            new ArrayList<>(Collections.singletonList(
                                    AutosuggestResultType.PLACE)));
                    autosuggestEngine.suggest(
                            autoCompleteTextView.getText().toString(), // User typed "p".
                            centerGeoCoordinates,
                            autosuggestOptions,
                            autosuggestCallback);
                } catch (InstantiationErrorException e) {
                    new RuntimeException("Initialization of AutosuggestEngine failed: " + e.error.name());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }
    private final AutosuggestEngine.Callback autosuggestCallback = new AutosuggestEngine.Callback() {
        @Override
        public void onSearchCompleted(@Nullable SearchError searchError, @Nullable List<AutosuggestResult> list) {
            if (searchError != null) {
                Log.d(LOG_TAG, "Autosuggest Error: " + searchError.name());
                return;
            }

            if (list.isEmpty()) {
                Log.d(LOG_TAG, "Autosuggest: No results found");
            } else {
                Log.d(LOG_TAG, "Autosuggest results: " + list.size());
                results.clear();
                for (AutosuggestResult autosuggestResult : list) {
                    Log.d(LOG_TAG, "Autosuggest result: " + autosuggestResult.title +
                            " Highlighted: " + autosuggestResult.coordinates);
                    results.add(autosuggestResult.vicinity);
                }
            }
            adapter = new ArrayAdapter<String>(AddBtnActivity.this,
                    android.R.layout.simple_dropdown_item_1line, results);
            autoCompleteTextView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    };
}
