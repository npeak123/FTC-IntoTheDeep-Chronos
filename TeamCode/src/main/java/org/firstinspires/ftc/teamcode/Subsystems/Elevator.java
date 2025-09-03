package org.firstinspires.ftc.teamcode.Subsystems;


import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class Elevator {
    Servo moverServo;
    Servo shooterServo;

    Boolean ready = false;
    Boolean shoot = false;
    private DcMotor elevatorMotor;

    public Elevator (HardwareMap hardwareMap){

        elevatorMotor = hardwareMap.get(DcMotor.class, Constants.ElevatorConstants.elevatorMotor);

        elevatorMotor = motorConfig(elevatorMotor);

        moverServo = hardwareMap.get(Servo.class, Constants.ElevatorConstants.moverServo);
        shooterServo = hardwareMap.get(Servo.class, Constants.ElevatorConstants.shooterServo);
    }

    public void teleop(Gamepad gamepad1, Gamepad gamepad2) {

        if(gamepad2.right_trigger >= 0.1){
            elevatorMotor.setPower(-gamepad2.right_trigger);
        }

        if(gamepad2.left_trigger >= 0.1){
            elevatorMotor.setPower(gamepad2.left_trigger);
        } else if(gamepad2.left_trigger == 0 && gamepad2.right_trigger == 0){
            elevatorMotor.setPower(0);
        }

        if(ready) {
            if(gamepad2.left_bumper) {
                waitForBlock();
                ready = false;
            }
        } else {
            if(gamepad2.right_bumper) {
                readyToMove();
                ready = true;
            }
        }

        if(shoot) {
            if(gamepad2.b) {
                waitForBlock();
                shoot = false;
                ready = false;
            }
        } else {
            if(gamepad2.x) {
                score();
                shoot = true;
            }
        }


    }

    public void waitForBlock () {
        moverServo.setPosition(.12);
        shooterServo.setPosition(.50);
    }

    public void readyToMove () {
        moverServo.setPosition(.5);
        shooterServo.setPosition(.50);
    }

    public void score () {
        shooterServo.setPosition(0);
    }

    private DcMotor motorConfig(DcMotor motor) {
        motor.setZeroPowerBehavior(Constants.MecanumConstants.neutralMode);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        return motor;
    }



    public void periodic(Telemetry telemetry) {
        telemetry.addLine("Elevator:");
    }
}
