package com.kerrrusha.lab_1_pizza_ordering;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.kerrrusha.lab_1_pizza_ordering.domain.validating.PizzaOrderValidator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    EditText pizzaName;
    RadioGroup radioGroup;
    LinearLayout checkboxesLinearLayout;
    Button okButton;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pizzaName = (EditText) findViewById(R.id.pizzaName);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        checkboxesLinearLayout = (LinearLayout) findViewById(R.id.checkbox_group);
        okButton = (Button) findViewById(R.id.okButton);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        okButton.setOnClickListener(v -> {
            processOrder();
        });
    }

    private void processOrder() {
        final String SEPARATOR = "; ";

        String pizzaNameText = pizzaName.getText().toString();
        int selectedPizzaSizeRadioButtonId = radioGroup.getCheckedRadioButtonId();
        List<CheckBox> checkedCheckBoxes = getCheckedCheckboxes();

        PizzaOrderValidator validator = new PizzaOrderValidator(pizzaNameText, selectedPizzaSizeRadioButtonId);
        if (!validator.getErrors().isEmpty()) {
            showPopupWindow(String.join(SEPARATOR, validator.getErrors()));
            return;
        }

        StringBuilder result = new StringBuilder();

        result.append(pizzaNameText).append(SEPARATOR);
        result.append(((RadioButton) findViewById(selectedPizzaSizeRadioButtonId)).getText().toString()).append(SEPARATOR);
        checkedCheckBoxes.forEach(e -> result.append(e.getText().toString()).append(SEPARATOR));

        resultTextView.setText(result.toString());
    }

    private void showPopupWindow(String message) {
        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);

        PopupWindow popupWindow = new PopupWindow(
                popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        popupWindow.setFocusable(true);

        TextView popupText = popupView.findViewById(R.id.popup_text);
        popupText.setText(message);

        Button closeButton = popupView.findViewById(R.id.popup_button);
        closeButton.setOnClickListener(v -> popupWindow.dismiss());

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }

    private List<CheckBox> getCheckedCheckboxes() {
        int childCount = checkboxesLinearLayout.getChildCount();
        List<CheckBox> checkedCheckBoxes = new ArrayList<>();

        for (int i = 0; i < childCount; i++) {
            View view = checkboxesLinearLayout.getChildAt(i);
            if (view instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) view;
                if (checkBox.isChecked()) {
                    checkedCheckBoxes.add(checkBox);
                }
            }
        }

        return checkedCheckBoxes;
    }
}
