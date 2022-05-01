package ru.rob.model.debt.enums;

public enum DebtResponseStatus {
    NOT_SENT("Без ответа"),
    SENT("Ответ ЖКХ"),
    AUTO_GENERATED("Автоответ ГИС");

    public final String description;

    DebtResponseStatus(String description){
        this.description = description;
    }

    public static DebtResponseStatus get(final String responseStatus) {
        try {
            return valueOf(responseStatus);
        } catch (Exception e){
            return null;
        }
    }

}
