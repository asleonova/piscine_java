package edu.school21.print.logic.printer;

import edu.school21.print.logic.renderer.Renderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterWithDateTimeImpl implements Printer {

    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String message) {

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        String dateTimeMessage = currentDateTime.format(dateTimeFormatter);
        dateTimeMessage += message;

        renderer.renderPrint(dateTimeMessage);
    }
}
