package com.duxburyrobotics.frc.subsystem.drive;

public class DuxDriveHelper extends DuxDrive
{

    public static final int PNEUMATICS_UP = 1;
    public static final int PNEUMATICS_DOWN = -1;
    public static final int PNEUMATICS_OFF = 0;

    public void shootBallOnPush()
    {
        lowerIntake();
        intakeIntake();
    }

    public void shootIntake()
    {
        runIntakeMotor(1);
    }

    public void intakeIntake()
    {
        runIntakeMotor(-1);
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
        runIntakeMotor(0);
        
    }
  	
}
