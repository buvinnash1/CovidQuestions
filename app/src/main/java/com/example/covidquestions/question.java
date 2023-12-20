package com.example.covidquestions;

public class question {
    private String questionAsked;
    private Boolean correctAnswer;
    private Boolean isDeciding;

    public question(String question, Boolean ans, Boolean decidingFactor){
        this.questionAsked = question;
        this.correctAnswer = ans;
        this.isDeciding = decidingFactor;
    }

    public void setCorrectAnswer(Boolean correctAns){
        this.correctAnswer = correctAns;
    }
    public void setQuestionAsked(String question){
        this.questionAsked = question;
    }
    public void setIsDeciding(Boolean decidingFactor){
        this.isDeciding = decidingFactor;
    }

    public String getQuestionAsked(){
        return this.questionAsked;
    }

    public Boolean getCorrectAnswer(){
        return this.correctAnswer;
    }

    public Boolean getIsDecidingFactor() {
        return this.isDeciding;
    }

}
