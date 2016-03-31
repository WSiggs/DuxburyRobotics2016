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
        // Middle wheel and driving
        middleWheel = driverControl.getRawButton(Constants.MIDDLE_WHEEL_TOGGLE);
        duxDrive.arcadeDrive(driverControl.getAxis(Joystick.AxisType.kY), driverControl.getAxis(Joystick.AxisType.kZ), middleWheel);

        // Arm Buttons
        if (operatorControl.getRawButton(Constants.ARM_DOWN_BUTTON))
            duxDrive.moveArmWithLimitSwitchChecking(true);
        else if (operatorControl.getRawButton(Constants.ARM_UP_BUTTON))
            duxDrive.moveArmWithLimitSwitchChecking(false);

        // Move the arm
        duxDrive.moveArm(-operatorControl.getAxis(Constants.ARM_CONTROL_AXIS));

        // Move the pneumatics
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

        // Intake motor
        double intakeMotorValue = operatorControl.getRawAxis(2) - operatorControl.getRawAxis(3);

        if (intakeMotorValue < 0.05 && intakeMotorValue > -0.05) intakeMotorValue = 0;
        duxDrive.runIntakeMotor(operatorControl.getRawAxis(2));

        // Reset the limit switch
        if (intakeMotorValue == 0 && pneumaticValue == 0)
            duxDrive.resetLimitSwitch();

        // Shoot ball
        if(driverControl.getRawButton(Constants.SHOOT_BALL_BUTTON)) 
        	duxDrive.shootBallOnPush();
        
    }
}
