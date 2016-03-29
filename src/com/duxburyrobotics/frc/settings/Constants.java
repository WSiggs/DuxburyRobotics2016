package com.duxburyrobotics.frc.settings;

import edu.wpi.first.wpilibj.Joystick;

public class Constants
{

    public static final int MIDDLE_WHEEL_LEFT_PORT = 4;
    public static final int MIDDLE_WHEEL_RIGHT_PORT = 1;

    public static final int ARM_LEFT_MOTOR_PORT = 3;
    public static final int ARM_RIGHT_MOTOR_PORT = 2;

    public static final Joystick.AxisType ARM_CONTROL_AXIS = Joystick.AxisType.kY;

    public static final double ARM_SPEED_POWER = 5;

    public static final int RAMP_SOLENOID_PORT_ONE = 6;
    public static final int RAMP_SOLENOID_PORT_TWO = 7;

    public static final int LOWER_RAMP_BUTTON = 6;
    public static final int RAISE_RAMP_BUTTON = 5;

    public static final int INTAKE_SOLENOID_PORT_ONE = 5;
    public static final int INTAKE_SOLENOID_PORT_TWO = 4;

    public static final int INTAKE_MOTOR_FORWARD_BUTTON = 4;
    public static final int INTAKE_MOTOR_REVERSE_BUTTON = 1;

    public static final int INTAKE_MOTOR_PORT = 0;

    public static final double INTAKE_MOTOR_SPEED = 1f;
    public static final double RAMP_DOWN_INTAKE_MOTOR_SPEED = 1f / 3f;

    public static final int FRONT_LEFT_ENCODER_PORT_ONE = 0;
    public static final int FRONT_LEFT_ENCODER_PORT_TWO = 1;

    public static final int REAR_LEFT_ENCODER_PORT_ONE = 2;
    public static final int REAR_LEFT_ENCODER_PORT_TWO = 3;

    public static final int FRONT_RIGHT_ENCODER_PORT_ONE = 4;
    public static final int FRONT_RIGHT_ENCODER_PORT_TWO = 5;

    public static final int REAR_RIGHT_ENCODER_PORT_ONE = 6;
    public static final int REAR_RIGHT_ENCODER_PORT_TWO = 7;
    
    public static final int PORTCULLIS_BUTTON = 7;
    public static final int CHEVAL_BUTTON = 8;
    
    public static final int SHOOT_BALL_BUTTON = 1;

    public static final int LIMIT_SWITCH_PORT = 9;
    
    public static final int MIDDLE_WHEEL_TOGGLE = 2;
}
