package com.yan.databinding5;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> aTeamScore;
    private MutableLiveData<Integer> bTeamScore;
    private int aTeamLastScore;
    private int bTeamLastScore;

    public MutableLiveData<Integer> getaTeamScore() {
        if(aTeamScore == null) {
            aTeamScore = new MutableLiveData<>();
            aTeamScore.setValue(0);
        }
        return aTeamScore;
    }

    public MutableLiveData<Integer> getbTeamScore() {
        if(bTeamScore == null) {
            bTeamScore = new MutableLiveData<>();
            bTeamScore.setValue(0);
        }
        return bTeamScore;
    }

    public void aTeamScoreAdd(int number){
        saveLastScore();
        aTeamScore.setValue(aTeamScore.getValue()+number);
    }

    public void bTeamScoreAdd(int number){
        saveLastScore();
        bTeamScore.setValue(bTeamScore.getValue()+number);
    }

    public void undo(){
        aTeamScore.setValue(aTeamLastScore);
        bTeamScore.setValue(bTeamLastScore);
    }

    private void saveLastScore(){
        this.aTeamLastScore = aTeamScore.getValue();
        this.bTeamLastScore = bTeamScore.getValue();
    }

    public void scoreReset(){
        aTeamScore.setValue(0);
        bTeamScore.setValue(0);
    }
}
