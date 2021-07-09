package edu.school21.print.logic.preprocessor;

public class PreProcessorToUpperImpl implements PreProcessor{

    @Override
    public String preProcessing(String message) {
        return message.toUpperCase();
    }
}
