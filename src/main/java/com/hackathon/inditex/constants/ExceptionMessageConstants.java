package com.hackathon.inditex.constants;

public class ExceptionMessageConstants {

    public static final String THERE_IS_ALREADY_A_LOGISTICS_CENTER_IN_THAT_POSITION = "There is already a logistics center in that position.";
    public static final String CURRENT_LOAD_CANNOT_EXCEED_MAX_CAPACITY = "Current load cannot exceed max capacity.";
    public static final String CENTER_NOT_FOUND = "Center not found.";
    public static final String YOU_CAN_T_CREATE_AN_INSTANCE_OF_THIS_CLASS = "You can't create an instance of this class.";

    private ExceptionMessageConstants() {
        throw new UnsupportedOperationException(YOU_CAN_T_CREATE_AN_INSTANCE_OF_THIS_CLASS);
    }
}
