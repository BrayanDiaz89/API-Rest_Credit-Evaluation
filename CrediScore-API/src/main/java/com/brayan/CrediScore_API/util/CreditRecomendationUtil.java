package com.brayan.CrediScore_API.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CreditRecomendationUtil {

    //APPROVED FALSE
    public final static String REQUIRED_CODEUDOR = "El cliente requiere un codeudor, de lo contrario, no es posible aprobar el crédito.";
    public final static String APPROVED_FALSE_BY_AGE = "El cliente no cuenta con la edad mínima, para recibir la aprobación del crédito.";
    public final static String APPROVED_FALSE_BY_SCORE_HISTORY = "El cliente no cuenta con el historial crediticio suficiente para la aprobación del crédito.";

    //APPROVED TRUE
    public final static String APPROVED_TRUE_LOW_RISK = "Se recomienda la aprobación del crédito al cliente.";
    public final static String APPROVED_TRUE_MEDIUM_RISK = "Se aprueba el crédito, pero con un límite de préstamo reducido.";
}
