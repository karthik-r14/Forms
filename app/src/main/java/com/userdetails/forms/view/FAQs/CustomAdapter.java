package com.userdetails.forms.view.FAQs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.userdetails.forms.R;

class CustomAdapter extends ArrayAdapter {
    private String[] questionNo;
    private String[] questions;
    private String[] answers;

    public CustomAdapter(Context context, String[] questionNo, String[] questions, String[] answers) {
        super(context, R.layout.custom_listview_layout, R.id.question_no, questionNo);
        this.questionNo = questionNo;
        this.questions = questions;
        this.answers = answers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_listview_layout, parent, false);

        TextView myQuestionNo = (TextView) row.findViewById(R.id.question_no);
        TextView myQuestion = (TextView) row.findViewById(R.id.question);
        TextView myAnswer = (TextView) row.findViewById(R.id.answer);

        myQuestionNo.setText(questionNo[position]);
        myQuestion.setText(questions[position]);
        myAnswer.setText(answers[position]);

        return row;
    }
}
