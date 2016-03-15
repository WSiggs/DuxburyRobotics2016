package com.duxburyrobotics.frc.subsystem.control;

import com.duxburyrobotics.frc.settings.Constants;
import com.duxburyrobotics.frc.subsystem.drive.DuxDriveHelper;
import edu.wpi.first.wpilibj.Joystick;

public class Teleop
{

    private DuxDriveHelper duxDrive;

    private Joystick driverControl;
    private Joystick operatorControl;

    private boolean isRampDown = false;

    public Teleop(DuxDriveHelper duxDrive)
    {
        this.duxDrive = duxDrive;

        this.driverControl = new Joystick(0);
        this.operatorControl = new Joystick(1);
    }

    public void periodic()
    {
        duxDrive.arcadeDrive(driverControl.getAxis(Joystick.AxisType.kY), driverControl.getAxis(Joystick.AxisType.kZ), true);
        duxDrive.moveArm(-operatorControl.getAxis(Constants.ARM_CONTROL_AXIS));
        int pneumaticValue = 0;
        if (operatorControl.getRawButton(Constants.LOWER_RAMP_BUTTON))
        {
            pneumaticValue = 1;
            isRampDown = false;
        }
        else if (operatorControl.getRawButton(Constants.RAISE_RAMP_BUTTON))
        {
            pneumaticValue = -1;
            isRampDown = true;
        }
        duxDrive.movePneumatics(pneumaticValue);
        int intakeMotorValue = 0;
        if (operatorControl.getRawButton(Constants.INTAKE_MOTOR_FORWARD_BUTTON))
            intakeMotorValue = 1;
        else if (operatorControl.getRawButton(Constants.INTAKE_MOTOR_REVERSE_BUTTON))
            intakeMotorValue = -1;
        duxDrive.runIntakeMotor(intakeMotorValue, isRampDown);
        
        if(operatorControl.getRawButton(Constants.SHOOT_BALL_BUTTON)) 
        	duxDrive.shootBallOnPush();
        
    }
}
