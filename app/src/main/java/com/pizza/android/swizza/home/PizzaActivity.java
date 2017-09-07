package com.pizza.android.swizza.home;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pizza.android.swizza.BaseApp;
import com.pizza.android.swizza.R;
import com.pizza.android.swizza.model.ExcludeList;
import com.pizza.android.swizza.model.PizzaResponse;
import com.pizza.android.swizza.model.VariantGroup;
import com.pizza.android.swizza.model.Variation;
import com.pizza.android.swizza.networking.Service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PizzaActivity extends BaseApp implements PizzaView {

    ArrayList<VariantGroup> mListVariantGroup = new ArrayList<>();
    ArrayList<Variation> mListVariations = new ArrayList<>();
    ArrayList<ArrayList<ExcludeList>> mListExcludeList = new ArrayList<>();

    LinearLayout mLinearLayout;
    RadioGroup rg;
    RadioButton[] rb;

    List<RadioButton> mHiddenButtons = new ArrayList<>();

    @Inject
    public Service service;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPizzaComponent().inject(this);

        renderView();
        mLinearLayout = (LinearLayout) findViewById(R.id.activity_main);
        PizzaPresenter presenter = new PizzaPresenter(service, this);
        presenter.getPizzaList();
    }

    public void renderView() {
        setContentView(R.layout.activity_pizza);
    }


    @Override
    public void onFailure(String appErrorMessage) {
        Toast.makeText(this, "please check your network", Toast.LENGTH_LONG).show();
    }

    @Override
    public void getPizzaResponseSuccess(PizzaResponse pizzaResponse) {
        mListVariantGroup = pizzaResponse.getVariants().getVariantGroups();
        mListExcludeList = pizzaResponse.getVariants().getExcludeList();

        for (int i = 0; i < mListVariantGroup.size(); i++) {

            showRadioGroupName(mListVariantGroup.get(i).getName());

            rg = new RadioGroup(PizzaActivity.this);
            rg.setOrientation(RadioGroup.VERTICAL);
            rg.setPadding(24, 0, 0, 24);
            rg.setId(1000 + i);

            mLinearLayout.addView(rg);

            mListVariations = mListVariantGroup.get(i).getVariations();

            rb = new RadioButton[mListVariations.size()];

            for (int j = 0; j < mListVariations.size(); j++) {

                rb[j] = new RadioButton(PizzaActivity.this);

                rb[j].setPadding(8, 0, 0, 0);

                rb[j].setText(mListVariations.get(j).getName() + " " +
                        "(Price : " + mListVariations.get(j).getPrice() + ", " +
                        "InStock : " + mListVariations.get(j).getInStock() + ")");

                rb[j].setTextColor(getResources().getColor(R.color.colorOrange));

                rb[j].setId(Integer.parseInt(mListVariations.get(j).getId()));

                rb[j].setTag(mListVariantGroup.get(i).getGroupId() + "," + rg.getId());

                rb[j].setOnClickListener(mThisButtonListener);

                rg.addView(rb[j]);
            }
        }
    }

    void unHideButtons(String currentClickedRadioGroupId) {

        for (RadioButton rb : mHiddenButtons) {

            rb.setVisibility(View.VISIBLE);

            String tag = (String) rb.getTag();
            String[] parts = tag.split(",");
            String radioGroupID = parts[1];

            if (!currentClickedRadioGroupId.equals((radioGroupID))) {
                RadioGroup rg = (RadioGroup) findViewById(Integer.parseInt(radioGroupID));
                rg.clearCheck();
            }
        }
    }

    void showRadioGroupName(String name) {

        TextView view = new TextView(this);
        view.setText(name);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        view.setTextColor(getResources().getColor(R.color.colorPrimary));
        mLinearLayout.addView(view);
    }

    private View.OnClickListener mThisButtonListener = new View.OnClickListener() {

        public void onClick(View v) {

            String tag = (String) v.getTag();
            String[] parts = tag.split(",");
            String group_id = parts[0];
            String radioGroupID = parts[1];

            unHideButtons(radioGroupID);
            mHiddenButtons.clear();

            int variation_id = v.getId();

            for (int i = 0; i < mListExcludeList.size(); i++) {

                List<ExcludeList> excludeListPair = mListExcludeList.get(i);

                for (int j = 0; j < excludeListPair.size(); j++) {

                    if (excludeListPair.get(j).getGroupId().equals(group_id) &&
                            excludeListPair.get(j).getVariationId().equals(Integer.toString(variation_id))) {

                        for (int k = 0; k < excludeListPair.size(); k++) {

                            if (k == j)
                                continue;

                            int var_id = Integer.parseInt(excludeListPair.get(k).getVariationId());

                            RadioButton rb = (RadioButton) findViewById(var_id);
                            mHiddenButtons.add(rb);
                            rb.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            }
        }
    };


}
