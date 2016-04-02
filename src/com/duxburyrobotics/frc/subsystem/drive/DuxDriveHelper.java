package com.duxburyrobotics.frc.subsystem.drive;

import com.duxburyrobotics.frc.settings.Constants;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class DuxDriveHelper extends DuxDrive
{

    // Constants
    public static final int PNEUMATICS_UP = 1;
    public static final int PNEUMATICS_DOWN = -1;

    // Limit Switches
    private final DigitalInput ballLimitSwitch = new DigitalInput(Constants.LimitSwitches.LIMIT_SWITCH_PORT);
    private final DigitalInput armInLimitSwitch = new DigitalInput(Constants.LimitSwitches.ARMIN_SWITCH_PORT);
    private final DigitalInput armOutLimitSwitch = new DigitalInput(Constants.LimitSwitches.ARMOUT_SWITCH_PORT);

    // Counters
    private final Counter ballInCounter = new Counter(ballLimitSwitch);
    private final Counter armInCounter = new Counter(armInLimitSwitch);
    private final Counter armOutCounter = new Counter(armOutLimitSwitch);

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

    public void shootIntake()
    {
        runIntakeMotor(-1);
    }

    public void intakeIntake()
    {
        runIntakeMotor(.5);
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
