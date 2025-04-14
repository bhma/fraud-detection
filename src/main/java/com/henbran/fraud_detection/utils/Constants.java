package com.henbran.fraud_detection.utils;

public class Constants {
    public static final String INVALID_USER_OR_PASSWORD_STRING = "Usuário ou senha inválidos.";
    public static final String FRAUD_DETECTED_STRING = "🚨 Fraude detectada na transação!"; 
    public static final String SECURE_TRANSACTION_STRING = "✅ Transação segura.";
    public static final String USERNAME_ALREADY_TAKE_STRING = "Username already taken";
    public static final String USER_NOT_FOUND_STRING = "User not found!";
    public static final String USER_DELETED_SUCCESSFULY_STRING = "User deleted successfully!";
    public static final String INVALID_DATA_USER_STRING = "Usuário inválido! Verifique os campos firstName, LastName, Username e Password!";
    public static final String ID_NOT_NULL_STRING = "O id não pode ser nulo.";
    public static final String INVALID_DATA_TRANSACTION_STRING = "Transaction não é válido. Verifique todos os campos.";

    public static final String REDIS_CHANNEL_STRING = "email_channel";
}
