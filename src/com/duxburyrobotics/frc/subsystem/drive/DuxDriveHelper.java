package com.duxburyrobotics.frc.subsystem.drive;

import com.duxburyrobotics.frc.settings.Constants;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class DuxDriveHelper extends DuxDrive
{

    public static final int PNEUMATICS_UP = 1;
    public static final int PNEUMATICS_DOWN = -1;
    public static final int PNEUMATICS_OFF = 0;

    DigitalInput ballLimitSwitch = new DigitalInput(Constants.LIMIT_SWITCH_PORT);
    DigitalInput armInLimitSwitch= new DigitalInput(Constants.ARMIN_SWITCH_PORT);
    DigitalInput armOutLimitSwitch = new DigitalInput(Constants.ARMOUT_SWITCH_PORT);

    Counter ballInCounter = new Counter(ballLimitSwitch);
    Counter armInCounter = new Counter(armInLimitSwitch);
    Counter armOutCounter = new Counter(armOutLimitSwitch);


    public void intakeBall()
    {
        this.lowerIntake();
        this.intakeIntake();
        this.ballInCounter.reset();
        this.ballInCounter.startLiveWindowMode();
    }

    public void armsIn()
    {
        this.moveArm(-1);
        this.armInCounter.reset();
        this.armInCounter.startLiveWindowMode();
    }

    public void armsOut()
    {
        this.moveArm(1);
        this.armOutCounter.reset();
        this.armOutCounter.startLiveWindowMode();
    }

    public boolean ballIn()
    {
        if(ballInCounter.get() > 0)
        {
            ballInCounter.stopLiveWindowMode();
            return true;
        }
        else
            return false;
    }

    public boolean armIn()
    {
        if(armInCounter.get() > 0)
        {
            armInCounter.stopLiveWindowMode();
            armInCounter.reset();
            return true;
        }
        else
            return false;
    }



    public boolean armOut()
    {
        if(armOutCounter.get() > 0)
        {
            armOutCounter.stopLiveWindowMode();
            armOutCounter.reset();
            return true;
        }
        else
            return false;
    }

    public void shootBallOnPush()
    {
        //lowerIntake();
        shootIntake();
    }

    public void shootIntake()
    {
        runIntakeMotor(-1);
    }

    public void intakeIntake()
    {
        runIntakeMotor(.4);
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
        movePneumatics(PNEUMATICS_UP);
        runIntakeMotor(0);
    }

}
