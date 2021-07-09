package edu.school21.print.logic.printer;

import edu.school21.print.logic.renderer.Renderer;

public class PrinterWithPrefixImpl implements Printer {
    private Renderer renderer;
    private String prefix = "";

    public PrinterWithPrefixImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {
        if (prefix.equals("")) {
            renderer.renderPrint(message);
        }
        else {
            prefix += " ";
            renderer.renderPrint(prefix += message);
        }
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
