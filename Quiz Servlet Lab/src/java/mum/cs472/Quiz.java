/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mum.cs472;

/**
 *
 * @author Natnael Getachew
 */

public class Quiz {
    private static String[] questions = {
        "3,1,4,1,5",
        "1,1,2,3,5",
        "1,4,9,16,25"
    };
    private static int[] answers = {9,8,36,13,32};
    public Quiz(){
    }
    public String[] getQuestions(){
        return questions;
    }
    public int[] getAnswers(){
        return answers;
    }
    
    public String getQuestion(int index){
        return questions[index];
    }
    
    public int getAnswer(int index){
        return answers[index];
    }    
}
