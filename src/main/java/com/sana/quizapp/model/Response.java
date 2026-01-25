package com.sana.quizapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

    private Long questionId;

    @JsonProperty("response")
    private String selectedOption;

    public Response() {
    }

    public Response(Long questionId, String selectedOption) {
        this.questionId = questionId;
        this.selectedOption = selectedOption;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}


