package edu.school21.print.logic.preprocessor;

public class PreProcessorToLowerImpl implements PreProcessor{

    @Override
    public String preProcessing(String message) {
        return message.toLowerCase();
    }
}
