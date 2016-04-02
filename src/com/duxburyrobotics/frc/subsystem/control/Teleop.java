package com.duxburyrobotics.frc.subsystem.control;

import com.duxburyrobotics.frc.settings.Constants;
import com.duxburyrobotics.frc.subsystem.drive.DuxDriveHelper;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Compressor;

public class Teleop
{

    private final DuxDriveHelper duxDrive;

    private final Joystick driverControl;
    private final Joystick operatorControl;

    private final Compressor compressor;

    public boolean catchingBall = false;
    public boolean armMoving = false;
    
    public boolean runComp = true;

    public Teleop(DuxDriveHelper duxDrive, boolean runComp)
    {
        this.duxDrive = duxDrive;

        this.runComp = runComp;
        this.compressor = new Compressor();
        
        this.driverControl = new Joystick(0);
        this.operatorControl = new Joystick(1);
    }

    public void periodic()
    {
        // Middle wheel and driving
        boolean middleWheel = driverControl.getRawButton(Constants.Buttons.MIDDLE_WHEEL_TOGGLE);
        duxDrive.arcadeDrive(driverControl.getAxis(Joystick.AxisType.kY), driverControl.getAxis(Joystick.AxisType.kZ), middleWheel);

        // Move the arm
        if(!this.armMoving)
        	duxDrive.moveArm(-operatorControl.getAxis(Constants.Misc.ARM_CONTROL_AXIS));

        // Move the pneumatics
        int pneumaticValue = 0;
        if (operatorControl.getRawButton(Constants.Buttons.LOWER_RAMP_BUTTON))
            pneumaticValue = 1;
        else if (operatorControl.getRawButton(Constants.Buttons.RAISE_RAMP_BUTTON))
            pneumaticValue = -1;
        duxDrive.movePneumatics(pneumaticValue);

        // Intake motor
        if(!catchingBall)
        {
            duxDrive.runIntakeMotor(operatorControl.getRawAxis(2));
        }

        // Shoot ball
        if(driverControl.getRawButton(Constants.Buttons.SHOOT_BALL_BUTTON))
        	duxDrive.shootIntake();

        // Intake ball
        if(operatorControl.getRawButton(Constants.Buttons.GET_BALL_BUTTON))
        {
            duxDrive.intakeBall();
            this.catchingBall = true;
        }

        // Stop everything
        if(this.catchingBall && duxDrive.ballIn())
        {
            this.catchingBall = false;
            duxDrive.stopAll();
        }

        // Move arm out
        if(operatorControl.getRawButton(Constants.Buttons.ARM_FORWARD_BUTTON))
        {
            duxDrive.armsOut();
            this.armMoving = true;
        }

        // Move arm in
        if(operatorControl.getRawButton(Constants.Buttons.ARM_BACKWARD_BUTTON))
        {
            duxDrive.armsIn();
            this.armMoving = true;
        }

        // Stop moving arm
        if(this.armMoving && (duxDrive.armOut() || duxDrive.armIn()))
        {
            duxDrive.moveArm(0);
            this.armMoving = false;
        }

        // Emergency stop
        if(this.catchingBall && operatorControl.getRawButton(Constants.Buttons.STOP_ALL_BUTTON))
        	duxDrive.stopAll();
    }
    
    public void init()
    {
    	if(!this.runComp)
    		this.compressor.stop();
    }
}
