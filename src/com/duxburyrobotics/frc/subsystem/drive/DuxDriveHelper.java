package com.duxburyrobotics.frc.subsystem.drive;

public class DuxDriveHelper extends DuxDrive
{

    public static final int PNEUMATICS_UP = 1;
    public static final int PNEUMATICS_DOWN = -1;
    public static final int PNEUMATICS_OFF = 0;

    public static final int INTAKE_REVERSE = 1;
    public static final int INTAKE_FORWARD = -1;
    public static final int INTAKE_STOP = 0;

    public void shootBallOnPush()
    {
        lowerIntake();
        intakeIntake();
    }

    public void shootIntake()
    {
        runIntakeMotor(INTAKE_FORWARD, false);
    }

    public void intakeIntake()
    {
        runIntakeMotor(INTAKE_REVERSE, false);
    }

    public void raiseIntake()
    {
        movePneumatics(PNEUMATICS_UP);
    }

    public void lowerIntake()
    {
        movePneumatics(PNEUMATICS_DOWN);
    }

    public void stopAll()
    {
        movePneumatics(PNEUMATICS_OFF);
        runIntakeMotor(INTAKE_STOP, false);
        
    }
  	
}
