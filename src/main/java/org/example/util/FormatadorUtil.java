package org.example.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class FormatadorUtil {

    private static final DateTimeFormatter DATA_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final NumberFormat NUMBER_FORMAT =
            NumberFormat.getInstance(new Locale("pt", "BR"));

    public static String formatarData(java.time.LocalDate data) {
        return DATA_FORMATTER.format(data);
    }

    public static String formatarSalario(BigDecimal valor) {
        return NUMBER_FORMAT.format(valor);
    }
}


