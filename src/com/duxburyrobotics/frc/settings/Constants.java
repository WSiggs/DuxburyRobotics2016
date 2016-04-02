package com.duxburyrobotics.frc.settings;

import edu.wpi.first.wpilibj.Joystick;

public class Constants
{
    public static class Motors
    {
        public static final int MIDDLE_WHEEL_LEFT_PORT = 4;
        public static final int MIDDLE_WHEEL_RIGHT_PORT = 1;

        public static final int ARM_LEFT_MOTOR_PORT = 3;
        public static final int ARM_RIGHT_MOTOR_PORT = 2;

        public static final int INTAKE_MOTOR_PORT = 0;
    }

    public static class Pneumatics
    {
        public static final int INTAKE_SOLENOID_PORT_ONE = 5;
        public static final int INTAKE_SOLENOID_PORT_TWO = 4;
    }

    public static class Buttons
    {
        public static final int LOWER_RAMP_BUTTON = 6;
        public static final int RAISE_RAMP_BUTTON = 5;

        public static final int GET_BALL_BUTTON = 4;
        public static final int SHOOT_BALL_BUTTON = 1;

        public static final int MIDDLE_WHEEL_TOGGLE = 2;

        public static final int ARM_FORWARD_BUTTON = 2;
        public static final int ARM_BACKWARD_BUTTON = 3;

        public static final int STOP_ALL_BUTTON = 1;
    }

    public static class LimitSwitches
    {
        public static final int LIMIT_SWITCH_PORT = 9;

        public static final int ARMIN_SWITCH_PORT = 4;
        public static final int ARMOUT_SWITCH_PORT = 5;
    }

    public static class Misc
    {
        public static final Joystick.AxisType ARM_CONTROL_AXIS = Joystick.AxisType.kY;

        public static final double ARM_SPEED_POWER = 2;
    }
}
