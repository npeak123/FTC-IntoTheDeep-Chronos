package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class Intake{
    DcMotor armExtender;
    CRServo intake;

    int armE = 0;
    boolean intakeOn = false;
    boolean intakeShoot =  false;

    public Intake(HardwareMap hardwareMap) {
        armExtender = hardwareMap.get(DcMotor.class, Constants.ClawConstants.armExtender);
        intake = hardwareMap.get(CRServo.class, Constants.ClawConstants.intake);

        armExtender.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        armExtender.setPower(0.3);
        //contract();

    }

    public void teleop(Gamepad gamepad1, Gamepad gamepad2) {
        if(gamepad2.right_trigger >= 0.1){
            armExtender.setPower(-gamepad2.right_trigger * .85);
        }

        if(gamepad2.left_trigger >= 0.1){
            armExtender.setPower(gamepad2.left_trigger * .85);
        } else if(gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0){
            armExtender.setPower(0.3);
        }

        if(gamepad1.right_trigger >= 0.1){
            intake.setPower(-gamepad1.right_trigger);
        }

        if(gamepad1.left_trigger >= 0.1){
            intake.setPower(gamepad1.left_trigger);
        } else if(gamepad1.left_trigger == 0 && gamepad1.right_trigger == 0){
            intake.setPower(0);
        }


    }

    //public void extend() {
    //    armExtender.setPosition(Constants.ClawConstants.extendedAngle);
    //}

   // public void contract() {
  //      armExtender.setPosition(Constants.ClawConstants.contractedAngle);
  //  }

    public void takeIn () {
        intake.setPower(-1);
    }

    public void stop () {
        intake.setPower(0);
    }

    public void shoot () {
        intake.setPower(1);
        }

       public void periodic(Telemetry telemetry) {
        telemetry.addLine("Intake:");
    }

    }

