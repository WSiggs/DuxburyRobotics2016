package com.duxburyrobotics.frc.subsystem.control;

import com.duxburyrobotics.frc.settings.Constants;
import com.duxburyrobotics.frc.subsystem.drive.DuxDriveHelper;
import edu.wpi.first.wpilibj.Joystick;

public class Teleop
{

    private DuxDriveHelper duxDrive;
    private Autonomous auto;

    private Joystick driverControl;
    private Joystick operatorControl;

    private boolean isRampDown = false;
    
    private boolean middleWheel = false;

    public Teleop(DuxDriveHelper duxDrive, Autonomous auto)
    {
        this.duxDrive = duxDrive;
        this.auto = auto;

        this.driverControl = new Joystick(0);
        this.operatorControl = new Joystick(1);
    }

    public void periodic()
    {
    	if(driverControl.getRawButton(Constants.MIDDLE_WHEEL_TOGGLE))
    		middleWheel = true;
    	else
    		middleWheel = false;
    	
        duxDrive.arcadeDrive(driverControl.getAxis(Joystick.AxisType.kY), driverControl.getAxis(Joystick.AxisType.kZ), middleWheel);
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
        
        /*if (operatorControl.getRawButton(Constants.INTAKE_MOTOR_FORWARD_BUTTON))
            intakeMotorValue = 1;
        else if (operatorControl.getRawButton(Constants.INTAKE_MOTOR_REVERSE_BUTTON))
            intakeMotorValue = -1;
        duxDrive.runIntakeMotor(intakeMotorValue, isRampDown);*/
        
        if(operatorControl.getRawAxis(2) > 0)
		{
			duxDrive.runIntakeMotor(operatorControl.getRawAxis(2), false);
		}
        else if(operatorControl.getRawAxis(3) > 0)
		{
			duxDrive.runIntakeMotor(operatorControl.getRawAxis(3), false);
		}
        else
        {
        	duxDrive.runIntakeMotor(0, false);
        }
        
        if (intakeMotorValue == 0 && pneumaticValue == 0)
            duxDrive.resetLimitSwitch();
        
        if(driverControl.getRawButton(Constants.SHOOT_BALL_BUTTON)) 
        	duxDrive.shootBallOnPush();

//        if (operatorControl.getRawButton(Constants.CHEVAL_BUTTON))
//            //auto.chevalMethod();
//        else
//            auto.chevalReset();

//        if (operatorControl.getRawButton(Constants.PORTCULLIS_BUTTON))
//            //auto.portcullisMethod();
//        else
//            auto.portcullisReset();

        
    }
}
